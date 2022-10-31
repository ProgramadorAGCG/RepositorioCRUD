
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    
    private final String url;
    private final String user;
    private final String driver;
    private final String pass;
    
    public ConexionBD(){
        this.url = "jdbc:mysql://localhost:3306/mibase3";
        this.driver = "com.mysql.cj.jdbc.Driver";
        this.user = "root";
        this.pass = "";
    }
    
    public Connection getConexion() throws SQLException{
        Connection conector=null;
        try {
            Class.forName(driver).newInstance();
            conector=DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            throw new SQLException(e.getMessage());   
        }
        return conector;
    }
    
}
