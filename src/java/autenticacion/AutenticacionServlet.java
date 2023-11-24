/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package autenticacion;

import entidades.CarritoCompras;
import entidades.Usuarios;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import sesiones.CarritoComprasFacade;
import sesiones.ElementosCarritoFacade;
import sesiones.UsuariosFacade;

/**
 *
 * @author julia
 */
@WebServlet(name = "AutenticacionServlet",
        urlPatterns = {
            "/login",
            "/logout",
            "/registro",
            "/update",
            "/auth",
            "/nuevo-usuario",
            "/mi-perfil",
            "/eliminar-perfil"
        }
)
public class AutenticacionServlet extends HttpServlet {

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
    private UsuariosFacade usuarioF;
    @EJB
    private CarritoComprasFacade carritoCompraF;
    @EJB
    private ElementosCarritoFacade elementoF;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String metodo = request.getMethod();
            String path = request.getServletPath();
            String url = null;
            System.out.println("servlet auth: " + path);
            HttpSession session = request.getSession();

            switch (path) {
                // NO CARGA LA RUTA INICIAL
                case "/login":
                    url = "/WEB-INF/vistas/login.jsp";
                    break;
                
                case "/logout":
                    session.invalidate();                    
                    url = "/login";
                    break;
                
                case "/registro":
                    url = "/WEB-INF/vistas/registro.jsp";
                    break;

                case "/mi-perfil":
                    url = "/WEB-INF/vistas/perfil_usuario.jsp";
                    break;

                case "/nuevo-usuario":

                    Usuarios usuarioCreado = registro(request);

                    List<Usuarios> existente = usuarioF.findByEmail(usuarioCreado.getCorreoElectronico());

                    if (existente.isEmpty()) {
                        usuarioF.create(usuarioCreado);
                        request.setAttribute("exito", "Nuevo usuario creado");
                        crearCarrito(usuarioCreado);
                        url = "/registro";
                    } else {
                        request.setAttribute("error", "Error al crear usuario");
                        url = "/registro";
                    }
                    break;

                case "/auth":
                {
                    Usuarios usuarioEncontrado = login(request);

                    if (usuarioEncontrado != null) {
                        request.setAttribute("usuario", usuarioEncontrado);
                        url = "/lista-productos";
                        CarritoCompras carritoEncontrado = carritoCompraF.findByUser(usuarioEncontrado);
                        session.setAttribute("usuario", usuarioEncontrado);
                        session.setAttribute("carrito", carritoEncontrado);
                        session.setAttribute("itemsCarrito", elementoF.findByCarrito(carritoEncontrado).size());
                    } else {
                        request.setAttribute("error", "Usuario no encontrado");
                        url = "/login";
                    }
                    break;
                }
                case "/update":
                    System.out.print("actualizar usuario...");

                    Usuarios usuarioActualizado = update(request);

                    if (usuarioActualizado == null) {
                        System.out.println("error");
                    } else {
                        System.out.println("actualizado");
                        session.setAttribute("usuario", usuarioActualizado);
                    }
                
                    url = "/mi-perfil";
                    break;
                case "/eliminar-perfil": {
                    Usuarios usuarioActual = (Usuarios) session.getAttribute("usuario");
                    usuarioF.remove(usuarioActual);
                    url = "/logout";
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
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        //request.getRequestDispatcher("/WEB-INF/vistas/mis_productos.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public Usuarios login(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("obtener usuario...");
        List<Usuarios> busqueda = usuarioF.findByEmail(email);
        if (busqueda.isEmpty()) {
            return null;
        }

        Usuarios usuarioObtenido = busqueda.get(0);

        if (usuarioObtenido.getContrasena().equals(password)) {
            return usuarioObtenido;
        } else {
            return null;
        }
    }

    public Usuarios registro(HttpServletRequest request) {
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Usuarios usuario = new Usuarios();
        usuario.setNombre(nombre);
        usuario.setDireccion(direccion);
        usuario.setCorreoElectronico(email);
        usuario.setContrasena(password);

        System.out.println("Nombre: " + nombre);
        System.out.println("Direccion: " + direccion);
        System.out.println("Email: " + email);
        System.out.println("Pass: " + password);
        System.out.println("creando usuario...");
        return usuario;
    }

    public Usuarios update(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Usuarios usuarioActual = (Usuarios) session.getAttribute("usuario");

        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String email = request.getParameter("correo");
        String password = request.getParameter("password");
        String path_image = request.getParameter("path_image");

        Usuarios usuarioUpdate = usuarioActual;
        usuarioUpdate.setNombre(nombre);
        usuarioUpdate.setDireccion(direccion);
        usuarioUpdate.setCorreoElectronico(email);
        usuarioUpdate.setContrasena(password);
        usuarioUpdate.setPathImagen(path_image);

        try {
            usuarioF.edit(usuarioUpdate);
            return usuarioUpdate;
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                System.out.println("-------------------------------------------");
                ConstraintViolationException cve = (ConstraintViolationException) e;
                Set<ConstraintViolation<?>> violations = cve.getConstraintViolations();
                for (ConstraintViolation<?> violation : violations) {
                    System.out.println("Validation error: " + violation.getMessage());
                }
            } else {
                e.printStackTrace();
            }
            return null;
        }

    }
    
    public void crearCarrito(Usuarios usuario) {
        List<Usuarios> busqueda = usuarioF.findByEmail(usuario.getCorreoElectronico());
        Usuarios usuarioObtenido = busqueda.get(0);
        CarritoCompras nuevoCarrito = new CarritoCompras();
        nuevoCarrito.setUsuarioId(usuarioObtenido);
        try {
            carritoCompraF.create(nuevoCarrito);
            System.out.println("carrito creado");
        }
        catch (Exception e) {
            System.out.println("error al crear carrito");
        }
    }
}
