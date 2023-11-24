/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesiones;

import entidades.Productos;
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
public class ProductosFacade extends AbstractFacade<Productos> {

    @PersistenceContext(unitName = "EcommercePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductosFacade() {
        super(Productos.class);
    }
    
    public List<Productos> findByUser(Usuarios usuario) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Productos> cq = cb.createQuery(Productos.class);
        
        Root<Productos> c = cq.from(Productos.class);
        cq.select(c);
        cq.where(cb.equal(c.get("usuarioId"), usuario));
        
        TypedQuery<Productos> q = em.createQuery(cq);
        return q.getResultList();
    }
    
    public List<Productos> findByCoincidence(String coincidencia) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Productos> cq = cb.createQuery(Productos.class);
        
        Root<Productos> c = cq.from(Productos.class);
        cq.select(c);
        cq.where(cb.like(cb.lower(c.get("nombre")), "%" + coincidencia + "%"));
        
        TypedQuery<Productos> q = em.createQuery(cq);
        return q.getResultList();
    }
    
}
