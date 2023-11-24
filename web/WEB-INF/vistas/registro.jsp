<%-- 
    Document   : registro
    Created on : Nov 6, 2023, 6:50:38 PM
    Author     : julia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <%@ include file="/WEB-INF/cabecera.html" %>
    
    <body>
        <div class="d-flex justify-content-center align-items-center" style="height:100vh;">

            <div class="w-25">
                <h1 class="text-center">Registrarse</h1>
                <br>
                <form action="nuevo-usuario" method="POST">
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Nombre y apellido</label>
                        <input type="text" class="form-control" id="exampleInputEmail1" name="nombre" aria-describedby="emailHelp">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Dirección</label>
                        <input type="text" class="form-control" id="exampleInputEmail1" name="direccion" aria-describedby="emailHelp">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Corréo</label>
                        <input type="email" class="form-control" id="exampleInputEmail1" name="email" aria-describedby="emailHelp">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">Contraseña</label>
                        <input type="password" class="form-control" id="exampleInputPassword1" name="password">
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Registrarse</button>
                </form>
                <c:set var="errorMessage" value="${requestScope.error}" />
                <c:if test="${not empty errorMessage}" >
                    <div class="alert alert-danger mt-2" role="alert">
                        ${errorMessage}
                    </div>
                </c:if>
                <c:set var="successMessage" value="${requestScope.exito}" />
                <c:if test="${not empty successMessage}" >
                    <div class="alert alert-success mt-2" role="alert">
                        ${successMessage}
                    </div>
                </c:if>
                <hr>
                <p class="text-center">Ya tienes una cuenta? <a href="logout">Iniciar sesión</a></p>
            </div>

        </div>
    </body>
</html>
