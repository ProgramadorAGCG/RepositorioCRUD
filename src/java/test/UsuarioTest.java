
package test;

import dao.Impl.UsuarioDaoImpl;
import dao.UsuarioDao;
import dto.Usuario;
import java.text.SimpleDateFormat;
import java.util.List;

public class UsuarioTest {
    
    public static void main(String args[]){
        UsuarioDao crudUsuario = new UsuarioDaoImpl();
        Usuario usuario = crudUsuario.usuarioBuscar("User1");
        System.out.println(crudUsuario.getMessage());
        System.out.println(usuario.getItemAi());
    }
    
}
