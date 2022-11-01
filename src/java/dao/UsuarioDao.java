
package dao;

import dto.Usuario;
import java.util.List;

public interface UsuarioDao {
    
    List<Usuario> usuarioSel();
    Usuario usuarioLogin(String usuario, String password);
    Usuario usuarioGet(Integer id);
    Usuario usuarioBuscar(String usuario);
    String usuarioIns(Usuario user);
    String usuarioUpd(Usuario user);
    String usuarioEstado(Integer id, Integer estado);
    String usuarioDel(Integer id);
    String getMessage();
    
}
