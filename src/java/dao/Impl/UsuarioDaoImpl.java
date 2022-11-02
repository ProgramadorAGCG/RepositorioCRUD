package dao.Impl;

import dao.UsuarioDao;
import dto.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.ConexionBD;

public class UsuarioDaoImpl implements UsuarioDao {

    private String message;
    private final ConexionBD conexion;

    public UsuarioDaoImpl() {
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
        try ( Connection puente = this.conexion.getConexion()) {
            PreparedStatement consultaPreparada = puente.prepareStatement(consultaSQL.toString());
            ResultSet resultado = consultaPreparada.executeQuery();
            listaUsuario = new ArrayList<>();
            while (resultado.next()) {
                Usuario usuario = new Usuario();
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
    /*
    Loguear usuarios
    */
    @Override
    public Usuario usuarioLogin(String usuario, String password) {
        Usuario user = null;
        StringBuilder consultaSQL = new StringBuilder();
        consultaSQL.append("SELECT ")
                .append("ItemAi,")
                .append("IdUsuario,")
                .append("CodUsuario,")
                .append("Usuario,")
                .append("Nombres,")
                .append("Apellidos,")
                .append("Permisos")
                .append(" FROM usuario WHERE ")
                .append("(CodUsuario = ? or Email = ? or Usuario = ?) ")
                .append("and (Password = AES_ENCRYPT(?,'contraseña')) and Estado = 1");
        try ( Connection puente = this.conexion.getConexion()) {
            PreparedStatement consultaPreparada = puente.prepareStatement(consultaSQL.toString());
            consultaPreparada.setString(1, usuario);
            consultaPreparada.setString(2, usuario);
            consultaPreparada.setString(3, usuario);
            consultaPreparada.setString(4, password);
            ResultSet resultado = consultaPreparada.executeQuery();
            if(resultado.next()){
                user = new Usuario();
                user.setItemAi(resultado.getInt(1));
                user.setIdUsuario(resultado.getString(2));
                user.setCodUsuario(resultado.getString(3));
                user.setUsuario(resultado.getString(4));
                user.setNombres(resultado.getString(5));
                user.setApellidos(resultado.getString(6));
                user.setPermisos(resultado.getString(7));
            }else{
                this.message = "La contraseña de este usuario es incorrecta";
            }
        } catch (Exception e) {
            this.message = e.getMessage();
        }
        return user;
    }

    @Override
    public String usuarioIns(Usuario user) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO usuario( ")
                .append("IdUsuario,")
                .append("CodUsuario,")
                .append("Usuario,")
                .append("Password,")
                .append("Nombres,")
                .append("Apellidos,")
                .append("Email,")
                .append("Fec_Creacion,")
                .append("Fec_Modificacion,")
                .append("Hora_Creacion,")
                .append("Hora_Modificacion")
                .append(") VALUES (?,?,?,AES_ENCRYPT(?,'contraseña'),?,?,?,NOW(),NOW(),NOW(),NOW())");

        try (Connection cn = this.conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, user.getIdUsuario());
            ps.setString(2, user.getCodUsuario());
            ps.setString(3, user.getUsuario());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getNombres());
            ps.setString(6, user.getApellidos());
            ps.setString(7, user.getEmail());

            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                message = "Cero filas insertadas";
            }
        } catch (Exception e) {
            this.message = e.getMessage();
        }
        return message;
    }

    @Override
    public String usuarioUpd(Usuario user) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE usuario SET ")
                .append("IdUsuario=?,")
                .append("CodUsuario=?,")
                .append("Usuario=?,")
                .append("Password=AES_ENCRYPT(?,'contraseña'),")
                .append("Nombres=?,")
                .append("Apellidos=?,")
                .append("Email=? ")
                .append("WHERE ItemAi = ? ");

        try (Connection cn = this.conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setString(1, user.getIdUsuario());
            ps.setString(2, user.getCodUsuario());
            ps.setString(3, user.getUsuario());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getNombres());
            ps.setString(6, user.getApellidos());
            ps.setString(7, user.getEmail());
            ps.setInt(8, user.getItemAi());
            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                message = "Cero filas insertadas";
            }
        } catch (Exception e) {
            this.message = e.getMessage();
        }
        return message;
    }

    @Override
    public String usuarioDel(Integer id) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM usuario WHERE ")
                .append("ItemAi = ? ");
        try (Connection cn = conexion.getConexion()) {
            PreparedStatement ps = cn.prepareStatement(sql.toString());
            ps.setInt(1, id);
            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                message = "Cero filas actualizadas";
            }
        } catch (Exception e) {
            message = e.getMessage();
        }
        return message;
    }
    
    @Override
    public Usuario usuarioBuscar(String usuario){
        Usuario user = null;
        StringBuilder consultaSQL = new StringBuilder();
        consultaSQL.append("SELECT ")
                .append("ItemAi,")
                .append("IdUsuario,")
                .append("CodUsuario,")
                .append("Usuario,")
                .append("aes_decrypt(Password, 'contraseña'),")
                .append("Nombres,")
                .append("Apellidos,")
                .append("Email FROM usuario ")
                .append("WHERE (CodUsuario = ? or Email = ? or Usuario = ?) and Estado = 1");
        try ( Connection puente = this.conexion.getConexion()){
            PreparedStatement consultaPreparada = puente.prepareStatement(consultaSQL.toString());
            consultaPreparada.setString(1, usuario);
            consultaPreparada.setString(2, usuario);
            consultaPreparada.setString(3, usuario);
            ResultSet resultado = consultaPreparada.executeQuery();
            if(resultado.next()){
                user = new Usuario();
                user.setItemAi(resultado.getInt(1));
                user.setIdUsuario(resultado.getString(2));
                user.setCodUsuario(resultado.getString(3));
                user.setUsuario(resultado.getString(4));
                user.setPassword(resultado.getString(5));
                user.setNombres(resultado.getString(6));
                user.setApellidos(resultado.getString(7));
                user.setEmail(resultado.getString(8));
            }else{
                this.message = "Este usuario no existe";
            }
        } catch (Exception e) {
            this.message = e.getMessage();
        }
        return user;
    }

    @Override
    public String usuarioEnLinea(Integer id, Integer estado) {
        StringBuilder consultaSQL = new StringBuilder();
        consultaSQL.append("UPDATE usuario SET ")
                .append("Enlinea = ? WHERE ItemAi = ?");
        try ( Connection puente = this.conexion.getConexion()){
            PreparedStatement consultaPreparada = puente.prepareStatement(consultaSQL.toString());
            consultaPreparada.setInt(1, estado);
            consultaPreparada.setInt(2, id);
            int resultado = consultaPreparada.executeUpdate();
            if(resultado == 0){
                this.message = "No se ha actualizado";
            }
        } catch (Exception e) {
            this.message = e.getMessage();
        }
        return this.message;
    }
    
    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String usuarioEstado(Integer id, Integer estado) {
        StringBuilder consultaSQL = new StringBuilder();
        consultaSQL.append("UPDATE usuario SET ")
                .append("Estado = ? WHERE ItemAI = ?");
        try ( Connection puente = this.conexion.getConexion()){
            PreparedStatement consultaPreparada = puente.prepareStatement(consultaSQL.toString());
            consultaPreparada.setInt(1, estado);
            consultaPreparada.setInt(2, id);
            int resultado = consultaPreparada.executeUpdate();
            if(resultado == 0d){
                this.message = "No se ha actualizado";
            }
        } catch (Exception e) {
            this.message = e.getMessage();
        }
        return this.message;
    }

}
