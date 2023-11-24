package entidades;

import entidades.CarritoCompras;
import entidades.Productos;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-11-22T16:41:59")
@StaticMetamodel(ElementosCarrito.class)
public class ElementosCarrito_ { 

    public static volatile SingularAttribute<ElementosCarrito, CarritoCompras> carritoId;
    public static volatile SingularAttribute<ElementosCarrito, Productos> productoId;
    public static volatile SingularAttribute<ElementosCarrito, Integer> cantidad;
    public static volatile SingularAttribute<ElementosCarrito, Integer> elementoCarritoId;

}