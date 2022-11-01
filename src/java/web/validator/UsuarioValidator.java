package web.validator;

import dao.Impl.UsuarioDaoImpl;
import dao.UsuarioDao;
import dto.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

public class UsuarioValidator {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private final UsuarioDao usuarioCrud;

    public UsuarioValidator(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.usuarioCrud = new UsuarioDaoImpl();
    }

    public String usuarioSel() {
        String result = null;
        List<Usuario> listaUsuario = this.usuarioCrud.usuarioSel();
        if (listaUsuario != null) {
            request.setAttribute("arreglo", listaUsuario);
        } else {
            result = this.usuarioCrud.getMessage();
        }
        return result;
    }

    public String usuarioLogin() {
        String usuario = request.getParameter("txtUsuario");
        String password = request.getParameter("txtPassword");
        Usuario user = usuarioCrud.usuarioBuscar(usuario);
        String mensaje = null;
        if (user != null) {
            user = usuarioCrud.usuarioLogin(usuario, password);
            if (user == null) {
                mensaje = usuarioCrud.getMessage();
            } else {
                HttpSession sesion = request.getSession();
                sesion.setAttribute("idUsuario", user.getItemAi());
                mensaje = usuarioCrud.usuarioEstado(user.getItemAi(), 1);
            }
        } else {
            mensaje = usuarioCrud.getMessage();
        }
        return mensaje;
    }

    public void usuarioCerrar() {
        HttpSession sesion = request.getSession();
        Integer id = (Integer) sesion.getAttribute("idUsuario");
        usuarioCrud.usuarioEstado(id, 0);
    }

}
