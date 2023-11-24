package entidades;

import entidades.CarritoCompras;
import entidades.Productos;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-11-22T16:41:59")
@StaticMetamodel(Usuarios.class)
public class Usuarios_ { 

    public static volatile CollectionAttribute<Usuarios, CarritoCompras> carritoComprasCollection;
    public static volatile SingularAttribute<Usuarios, String> pathImagen;
    public static volatile SingularAttribute<Usuarios, String> direccion;
    public static volatile CollectionAttribute<Usuarios, Productos> productosCollection;
    public static volatile SingularAttribute<Usuarios, String> contrasena;
    public static volatile SingularAttribute<Usuarios, String> nombre;
    public static volatile SingularAttribute<Usuarios, String> correoElectronico;
    public static volatile SingularAttribute<Usuarios, Integer> usuarioId;

}