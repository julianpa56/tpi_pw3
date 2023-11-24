package entidades;

import entidades.ElementosCarrito;
import entidades.Usuarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-11-22T16:41:58")
@StaticMetamodel(CarritoCompras.class)
public class CarritoCompras_ { 

    public static volatile CollectionAttribute<CarritoCompras, ElementosCarrito> elementosCarritoCollection;
    public static volatile SingularAttribute<CarritoCompras, Integer> carritoId;
    public static volatile SingularAttribute<CarritoCompras, Usuarios> usuarioId;

}