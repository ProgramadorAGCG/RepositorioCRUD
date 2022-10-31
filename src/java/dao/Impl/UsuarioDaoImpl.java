
package dao.Impl;

import dao.UsuarioDao;
import dto.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.ConexionBD;

public class UsuarioDaoImpl implements UsuarioDao{

    private String message;
    private final ConexionBD conexion;
    
    public UsuarioDaoImpl(){
        this.conexion = new ConexionBD();
    }

    @Override
    public List<Usuario> usuarioSel() {
        List<Usuario> listaUsuario = null;
        StringBuilder consultaSQL = new StringBuilder();
        consultaSQL.append("SELECT ")
                .append("ItemAi,")
                .append("IdUsuario,")
                .append("CodUsuario,")
                .append("Usuario,")
                .append("aes_decrypt(Password, 'contraseña'),")
                .append("Nombres,")
                .append("Apellidos,")
                .append("Email,")
                .append("Permisos,")
                .append("Estado,")
                .append("Enlinea,")
                .append("Num_Ingresos,")
                .append("Fec_Creacion,")
                .append("Fec_Modificacion,")
                .append("Fec_Eliminacion,")
                .append("Fec_UltimoAcceso,")
                .append("Creado_Por,")
                .append("Modificado_Por,")
                .append("Eliminado_Por,")
                .append("Hora_Creacion,")
                .append("Hora_Modificacion,")
                .append("Hora_Eliminacion,")
                .append("Hora_UltimoAcceso")
                .append(" FROM usuario");
        try (Connection puente = this.conexion.getConexion()){
            PreparedStatement consultaPreparada = puente.prepareStatement(consultaSQL.toString());
            ResultSet resultado = consultaPreparada.executeQuery();
            listaUsuario = new ArrayList<>();
            while(resultado.next()){
                Usuario  usuario = new Usuario();
                usuario.setItemAi(resultado.getInt(1));
                usuario.setIdUsuario(resultado.getString(2));
                usuario.setCodUsuario(resultado.getString(3));
                usuario.setUsuario(resultado.getString(4));
                usuario.setPassword(resultado.getString(5));
                usuario.setNombres(resultado.getString(6));
                usuario.setApellidos(resultado.getString(7));
                usuario.setEmail(resultado.getString(8));
                usuario.setPermisos(resultado.getString(9));
                usuario.setEstado(resultado.getInt(10));
                usuario.setEnlinea(resultado.getInt(11));
                usuario.setNum_Ingresos(resultado.getInt(12));
                usuario.setFec_Creacion(resultado.getDate(13));
                usuario.setFec_Modificacion(resultado.getDate(14));
                usuario.setFec_Eliminacion(resultado.getDate(15));
                usuario.setFec_UltimoAcceso(resultado.getDate(16));
                usuario.setCreado_Por(resultado.getString(17));
                usuario.setModificado_Por(resultado.getString(18));
                usuario.setEliminado_Por(resultado.getString(19));
                usuario.setHora_Creacion(resultado.getTime(20));
                usuario.setHora_Modificacion(resultado.getTime(21));
                usuario.setHora_Eliminacion(resultado.getTime(22));
                usuario.setHora_UltimoAcceso(resultado.getTime(23));
                listaUsuario.add(usuario);
            }
        } catch (Exception e) {
            this.message = e.getMessage();
        }
        return listaUsuario;
    }
    
    /*
    INSERT INTO `usuario`(`IdUsuario`, `CodUsuario`, `Usuario`, `Password`, `Nombres`, 
    `Apellidos`, `Email`, `Fec_Creacion`, `Fec_Modificacion`, `Hora_Creacion`, 
    `Hora_Modificacion`) VALUES ('100000005', 'Piedad6', 'Piedad', AES_ENCRYPT('Progrtamador','contraseña'), 
    'Piedad', 'Palma', 'PiedadPalma@gmail.com', NOW(), NOW(), NOW(), NOW());
    */
    @Override
    public Usuario usuarioLogin(String usuario, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario usuarioGet(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String usuarioIns(Usuario user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String usuarioUpd(Usuario user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String usuarioDel(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public String getMessage() {
        return this.message;
    }
    
}
