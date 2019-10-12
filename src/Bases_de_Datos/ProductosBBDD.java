package Bases_de_Datos;


import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductosBBDD {

    private final String producto="INSERT INTO productos (NOMBRE_PRODUCTO,PRECIO_UNIDAD,CANTIDAD) VALUES (?,?,?)";
    private final String verificarConsulta="SELECT NOMBRE_PRODUCTO FROM productos WHERE NOMBRE_PRODUCTO=?";
    private final String updateCantidad="UPDATE productos SET CANTIDAD=(CANTIDAD+?) WHERE NOMBRE_PRODUCTO=?";
    private final String cleanProducto="DELETE FROM productos WHERE NOMBRE_ARTICULO=?";

    private PreparedStatement preparedStatement=null;
    private PreparedStatement verificar=null;
    private PreparedStatement update=null;
    private PreparedStatement delete=null;

    Conexion conexion=new Conexion();
    Connection accesBD=conexion.dameConexion();
    ResultSet resultadosVerificar;

    public boolean existeProducto(String nombre_producto) throws SQLException {
        verificar=accesBD.prepareStatement(verificarConsulta);
        verificar.setString(1,nombre_producto);
        resultadosVerificar=verificar.executeQuery();
        if(resultadosVerificar.next()){
            return true;
        }else{
            return false;
        }
    }
    public boolean addProductos(String nombre_producto,String descripcion,double precio, int cantidad){
        try {
            preparedStatement=accesBD.prepareStatement(producto);
            preparedStatement.setString(1,nombre_producto);
            preparedStatement.setDouble(2,precio);
            preparedStatement.setInt(3,cantidad);
            preparedStatement.executeUpdate();
            return true;
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
            return false;
        }
    }
    public boolean actualizarCantidad(String nombre_producto,int cantidad){
        try {
            update=accesBD.prepareStatement(updateCantidad);
            update.setInt(1,cantidad);
            update.setString(2,nombre_producto);
            update.executeUpdate();
            return true;
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
            return false;
        }
    }
    public boolean deleteProducto(String nombre_producto){
        try {
            delete=accesBD.prepareStatement(cleanProducto);
            delete.setString(1,nombre_producto);
            delete.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
            return false;
        }

    }

}
