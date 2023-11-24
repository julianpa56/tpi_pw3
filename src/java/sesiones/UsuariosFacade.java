/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesiones;

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
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "EcommercePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    
    public List<Usuarios> findByEmail(String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Usuarios> cq = cb.createQuery(Usuarios.class); // Especifica el tipo de resultado

        Root<Usuarios> c = cq.from(Usuarios.class);
        cq.select(c);
        cq.where(cb.equal(c.get("correoElectronico"), email));

        TypedQuery<Usuarios> q = em.createQuery(cq); // Usa TypedQuery para obtener el tipo correcto de resultado
        return q.getResultList();
    }
    
}
