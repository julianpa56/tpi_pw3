<%@page import="entidades.CarritoCompras"%>
<%@page import="entidades.Usuarios"%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-lg bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Ecommerce</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="lista-productos">Productos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="carrito">Mi carrito <i class="fa-solid fa-cart-shopping"></i> (<%= ((Integer)session.getAttribute("itemsCarrito")) %>)</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <img style="width: 30px; height: 30px;" class="rounded-circle" src="<%= ((Usuarios)session.getAttribute("usuario")).getPathImagen() %>" alt="alt"/>
                        <%= ((Usuarios)session.getAttribute("usuario")).getNombre() %>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <li><a class="dropdown-item" href="mi-perfil">Mi perfíl</a></li>
                        <li><a class="dropdown-item" href="mis-productos">Mis productos</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="logout">Cerrar Sesión</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>