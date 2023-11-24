<%-- 
    Document   : detalle_producto
    Created on : Nov 8, 2023, 1:16:59 AM
    Author     : julia
--%>
<%@page import="entidades.Productos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <%@include file="../cabecera.html" %>
    <body>
        <%@ include file="/WEB-INF/navbar.jsp" %>

        <div class="d-flex flex-column justify-content-center align-items-center w-100" style="height:100%;width: 100%;">
            
            <c:if test="${not empty productoAgregado}">
                <div class="mt-5 w-50">
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        ${productoAgregado}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </div>
            </c:if>
            <c:if test="${not empty productoNoAgregado}">
                <div class="mt-5 w-50">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        ${productoNoAgregado}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </div>
            </c:if>
            <h2 class="mt-5">Detalles del producto</h2>

            <div class="mt-5 w-75">

                <div class="row">
                    <c:set var="producto" value="${productoEncontrado}" />

                    <div class="col-8 p-2 d-flex justify-content-center">
                        <div class="border rounded p-3" style="width: 500px; height: 500px;">
                            <img class="object-fit-cover w-100 h-100" src="${producto.getPathImagen()}" alt="alt"/>
                        </div>

                    </div>

                    <div class="col-4 p-3">

                        <div class="border border-primary rounded p-3">
                            <h4 class="text-center">${producto.getNombre()}</h4>
                            <hr>

                            <div>
                                <p class="fs-3 fw-bold" style="color: green;">$ 123123</p>
                            </div>

                            <div>
                                <p class="fs-5">${producto.getDescripcion()}</p>
                            </div>

                            <div class="d-flex justify-content-between">
                                <p>Unidades disponibles</p>
                                <p class="fw-bold">${producto.getStock()}</p>
                            </div>
                            <form id="compra" method="POST">
                                <input type="hidden" name="producto_id" value="${producto.getProductoId()}">
                                <div class="d-flex justify-content-between align-items-center">
                                    <p class="m-0">Cantidad</p>
                                    <div class="d-flex align-items-center">
                                        <button type="button" class="btn btn-primary" onclick="decrementarCantidad(event)">-</button>
                                        <p id="cantidad" class="my-0 mx-2 py-2 text-center border rounded" style="width: 50px; max-width: 50px;">1</p>
                                        <button type="button" class="btn btn-primary" onclick="incrementarCantidad(event)">+</button>
                                    </div>
                                </div>

                                <hr>

                                <div class="d-flex flex-column gap-2">
                                    <button type="button" class="btn btn-primary" onclick="comprar(event)">
                                        Comprar
                                    </button>
                                    <button type="button" class="btn btn-outline-primary" onclick="agregarAlCarrito(event)">
                                        Agregar al carrito
                                    </button>
                                </div>
                            </form>
                        </div>

                    </div>

                </div>

            </div>

        </div>
    </body>
    <script>
        var cantidadElement = document.getElementById("cantidad");
        var stock = ${producto.getStock()}; // Aquí debes obtener el valor de stock desde tu servidor

        function decrementarCantidad(event) {
            var cantidad = parseInt(cantidadElement.innerText);
            if (cantidad > 1) {
                cantidad--;
                cantidadElement.innerText = cantidad;
            }
            event.preventDefault();
        }

        function incrementarCantidad(event) {
            var cantidad = parseInt(cantidadElement.innerText);
            if (cantidad < stock) {
                cantidad++;
                cantidadElement.innerText = cantidad;
            }
            event.preventDefault();
        }

        function comprar(event) {            
            var formulario = document.getElementById("compra");
            var cantidad = parseInt(cantidadElement.innerText);
            var cantidadInput = document.createElement("input");
            cantidadInput.type = "hidden";
            cantidadInput.name = "cantidad";
            cantidadInput.value = cantidad;
            
            formulario.action = "realizar-compra";
            formulario.appendChild(cantidadInput);
            formulario.submit();

            event.preventDefault();
        }

        function agregarAlCarrito(event) {
            var formulario = document.getElementById("compra");
            var cantidad = parseInt(cantidadElement.innerText);
            var cantidadInput = document.createElement("input");
            cantidadInput.type = "hidden";
            cantidadInput.name = "cantidad";
            cantidadInput.value = cantidad;
            
            formulario.action = "agregar-compra";
            formulario.appendChild(cantidadInput);
            formulario.submit();

            event.preventDefault();
        }
    </script>
</html>
