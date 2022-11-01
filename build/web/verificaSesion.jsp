<%@ page session="true" %>
<%
    String varNombresMostrar = "";
    HttpSession sesion = request.getSession();
    if(sesion.getAttribute("idUsuario") == null){
    %>
    <jsp:forward page="login.jsp">
        <jsp:param name="error" value="Ingrese sus datos"/>
    </jsp:forward>
    <%
    }
%>