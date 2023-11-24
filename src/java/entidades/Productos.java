/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author julia
 */
@Entity
@Table(name = "productos", catalog = "tpi_pw3", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p"),
    @NamedQuery(name = "Productos.findByProductoId", query = "SELECT p FROM Productos p WHERE p.productoId = :productoId"),
    @NamedQuery(name = "Productos.findByNombre", query = "SELECT p FROM Productos p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Productos.findByPrecio", query = "SELECT p FROM Productos p WHERE p.precio = :precio"),
    @NamedQuery(name = "Productos.findByStock", query = "SELECT p FROM Productos p WHERE p.stock = :stock")})
public class Productos implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;
    @Lob
    @Size(max = 65535)
    @Column(name = "descripcion", length = 65535)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio", nullable = false)
    private double precio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stock", nullable = false)
    private int stock;
    @Lob
    @Size(max = 65535)
    @Column(name = "path_imagen", length = 65535)
    private String pathImagen;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "producto_id", nullable = false)
    private Integer productoId;
    @OneToMany(mappedBy = "productoId")
    private Collection<ElementosCarrito> elementosCarritoCollection;
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    @ManyToOne
    private Usuarios usuarioId;

    public Productos() {
    }

    public Productos(Integer productoId) {
        this.productoId = productoId;
    }

    public Productos(Integer productoId, String nombre, double precio, int stock) {
        this.productoId = productoId;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }


    public String getPathImagen() {
        return pathImagen;
    }

    public void setPathImagen(String pathImagen) {
        this.pathImagen = pathImagen;
    }

    @XmlTransient
    public Collection<ElementosCarrito> getElementosCarritoCollection() {
        return elementosCarritoCollection;
    }

    public void setElementosCarritoCollection(Collection<ElementosCarrito> elementosCarritoCollection) {
        this.elementosCarritoCollection = elementosCarritoCollection;
    }

    public Usuarios getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuarios usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productoId != null ? productoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.productoId == null && other.productoId != null) || (this.productoId != null && !this.productoId.equals(other.productoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Productos[ productoId=" + productoId + " ]";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
}
