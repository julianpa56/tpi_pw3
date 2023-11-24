<%-- 
    Document   : login
    Created on : Nov 6, 2023, 6:17:16 PM
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
                <h1 class="text-center">Iniciar Sesión</h1>
                <br>
                <form action="auth" method="POST">
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Corréo</label>
                        <input name="email" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">Contraseña</label>
                        <input name="password" type="password" class="form-control" id="exampleInputPassword1">
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Iniciar Sesión</button>
                </form>
                <c:set var="errorMessage" value="${requestScope.error}" />
                <c:if var="error" test="${not empty errorMessage}" >
                    <div class="alert alert-danger mt-2" role="alert">
                        ${errorMessage}
                    </div>
                </c:if>
                <hr>
                <p class="text-center">No tienes una cuenta? <a href="registro">Registrate</a></p>
            </div>

    </body>
</html>
