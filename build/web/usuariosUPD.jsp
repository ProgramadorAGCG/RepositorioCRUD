<%@ page import= "dto.Usuario" %>
<%@include file="verificaSesion.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Register - SB Admin</title>
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-7">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <form action="Usuario" method="POST" > 
                                        <div class="card-header"><h3 class="text-center font-weight-light my-4">Actualizar usuario</h3></div>

                                        <div class="card-body">
                                             <%
                                             Usuario user = (Usuario)request.getAttribute("user");
                                             %>
                                            <br/>
                                            <input type="hidden" name="accion" value="UPD"/>
                                            <input type="hidden" name="itemAI" value="<%=user.getItemAi()%>"/>
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputFirstName" value="<%=user.getIdUsuario()%>" name="idUsuario" type="number" placeholder="Enter your first name" />
                                                        <label for="inputFirstName">ID Usuario</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-floating">
                                                        <input class="form-control" id="inputLastName" value="<%=user.getCodUsuario()%>" name="codUsuario" type="text" placeholder="Enter your last name" />
                                                        <label for="inputLastName">Cod Usuario</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="inputEmail" type="text" name="usuario" placeholder="Usuario" value="<%=user.getUsuario()%>"/>
                                                <label for="inputEmail">Usuario</label>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputPassword" name="password" type="password" placeholder="Password" value="<%=user.getPassword()%>"/>
                                                        <label for="inputPassword">Password</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputPasswordConfirm" name="nombres" type="text" placeholder="Ingresa nombre" value="<%=user.getNombres()%>"/>
                                                        <label for="inputPasswordConfirm">Nombres</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-floating mb-3 mb-md-0">
                                                    <input class="form-control" id="inputPasswordConfirm" name="apellidos" type="text" placeholder="Ingrese Apellido" value="<%=user.getApellidos()%>"/>
                                                    <label for="inputPasswordConfirm">Apellidos</label>
                                                </div>
                                            </div>
                                            <br>

                                            <div class="col-md-6">
                                                <div class="form-floating mb-3 mb-md-0">
                                                    <input class="form-control" id="inputPasswordConfirm" type="email" name="email" placeholder="Correo Electronico" value="<%=user.getEmail()%>"/>
                                                    <label for="inputPasswordConfirm">Correo Electronico</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-footer text-center py-3">
                                            <div class="small"><input type="submit" value="Actualizar" style="background-color: green; color:white;"></div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
            <%
            String message = (String)request.getAttribute("message");
            if(message != null){
                %>
                <div><%=message%></div>
                <%
            }
            %>
            <div id="layoutAuthentication_footer">
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2022</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>
