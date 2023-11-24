<%-- 
    Document   : lista_productos
    Created on : Nov 6, 2023, 8:22:53 PM
    Author     : julia
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <%@ include file="/WEB-INF/cabecera.html" %>
    
    <body>
        
        <%@ include file="/WEB-INF/navbar.jsp" %>
        
        <div class="d-flex flex-column justify-content-center align-items-center w-100" style="height:100%;width: 100%;">
            
            <h2 class="mt-5">Listado de productos</h2>

            <form class="d-flex" role="search" action="buscar-producto">
                <input name="coincidencia" class="form-control me-2" type="search" placeholder="Buscar producto..." aria-label="Search">
                <button class="btn btn-outline-primary" type="submit">Buscar</button>
            </form>

            <div class="mt-5 w-75">
                <div class="row gap-3 justify-content-center">
                    <c:forEach var="producto" items="${productos}">
                        <div class="card col-md-4" style="width: 18rem;">
                            <a class="card-img-top p-0" style="height: 200px;" href="detalle-producto?id=${producto.getProductoId()}">
                                <img src="${producto.getPathImagen()}" class="object-fit-cover w-100 h-100" alt="...">
                            </a>
                            <div class="card-body">
                                <h3 style="color:green;">$ ${producto.getPrecio()}</h3>
                                <h5 class="card-title" style="text-overflow: ellipsis;overflow: hidden; white-space: nowrap;">
                                    ${producto.getNombre()}
                                </h5>
                                <p class="card-text" style="text-overflow: ellipsis;overflow: hidden; white-space: nowrap;">
                                    ${producto.getDescripcion()}
                                </p>
                                <div class="d-flex flex-column gap-3">
                                    <a href="detalle-producto?id=${producto.getProductoId()}" class="btn btn-primary w-100 text-center"><i class="fa-solid fa-circle-info"></i> Ver mas información</a>                                </div>
                                </div>
                        </div>
                    </c:forEach>
                </div>

            </div>

        </div>
    </body>
</html>
