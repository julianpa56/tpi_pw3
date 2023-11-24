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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author julia
 */
@Entity
@Table(name = "carrito_compras", catalog = "tpi_pw3", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CarritoCompras.findAll", query = "SELECT c FROM CarritoCompras c"),
    @NamedQuery(name = "CarritoCompras.findByCarritoId", query = "SELECT c FROM CarritoCompras c WHERE c.carritoId = :carritoId")})
public class CarritoCompras implements Serializable {

    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    @ManyToOne
    private Usuarios usuarioId;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "carrito_id", nullable = false)
    private Integer carritoId;
    @OneToMany(mappedBy = "carritoId")
    private Collection<ElementosCarrito> elementosCarritoCollection;

    public CarritoCompras() {
    }

    public CarritoCompras(Integer carritoId) {
        this.carritoId = carritoId;
    }

    public Integer getCarritoId() {
        return carritoId;
    }

    public void setCarritoId(Integer carritoId) {
        this.carritoId = carritoId;
    }

    @XmlTransient
    public Collection<ElementosCarrito> getElementosCarritoCollection() {
        return elementosCarritoCollection;
    }

    public void setElementosCarritoCollection(Collection<ElementosCarrito> elementosCarritoCollection) {
        this.elementosCarritoCollection = elementosCarritoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carritoId != null ? carritoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarritoCompras)) {
            return false;
        }
        CarritoCompras other = (CarritoCompras) object;
        if ((this.carritoId == null && other.carritoId != null) || (this.carritoId != null && !this.carritoId.equals(other.carritoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CarritoCompras[ carritoId=" + carritoId + " ]";
    }

    public Usuarios getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuarios usuarioId) {
        this.usuarioId = usuarioId;
    }
    
}
