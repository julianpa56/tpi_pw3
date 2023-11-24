/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesiones;

import entidades.CarritoCompras;
import entidades.Usuarios;
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
public class CarritoComprasFacade extends AbstractFacade<CarritoCompras> {

    @PersistenceContext(unitName = "EcommercePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarritoComprasFacade() {
        super(CarritoCompras.class);
    }
    
    public CarritoCompras findByUser(Usuarios usuario) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CarritoCompras> cq = cb.createQuery(CarritoCompras.class); // Especifica el tipo de resultado

        Root<CarritoCompras> c = cq.from(CarritoCompras.class);
        cq.select(c);
        cq.where(cb.equal(c.get("usuarioId"), usuario));

        TypedQuery<CarritoCompras> q = em.createQuery(cq); 
        if (!q.getResultList().isEmpty()) {
            return q.getResultList().get(0);
        } else {
            return null; 
        }
    }
}
