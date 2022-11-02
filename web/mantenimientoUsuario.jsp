<%@include file="verificaSesion.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        <%@include file="WEB-INF/jspf/header.jspf" %>
                <main class="mantenimiento">
                    <a href="usuarioINS.jsp" class="btn btn-success">INSERT</a>
                    <form action="Usuario?accion=Metodo" method="POST">
                        <label>Usuario</label>
                        <input type="text" class="form-control" placeholder="Email, Usuario o Codigo" name="txtUsuario" required>
                        <div class="btn-group">
                            <input type="submit" class="btn btn-warning" value="UPDATE" name="txtUpdate">
                            <input type="submit" class="btn btn-danger" value="DELETE" name="txtDelete">
                        </div>
                    </form>
                </main>
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
