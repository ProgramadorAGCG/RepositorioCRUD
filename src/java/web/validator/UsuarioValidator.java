package web.validator;

import dao.Impl.UsuarioDaoImpl;
import dao.UsuarioDao;
import dto.Usuario;
import jakarta.servlet.http.Cookie;
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
        Usuario user1 = usuarioCrud.usuarioBuscar(usuario);
        String mensaje = null;
        if (user1 != null) {
            Usuario user2 = usuarioCrud.usuarioLogin(usuario, password);
            if (user2 == null) {
                mensaje = usuarioCrud.getMessage();
                corroborarConteoCookie(user1);
            } else {
                HttpSession sesion = request.getSession();
                sesion.setAttribute("usuario", user2);
                mensaje = usuarioCrud.usuarioEnLinea(user2.getItemAi(), 1);
                Cookie cookie = recuperarCookie(String.valueOf(user2.getItemAi()));
                if(cookie != null){
                    borrarCookie(cookie);
                }
            }
        } else {
            mensaje = usuarioCrud.getMessage();
        }
        return mensaje;
    }

    public void corroborarConteoCookie(Usuario user) {
        Cookie cookie = recuperarCookie(String.valueOf(user.getItemAi()));
        int numero = 1;
        if (cookie == null) {
            cookie = new Cookie(String.valueOf(user.getItemAi()), String.valueOf(1));
            response.addCookie(cookie);
        } else {
            numero = Integer.parseInt(cookie.getValue()) + 1;
            if (numero == 3) {
                borrarCookie(cookie);
                usuarioCrud.usuarioEstado(user.getItemAi(), 3);
            } else {
                cookie.setValue(String.valueOf(numero));
                response.addCookie(cookie);
            }
        }
        request.setAttribute("contador", numero);
    }

    public void borrarCookie(Cookie cookie) {
        cookie = new Cookie(cookie.getName(), "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public void usuarioCerrar() {
        HttpSession sesion = request.getSession();
        Integer id = (Integer) sesion.getAttribute("idUsuario");
        usuarioCrud.usuarioEnLinea(id, 0);
    }

    public String usuariosInsUpd(boolean upd) {
        StringBuilder result = new StringBuilder("<ul>");

        Integer itemAI = (request.getParameter("itemAI") == null)
                ? null
                : Integer.valueOf(request.getParameter("itemAI"));
        String idUsuario = request.getParameter("idUsuario");
        String codUsuario = request.getParameter("codUsuario");
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");

        Usuario user = new Usuario();
        user.setIdUsuario(idUsuario);
        user.setCodUsuario(codUsuario);
        user.setUsuario(usuario);
        user.setPassword(password);
        user.setNombres(nombres);
        user.setApellidos(apellidos);
        user.setEmail(email);

        user.setItemAi(itemAI);

        if (result.length() == 4) {
            String msg = upd
                    ? usuarioCrud.usuarioUpd(user)
                    : usuarioCrud.usuarioIns(user);
            if (msg != null) {
                result.append("<li>").append(msg).append("</li>");
            }
        }
        if (result.length() > 4) {
            request.setAttribute("user", user);
        }
        return result.length() == 4 ? null : result.append("</ul>").toString();
    }

    /*LEER*/
    public String usuariosGet() {
        String result = null;
        String usuario = (String) request.getAttribute("txtUsuario");
        dto.Usuario user = usuarioCrud.usuarioBuscar(usuario);
        if (user != null) {
            request.setAttribute("user", user);
        } else {
            result = usuarioCrud.getMessage();
        }
        return result;
    }

    public Cookie recuperarCookie(String name) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }
        return null;
    }

    public String usuarioDel(String usuario) {
        Usuario user = this.usuarioCrud.usuarioBuscar(usuario);
        return this.usuarioCrud.usuarioDel(user.getItemAi());
    }

}
