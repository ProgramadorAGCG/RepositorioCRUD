<%@ page import= "dto.Usuario" %>
<%@ page import= "java.util.List" %>
<%@include file="verificaSesion.jsp"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Dashboard - SB Admin</title>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        <%@include file="WEB-INF/jspf/header.jspf" %>
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Dashboard</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Dashboard</li>
                        </ol>
                        <div class="mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                DataTable Example
                            </div>
                            <div class="table-responsive">
                                <table id="datatablesSimple" class="table">
                                    <thead>
                                        <tr>
                                            <th>ItemAi</th>
                                            <th>IdUsuario</th>
                                            <th>CodUsuario</th>
                                            <th>Usuario</th>
                                            <th>Password</th>
                                            <th>Nombres</th>
                                            <th>Apellidos</th>
                                            <th>Email</th>
                                            <th>Permisos</th>
                                            <th>Estado</th>
                                            <th>En linea</th>
                                            <th>Num_Ingresos</th>
                                            <th>Fecha Creación</th>
                                            <th>Fecha_Modificación</th>
                                            <th>Fecha Eliminacion</th>
                                            <th>Fecha Ultimo Acceso</th>
                                            <th>Creado por</th>
                                            <th>Modificado por</th>
                                            <th>Eliminado por</th>
                                            <th>hora_creacion</th>
                                            <th>hora_modificacion</th>
                                            <th>hora_eliminacion</th>
                                            <th>hora_ultimoacceso</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                        List<Usuario> lista = (List<Usuario>)request.getAttribute("arreglo");
                                        for(Usuario user:lista){
                                        %>
                                        <tr>
                                            <td><%=user.getItemAi()%></td>
                                            <td><%=user.getIdUsuario()%></td>
                                            <td><%=user.getCodUsuario()%></td>
                                            <td><%=user.getUsuario()%></td>
                                            <td><%=user.getPassword()%></td>
                                            <td><%=user.getNombres()%></td>
                                            <td><%=user.getApellidos()%></td>
                                            <td><%=user.getEmail()%></td>
                                            <td><%=user.getPermisos()%></td>
                                            <td>
                                                <%
                                                    switch(user.getEstado()){
                                                        case 0: out.print("Desactivo");break;
                                                        case 1: out.print("Activo");break;
                                                        case 2: out.print("Eliminado");break;
                                                        default: out.print("Bloqueado");break;
                                                    }
                                                %>
                                            </td>
                                            <td><%=(user.getEnlinea()==0)?"Sesion cerrada":"Sesion activa"%></td>
                                            <td><%=user.getNum_Ingresos()%></td>
                                            <td><%=user.getFec_Creacion()%></td>
                                            <td><%=user.getFec_Modificacion()%></td>
                                            <td><%=(user.getFec_Eliminacion()==null)?"Sin fecha":user.getFec_Eliminacion()%></td>
                                            <td><%=(user.getFec_UltimoAcceso()==null)?"Sin fecha":user.getFec_UltimoAcceso()%></td>
                                            <td><%=user.getCreado_Por()%></td>
                                            <td><%=user.getModificado_Por()%></td>
                                            <td><%=user.getEliminado_Por()%></td>
                                            <td><%=user.getHora_Creacion()%></td>
                                            <td><%=user.getHora_Modificacion()%></td>1
                                            <td><%=(user.getHora_Eliminacion()==null)?"Sin hora":user.getHora_Eliminacion()%></td>
                                            <td><%=(user.getHora_UltimoAcceso()==null)?"Sin hora":user.getHora_UltimoAcceso()%></td>
                                        </tr>
                                        <%
                                    }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </main>
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
