<%-- 
    Document   : perfil_usuario
    Created on : Nov 10, 2023, 6:05:21 PM
    Author     : julia
--%>
<%@page import="entidades.Usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ include file="/WEB-INF/cabecera.html" %>

    <body>
        <%@ include file="/WEB-INF/navbar.jsp" %>

        <div class="d-flex flex-column justify-content-center align-items-center w-100" style="height:100%;width: 100%;">

            <h2 class="mt-5">Mi perfíl</h2>

            <div class="mt-5 w-75">

                <form action="update" method="POST" class="row">

                    <div class="col-8 ">


                        <div class="border border-primary rounded p-3">
                            <h4 class="text-center">Datos personales</h4>
                            <hr>
                            <div>

                                <div class="mb-3">
                                    <label for="nombre" class="form-label">Nombre y apellido</label>
                                    <input id="nombre" name="nombre" value="<%= ((Usuarios) session.getAttribute("usuario")).getNombre()%>" type="text" class="form-control" placeholder="Nombre" aria-label="Username" aria-describedby="basic-addon1">
                                </div>                      

                                <div class="mb-3">
                                    <label for="direccion" class="form-label">Dirección</label>
                                    <input id="direccion" name="direccion" value="<%= ((Usuarios) session.getAttribute("usuario")).getDireccion()%>" type="text" class="form-control" placeholder="Correo" aria-label="Username" aria-describedby="basic-addon1">
                                </div>

                                <div class="mb-3">
                                    <label for="correo" class="form-label">Correo electrónico</label>
                                    <input id="correo" name="correo" value="<%= ((Usuarios) session.getAttribute("usuario")).getCorreoElectronico()%>" type="email" class="form-control" placeholder="Correo" aria-label="Username" aria-describedby="basic-addon1">
                                </div>

                                <div class="mb-3">
                                    <label for="pass" class="form-label">Contraseña</label>
                                    <input id="pass" name="password" value="<%= ((Usuarios) session.getAttribute("usuario")).getContrasena()%>" type="text" class="form-control" placeholder="Contraseña" aria-label="Username" aria-describedby="basic-addon1">
                                </div>

                            </div>

                            <hr>

                            <div class="d-flex flex-column gap-2">
                                <button type="submit" class="btn btn-success" name="accion" value="actualizar">
                                    Actualizar perfíl
                                </button>
                                <a href="eliminar-perfil" class="btn btn-outline-danger">
                                    Eliminar perfíl
                                </a>
                            </div>
                        </div>



                    </div>

                    <div class="col-4 d-flex gap-2 flex-column justify-content-center">

                        <div class="border rounded p-3" style="width: auto; height: 300px;">
                            <img src="<%= ((Usuarios) session.getAttribute("usuario")).getPathImagen()%>" class="object-fit-cover w-100 h-100" alt="alt"/>
                        </div>

                        <div class="input-group">
                            <span class="input-group-text" id="basic-addon1"><i class="fa-solid fa-image"></i></span>
                            <input value="<%= ((Usuarios) session.getAttribute("usuario")).getPathImagen()%>" type="text" name="path_image" class="form-control" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04" aria-label="Upload">
                        </div>

                    </div>



                </form>

            </div>

        </div>
    </body>
</html>
