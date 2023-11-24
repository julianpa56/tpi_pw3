<%-- 
    Document   : carrito
    Created on : Nov 7, 2023, 10:13:49 PM
    Author     : julia
--%>
<%@page import="entidades.Productos"%>
<%@page import="entidades.ElementosCarrito"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <%@include file="../cabecera.html" %>
    <body>
        <%@ include file="/WEB-INF/navbar.jsp" %>

        <div class="d-flex flex-column justify-content-center align-items-center w-100" style="height:100%;width: 100%;">

            <h2 class="mt-5">Detalles del carrito</h2>

            <div class="mt-5 w-75">
                <c:if test="${empty itemsCarrito}">
                    <h4>No hay productos agregados al carrito</h4>
                </c:if>

                <c:if test="${not empty itemsCarrito}">
                    <div class="row">

                        <div class="col-8 p-2 table-responsive">
                            <table class="table table-striped ">
                                <thead>
                                    <tr>
                                        <th scope="col"></th>
                                        <th scope="col">Imagen</th>
                                        <th scope="col">Código</th>
                                        <th scope="col">Nombre</th>
                                        <th scope="col">Precio</th>
                                        <th scope="col">Cantidad</th>
                                        <th scope="col"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${itemsCarrito}" varStatus="status">
                                        <tr>
                                            <th class="align-middle" scope="row">${status.index +1}</th>
                                            <td class="align-middle">
                                                <div class="rounded" style="width: 75px;height: 75px; overflow: hidden;">
                                                    <img class="w-100 h-100 object-fit-cover" src="${productosCarrito[status.index].getPathImagen()}" alt="alt"/>
                                                </div>
                                            </td>
                                            <td class="align-middle">${productosCarrito[status.index].getProductoId()}</td>
                                            <td class="align-middle">${productosCarrito[status.index].getNombre()}</td>
                                            <td class="align-middle text-nowrap">$ ${productosCarrito[status.index].getPrecio()}</td>
                                            <td class="align-middle">${itemsCarrito[status.index].getCantidad()}</td>
                                            <td class="align-middle">
                                                <a href="eliminar-item?idItem=${itemsCarrito[status.index].getElementoCarritoId()}" class="btn btn-danger">
                                                    <i class="fa-solid fa-trash-can"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>

                        <div class="col-4 p-3">

                            <div class="border border-primary rounded p-3">
                                <h4 class="text-center">Detalles de la compra</h4>
                                <hr>

                                <div class="d-flex justify-content-between">
                                    <p class="fs-5">Total</p>
                                    <p class="fs-5 fw-bold" style="color: green;">$ ${totalCompra}</p>
                                </div>

                                <hr>

                                <div class="d-flex flex-column gap-2">
                                    <a href="terminar-compra?idCarrito=${carritoId}" class="btn btn-primary">
                                        Realizar compra
                                    </a>
                                    <a href="vaciar-carrito?idCarrito=${carritoId}" class="btn btn-outline-primary">
                                        Vaciar carrito
                                    </a>
                                </div>
                            </div>

                        </div>

                    </div>
                </c:if>

            </div>

        </div>
    </body>
</html>
