<%-- 
    Document   : amb_producto
    Created on : Nov 10, 2023, 5:20:25 PM
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

            <c:if test="${empty productoEncontrado}">
                <h2 class="mt-5">Nuevo producto</h2>
            </c:if>

            <c:if test="${not empty productoEncontrado}">
                <h2 class="mt-5">Editar producto</h2>
            </c:if>


            <div class="mt-5 w-75">

                <form class="row" method="POST" action="actualizar-producto" id="miFormulario">

                    <div class="col-8 ">

                        <div class="border border-primary rounded p-3">
                            <h4 class="text-center">Datos del producto</h4>
                            <hr>
                            <div>
                                <c:if test="${not empty productoEncontrado}">
                                    <input type="hidden" name="producto_id" value="${productoEncontrado.getProductoId()}">
                                    <input type="hidden" name="usuario_id" value="${productoEncontrado.getUsuarioId()}">
                                </c:if>
                                <div class="input-group mb-3">
                                    <input value="${productoEncontrado.getNombre()}" name="nombre" type="text" class="form-control" placeholder="Nombre del producto" aria-label="Username" aria-describedby="basic-addon1">
                                </div>

                                <div class="d-flex gap-2">
                                    <div class="input-group mb-3">
                                        <span class="input-group-text">$</span>
                                        <input value="${productoEncontrado.getPrecio()}" name="precio" type="number" class="form-control">
                                    </div>

                                    <div class="input-group mb-3">
                                        <input value="${productoEncontrado.getStock()}" name="stock" type="number" class="form-control" placeholder="Cantidad de unidades">
                                    </div>
                                </div>

                                <div class="input-group">
                                    <textarea name="descripcion" class="form-control" aria-label="With textarea" placeholder="Descripción del producto">${productoEncontrado.getDescripcion()}</textarea>
                                </div>

                            </div>

                            <hr>

                            <div class="d-flex flex-column gap-2">
                                
                                <c:if test="${empty productoEncontrado}">
                                    <button type="button" class="btn btn-primary" onclick="submitForm('crear')">
                                        Crear producto
                                    </button>
                                </c:if>
                                
                                <c:if test="${not empty productoEncontrado}">
                                    <button type="button" class="btn btn-success" onclick="submitForm('actualizar')">
                                        Actualizar producto
                                    </button>

                                    <button type="button" class="btn btn-outline-danger" onclick="submitForm('eliminar')">
                                        Eliminar producto
                                    </button>
                                </c:if>

                            </div>
                        </div>


                    </div>

                    <div class="col-4 d-flex gap-2 flex-column justify-content-center">

                        <div class="border rounded p-3 bg-primary" style="width: auto; height: 300px; overflow: hidden;">
                            <img class="w-100 h-100 object-fit-cover" src="${productoEncontrado.getPathImagen()}" alt="alt"/>
                        </div>

                        <div class="input-group">
                            <span class="input-group-text" id="basic-addon1"><i class="fa-solid fa-image"></i></span>
                            <input value="${productoEncontrado.getPathImagen()}" name="path_imagen" type="text" class="form-control" placeholder="Ej: https;//www.imagen.com..." aria-label="Username" aria-describedby="basic-addon1">
                        </div>

                    </div>


                </form>

                <script>
                    function submitForm(accion) {
                        
                        var formulario = document.getElementById("miFormulario");
                        var accionInput = document.createElement("input");
                        accionInput.type = "hidden";
                        accionInput.name = "accion";
                        accionInput.value = accion;

                        formulario.appendChild(accionInput);

                        formulario.submit();
                    }
                </script>

            </div>

        </div>
    </body>
</html>
