package Bases_de_Datos;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    String url="jdbc:mysql://localhost:3306/tienda_walfrebre?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String user="wnaesVM";
    String password="wnaes0823";
    Connection conexion;

    public Connection dameConexion(){
        try {
            //crea la conexion con la base de datos
            conexion = DriverManager.getConnection(url, user, password);
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Error de conexión");
        }

        return conexion;
    }

}
