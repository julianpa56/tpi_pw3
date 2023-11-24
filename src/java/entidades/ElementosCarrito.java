/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author julia
 */
@Entity
@Table(name = "elementos_carrito", catalog = "tpi_pw3", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ElementosCarrito.findAll", query = "SELECT e FROM ElementosCarrito e"),
    @NamedQuery(name = "ElementosCarrito.findByElementoCarritoId", query = "SELECT e FROM ElementosCarrito e WHERE e.elementoCarritoId = :elementoCarritoId"),
    @NamedQuery(name = "ElementosCarrito.findByCantidad", query = "SELECT e FROM ElementosCarrito e WHERE e.cantidad = :cantidad")})
public class ElementosCarrito implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    @JoinColumn(name = "producto_id", referencedColumnName = "producto_id")
    @ManyToOne
    private Productos productoId;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "elemento_carrito_id", nullable = false)
    private Integer elementoCarritoId;
    @JoinColumn(name = "carrito_id", referencedColumnName = "carrito_id")
    @ManyToOne
    private CarritoCompras carritoId;

    public ElementosCarrito() {
    }

    public ElementosCarrito(Integer elementoCarritoId) {
        this.elementoCarritoId = elementoCarritoId;
    }

    public ElementosCarrito(Integer elementoCarritoId, int cantidad) {
        this.elementoCarritoId = elementoCarritoId;
        this.cantidad = cantidad;
    }

    public Integer getElementoCarritoId() {
        return elementoCarritoId;
    }

    public void setElementoCarritoId(Integer elementoCarritoId) {
        this.elementoCarritoId = elementoCarritoId;
    }


    public CarritoCompras getCarritoId() {
        return carritoId;
    }

    public void setCarritoId(CarritoCompras carritoId) {
        this.carritoId = carritoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (elementoCarritoId != null ? elementoCarritoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ElementosCarrito)) {
            return false;
        }
        ElementosCarrito other = (ElementosCarrito) object;
        if ((this.elementoCarritoId == null && other.elementoCarritoId != null) || (this.elementoCarritoId != null && !this.elementoCarritoId.equals(other.elementoCarritoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ElementosCarrito[ elementoCarritoId=" + elementoCarritoId + " ]";
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Productos getProductoId() {
        return productoId;
    }

    public void setProductoId(Productos productoId) {
        this.productoId = productoId;
    }
    
}
