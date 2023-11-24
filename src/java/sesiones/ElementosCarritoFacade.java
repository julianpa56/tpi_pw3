/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesiones;

import entidades.CarritoCompras;
import entidades.ElementosCarrito;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author julia
 */
@Stateless
public class ElementosCarritoFacade extends AbstractFacade<ElementosCarrito> {

    @PersistenceContext(unitName = "EcommercePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ElementosCarritoFacade() {
        super(ElementosCarrito.class);
    }
    
    public List<ElementosCarrito> findByCarrito(CarritoCompras carrito) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ElementosCarrito> cq = cb.createQuery(ElementosCarrito.class); // Especifica el tipo de resultado

        Root<ElementosCarrito> c = cq.from(ElementosCarrito.class);
        cq.select(c);
        cq.where(cb.equal(c.get("carritoId"), carrito));

        TypedQuery<ElementosCarrito> q = em.createQuery(cq); 
        return q.getResultList();
    }
    
}
