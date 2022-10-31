
package test;

import dao.Impl.UsuarioDaoImpl;
import dao.UsuarioDao;
import dto.Usuario;
import java.text.SimpleDateFormat;
import java.util.List;

public class UsuarioTest {
    
    public static void main(String args[]){
        UsuarioDao crudUsuario = new UsuarioDaoImpl();
        
        List<Usuario> listaUsuario = crudUsuario.usuarioSel();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        for (Usuario usuario : listaUsuario) {
            System.out.println(usuario.getHora_Modificacion());
        }
    }
    
}
