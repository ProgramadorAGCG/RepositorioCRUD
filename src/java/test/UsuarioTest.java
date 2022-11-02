
package test;

import dao.Impl.UsuarioDaoImpl;
import dao.UsuarioDao;
import dto.Usuario;
import java.text.SimpleDateFormat;
import java.util.List;

public class UsuarioTest {
    
    public static void main(String args[]){
        UsuarioDao crudUsuario = new UsuarioDaoImpl();
        Usuario usuario = new Usuario();
        usuario.setIdUsuario("00000123");
        usuario.setCodUsuario("holaa112");
        usuario.setUsuario("hola");
        usuario.setPassword("hola");
        usuario.setNombres("hola");
        usuario.setApellidos("mundo");
        usuario.setEmail("holamundo@gmail.com");
        System.out.println(crudUsuario.usuarioIns(usuario));
    }
    
}
