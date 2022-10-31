
package dao;

import dto.Usuario;
import java.util.List;

public interface UsuarioDao {
    
    List<Usuario> usuarioSel();
    Usuario usuarioLogin(String usuario, String password);
    Usuario usuarioGet(Integer id);
    String usuarioIns(Usuario user);
    String usuarioUpd(Usuario user);
    String usuarioDel(Integer id);
    String getMessage();
    
}
