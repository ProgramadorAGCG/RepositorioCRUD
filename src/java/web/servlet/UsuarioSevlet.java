
package web.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.validator.UsuarioValidator;

@WebServlet(name = "UsuarioSevlet", urlPatterns = {"/Usuario"})
public class UsuarioSevlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");
        String result=null;
        String mensajeError = null;
        String target = "login.jsp";
        UsuarioValidator validator = new UsuarioValidator(request, response);
        switch (accion) {
            case "SEL":
                mensajeError = validator.usuarioSel();
                target = "index.jsp";
                break;
            case "INS":
                result = validator.usuariosInsUpd(false);
                target = result == null ? "usuarios.jsp" : "usuariosINS.jsp";
                break;
            case "INSCLI":
                result = validator.usuariosInsUpd(false);
                target = "Usuario?accion=SEL";
                break;
            case "GET":
                result = validator.usuariosGet();
                target = "usuariosUPD.jsp";
                break;
            case "UPD":
                result = validator.usuariosInsUpd(true);
                target = result == null ? "Usuario?accion=SEL" : "usuariosUPD.jsp";
                break;
            case "LOG":
                mensajeError = validator.usuarioLogin();
                target = (mensajeError == null)?"Usuario?accion=SEL":"login.jsp";
                break;
            case "OUT":
                validator.usuarioCerrar();
                target = "sesionCerrar.jsp";
                break;
            case "Metodo":
                String btnUpdate = request.getParameter("txtUpdate");
                String btnDelete = request.getParameter("txtDelete");
                String usuario = request.getParameter("txtUsuario");
                if(btnUpdate != null){
                    target = "Usuario?accion=GET";
                    request.setAttribute("txtUsuario", usuario);
                }else{
                    mensajeError = validator.usuarioDel(usuario);
                    target = "Usuario?accion=SEL";
                }
                
            default:
                mensajeError = "Solicitud no reconocida";
        }
        if (mensajeError != null) 
            request.setAttribute("message", mensajeError);
        request.getRequestDispatcher(target).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
