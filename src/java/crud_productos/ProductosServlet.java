/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package crud_productos;

import entidades.CarritoCompras;
import entidades.Productos;
import entidades.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sesiones.ProductosFacade;

/**
 *
 * @author julia
 */
@WebServlet(name = "Productos",
        urlPatterns = {
            "/lista-productos",
            "/nuevo-producto",
            "/editar-producto",
            "/actualizar-producto",
            "/eliminar-producto",
            "/mis-productos",
            "/detalle-producto",
            "/buscar-producto",
            "/agregar-compra",
            "/realizar-compra"
        })
public class ProductosServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    private ProductosFacade productoF;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String path = request.getServletPath();
            String url = null;
            System.out.println("servlet productos: " + path);
            HttpSession session = request.getSession();
            Usuarios usuarioLoggeado = (Usuarios) session.getAttribute("usuario");
            CarritoCompras carritoActual = (CarritoCompras) session.getAttribute("carrito");
            System.out.println("-- id c: " + carritoActual.getCarritoId());
            switch (path) {
                case "/lista-productos":
                {
                    List<Productos> productos = obtenerProductos();
                    request.setAttribute("productos", productos);
                    url = "/WEB-INF/vistas/lista_productos.jsp";
                    break;
                }

                case "/nuevo-producto":
                {
                    url = "/WEB-INF/vistas/abm_producto.jsp";
                    break;
                }
                
                case "/editar-producto":
                {
                    Integer productoId;
                    productoId = Integer.valueOf(request.getParameter("id"));
                    System.out.println("buscar producto "+productoId);
                    
                    Productos productoEncontrado = productoF.find( productoId);
                    request.setAttribute("productoEncontrado", productoEncontrado);                    
                    
                    url = "/WEB-INF/vistas/abm_producto.jsp";
                    break;
                }
                
                case "/eliminar-producto":
                {
                    url = "/WEB-INF/vistas/lista_productos.jsp";
                    break;
                }

                case "/actualizar-producto":
                {    
                    String accion = request.getParameter("accion");

                    if (null != accion) switch (accion) {
                        case "crear":
                        {
                            Productos producto = crear(request);
                            if (producto != null) {
                                try {
                                    productoF.create(producto);
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                System.out.println("Producto creado");
                            }
                            else {
                                System.out.println("Error al crear producto");
                            }
                            break;
                        }
                        case "actualizar":
                        {
                            boolean resultado = actualizarProducto(request);
                            if (resultado) {
                                System.out.println("producto actualizado");
                            }
                            else {
                                System.out.println("error al actualizar");
                            }
                            break;
                        }
                        case "eliminar":
                        {                            
                            System.out.println("eliminar producto");
                            boolean resultado = eliminarProducto(request, usuarioLoggeado);
                            
                            if(resultado) {
                                System.out.println("producto eliminado");
                            }
                            else System.out.println("error");
                            break;
                        }
                        default:
                            break;
                    }
                    
                    url = "/lista-productos";
                    break;
                }

                case "/mis-productos":
                {
                    List<Productos> misProductos = buscarProductosUsuario(usuarioLoggeado);
                    
                    request.setAttribute("misProductos", misProductos);
                    url = "/WEB-INF/vistas/mis_productos.jsp";
                    break;
                }
                case "/detalle-producto":
                {
                    Integer productoId;
                    productoId = Integer.valueOf(request.getParameter("id"));
                    System.out.println("buscar producto "+productoId);
                    
                    Productos productoEncontrado = productoF.find( productoId);
                    System.out.println("-- producto: "+ productoEncontrado.getNombre());
                    request.setAttribute("productoEncontrado", productoEncontrado);
                    
                    url = "/WEB-INF/vistas/detalle_producto.jsp";
                    break;
                }
                case "/buscar-producto":
                {
                    String coincidencia = request.getParameter("coincidencia");
                    List<Productos> productos = productoF.findByCoincidence(coincidencia);
                    request.setAttribute("productos", productos);
                    url = "/WEB-INF/vistas/lista_productos.jsp";
                    break;
                }

                case "/agregar-compra":
                    url = "/agregar";
                    break;

                case "/realizar-compra":
                {
                    url = "/comprar";
                    break;
                }

                default:
                    throw new AssertionError();
            }

            try {
                request.getRequestDispatcher(url).forward(request, response);
            } catch (ServletException | IOException ex) {
            }
        } catch (Exception e) {
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public Productos crear(HttpServletRequest request) {

        System.out.println("creando producto...");
        try {
            HttpSession session = request.getSession();
            Usuarios usuario = (Usuarios) session.getAttribute("usuario");

            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            String imagen = request.getParameter("path_imagen");
            float precio = Float.parseFloat(request.getParameter("precio"));
            int stock = Integer.parseInt(request.getParameter("stock"));

            Productos producto = new Productos();
            producto.setUsuarioId(usuario);
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setStock(stock);
            producto.setPathImagen(imagen);

            return producto;
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public List<Productos> obtenerProductos(){
    
        return productoF.findAll();
    }

    
    public List<Productos> buscarProductosUsuario( Usuarios usuario ) {
        
        List<Productos> productos = productoF.findByUser(usuario);
        
        return productos;
    }
    
    public boolean actualizarProducto(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Usuarios usuario = (Usuarios) session.getAttribute("usuario");
        
        Integer productoId = Integer.parseInt(request.getParameter("producto_id"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String imagen = request.getParameter("path_imagen");
        float precio = Float.parseFloat(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        Productos producto = new Productos();
        producto.setProductoId(productoId);
        producto.setUsuarioId(usuario);
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setPathImagen(imagen);
        
        try {
            productoF.edit(producto);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    public boolean eliminarProducto(HttpServletRequest request, Usuarios usuario ) {
        Integer idProducto = Integer.parseInt(request.getParameter("producto_id"));
        Productos producto = productoF.find(idProducto);
        if (producto != null)  {
            if (producto.getUsuarioId().equals(usuario)) {
                try {
                    productoF.remove(producto);
                    return true;
                }
                catch (Exception e) {
                    System.out.println("error al eliminar");
                    return false;
                }
            }
            else {
                System.out.println("no coincide el usuario");
                return false;
            }
        }
        else {
            System.out.println("producto no encontrado");
            return false;
        }
    }
}
