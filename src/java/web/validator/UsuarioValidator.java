
package web.validator;

import dao.Impl.UsuarioDaoImpl;
import dao.UsuarioDao;
import dto.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    
    public String usuarioSel(){
        String result = null;
        List<Usuario> listaUsuario = this.usuarioCrud.usuarioSel();
        if(listaUsuario != null) request.setAttribute("arreglo", listaUsuario);
        else result = this.usuarioCrud.getMessage();
        return result;
    }
    
}
