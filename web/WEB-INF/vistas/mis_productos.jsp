<%-- 
    Document   : mis_productos
    Created on : Nov 10, 2023, 6:08:05 PM
    Author     : julia
--%>
<%@page import="entidades.Productos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <%@ include file="/WEB-INF/cabecera.html" %>

    <body>
        <%@ include file="/WEB-INF/navbar.jsp" %>

        <div class="d-flex flex-column justify-content-center align-items-center w-100" style="height:100%;width: 100%;">

            <h2 class="mt-5">Mis productos</h2>
            <div class="container d-flex justify-content-end">
                <a href="nuevo-producto" class="btn btn-primary">Crear nuevo producto</a>
            </div>

            <div class="col-8 p-2 table-responsive">
                
                <c:if test="${empty misProductos}">
                    <div class="mt-5 d-flex align-items-center">
                        <p>Aun no has cargado productos</p>
                    </div>
                </c:if>
                
                <c:if test="${not empty misProductos}">
                    <table class="table table-striped ">
                        <thead>
                            <tr>
                                <th scope="col">Imagen</th>
                                <th scope="col">Código</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Precio</th>
                                <th scope="col">Cantidad</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="producto" items="${misProductos}">
                                <tr>
                                    <td class="align-middle">
                                        <div class="rounded" style="width: 75px;height: 75px; overflow: hidden;">
                                            <img class="w-100 h-100 object-fit-cover" src="${producto.getPathImagen()}" alt="alt"/>
                                        </div>
                                    </td>
                                    <td class="align-middle">${producto.getProductoId()}</td>
                                    <td class="align-middle">${producto.getNombre()}</td>
                                    <td class="align-middle text-nowrap">$ ${producto.getPrecio()}</td>
                                    <td class="align-middle">${producto.getStock()}</td>
                                    <td class="align-middle">
                                        <a href="editar-producto?id=${producto.getProductoId()}" class="btn btn-warning">
                                            <i class="fa-solid fa-pen-to-square"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>


                        </tbody>
                    </table>
                </c:if>
            </div>

        </div>
    </body>
</html>
