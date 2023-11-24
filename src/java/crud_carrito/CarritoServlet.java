/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package crud_carrito;

import entidades.Usuarios;
import entidades.CarritoCompras;
import entidades.Productos;
import entidades.ElementosCarrito;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sesiones.CarritoComprasFacade;
import sesiones.ElementosCarritoFacade;
import sesiones.ProductosFacade;
import java.util.List;

/**
 *
 * @author julia
 */
@WebServlet(name = "CarritoServlet",
        urlPatterns = {
            "/comprar",
            "/agregar",
            "/carrito",
            "/terminar-compra",
            "/vaciar-carrito",
            "/compra-realizada",
            "/eliminar-item"
        })
public class CarritoServlet extends HttpServlet {

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
    private CarritoComprasFacade carritoCompraF;
    @EJB
    private ProductosFacade productoF;
    @EJB
    private ElementosCarritoFacade elementoF;

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

            switch (path) {
                case "/comprar": {
                    String idProducto = request.getParameter("producto_id");
                    String cantidad = request.getParameter("cantidad");
                    agregarAlCarrito(request, carritoActual);
                    session.setAttribute("itemsCarrito", elementoF.findByCarrito(carritoActual).size());

                    url = "/carrito";
                    break;
                }
                
                case "/compra-realizada" : {
                    url = "/WEB-INF/vistas/compra_realizada.jsp";
                    break;
                }
                
                case "/agregar": {
                    String idProducto = request.getParameter("producto_id");
                    String cantidad = request.getParameter("cantidad");
                    try {
                        agregarAlCarrito(request, carritoActual);
                        session.setAttribute("itemsCarrito", elementoF.findByCarrito(carritoActual).size());
                        request.setAttribute("productoAgregado", "Producto agregado con exito");
                    }
                    catch (Exception e) {
                        request.setAttribute("productoNoAgregado", "Error al agregar producto");
                    }

                    url = "/detalle-producto?id="+idProducto;
                    break;
                }
                
                case "/carrito": {
                    System.out.println("--- 1");
                    List<ElementosCarrito> itemsCarrito = elementoF.findByCarrito(carritoActual);
                    System.out.println("--- 2");
                    List<Productos> productosCarrito = obtenerElementos(itemsCarrito);
                    System.out.println("--- 3");
                    double totalCompra = calcularTotal(itemsCarrito, productosCarrito);

                    request.setAttribute("carritoId", carritoActual.getCarritoId());
                    request.setAttribute("itemsCarrito", itemsCarrito);
                    request.setAttribute("productosCarrito", productosCarrito);
                    request.setAttribute("totalCompra", totalCompra);

                    url = "/WEB-INF/vistas/carrito.jsp";
                    break;
                }
                
                case "/terminar-compra": {
                    Integer idCarrito = Integer.parseInt(request.getParameter("idCarrito"));
                    CarritoCompras nuevo = realizarCompra(usuarioLoggeado,idCarrito);
                    if(nuevo != null) {
                        System.out.println("nuevo carrito " + nuevo.getCarritoId());
                        session.setAttribute("carrito", nuevo);
                        session.setAttribute("itemsCarrito", elementoF.findByCarrito(carritoActual).size());
                    }
                    url = "/compra-realizada";
                    break;
                }
                
                case "/vaciar-carrito": {
                    Integer idCarrito = Integer.parseInt(request.getParameter("idCarrito"));
                    vaciarCarrito(carritoActual);
                    session.setAttribute("itemsCarrito", elementoF.findByCarrito(carritoActual).size());
                    url = "/carrito";
                    break;
                }
                
                case "/eliminar-item": {
                    Integer idItem = Integer.parseInt(request.getParameter("idItem"));
                    ElementosCarrito item = elementoF.find(idItem);
                    elementoF.remove(item);
                    session.setAttribute("itemsCarrito", elementoF.findByCarrito(carritoActual).size());
                    url = "/carrito";
                    break;
                }
                
                default:
                    throw new AssertionError();
            }

            try {
                request.getRequestDispatcher(url).forward(request, response);
            } catch (ServletException | IOException ex) {
                System.out.println("error 1");
            }
        } catch (Exception e) {
            System.out.println("error 2");
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void agregarAlCarrito(HttpServletRequest request, CarritoCompras carrito) {
        Integer idProducto = Integer.parseInt(request.getParameter("producto_id"));
        Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));

        try {
            Productos producto = productoF.find(idProducto);
            ElementosCarrito nuevoElemento = new ElementosCarrito();
            nuevoElemento.setProductoId(producto);
            nuevoElemento.setCantidad(cantidad);
            nuevoElemento.setCarritoId(carrito);
            elementoF.create(nuevoElemento);
            System.out.println("Producto agregado al carrito");
        } catch (Exception e) {
            System.out.println("Error al agregar producto");
        }

    }

    public List<Productos> obtenerElementos(List<ElementosCarrito> itemsCarrito) {
        List<Productos> elementos = new ArrayList<Productos>();
        try {
            itemsCarrito.forEach((item) -> {
                Productos producto = productoF.find(item.getProductoId().getProductoId());
                elementos.add(producto);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementos;
    }

    public double calcularTotal(List<ElementosCarrito> items, List<Productos> productos) {
        double total = 0;

        for (int i = 0; i < items.size(); i++) {
            total += items.get(i).getCantidad() * productos.get(i).getPrecio();
        }

        return total;
    }
    
    public CarritoCompras realizarCompra(Usuarios usuario,Integer idCarrito) {
        CarritoCompras nuevoCarrito = new CarritoCompras();
        nuevoCarrito.setUsuarioId(usuario);
        
        try {
            carritoCompraF.create(nuevoCarrito);
            CarritoCompras carritoViejo = carritoCompraF.find(idCarrito);
            carritoCompraF.remove(carritoViejo);
            CarritoCompras nuevo = carritoCompraF.findByUser(usuario);
            System.out.println("Compra realizada");
            return nuevo;
        }
        catch (Exception e) {
            System.out.println("error al realizar compra");
            return null;
        }
    }
    
    public void vaciarCarrito(CarritoCompras carritoActual){
        List<ElementosCarrito> items = elementoF.findByCarrito(carritoActual);
        
        items.forEach((item) -> {
            elementoF.remove(item);
        });
    }
}
