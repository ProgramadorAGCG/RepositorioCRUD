<%@ page session="true" %>
<%
    String varNombresMostrar = "";
    HttpSession sesion = request.getSession();
    Usuario usuario = (Usuario)sesion.getAttribute("usuario");
    if(usuario == null){
    %>
    <jsp:forward page="login.jsp">
        <jsp:param name="error" value="Ingrese sus datos"/>
    </jsp:forward>
    <%
    }
%>