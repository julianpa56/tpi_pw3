package entidades;

import entidades.ElementosCarrito;
import entidades.Usuarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-11-22T16:41:58")
@StaticMetamodel(Productos.class)
public class Productos_ { 

    public static volatile SingularAttribute<Productos, String> descripcion;
    public static volatile CollectionAttribute<Productos, ElementosCarrito> elementosCarritoCollection;
    public static volatile SingularAttribute<Productos, Double> precio;
    public static volatile SingularAttribute<Productos, Integer> productoId;
    public static volatile SingularAttribute<Productos, String> pathImagen;
    public static volatile SingularAttribute<Productos, Integer> stock;
    public static volatile SingularAttribute<Productos, String> nombre;
    public static volatile SingularAttribute<Productos, Usuarios> usuarioId;

}