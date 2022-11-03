<%@ page session="true" %>
<%
    String varNombresMostrar = "";
    HttpSession sesion = request.getSession();
    Usuario usuario = (sesion.getAttribute("usuario") != null) ? (Usuario)sesion.getAttribute("usuario"):null;
    if(usuario == null){
    %>
    <jsp:forward page="login.jsp">
        <jsp:param name="error" value="Ingrese sus datos"/>
    </jsp:forward>
    <%
    }
%>