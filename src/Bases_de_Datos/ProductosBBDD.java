package Bases_de_Datos;


import javax.swing.*;
import java.sql.*;

public class ProductosBBDD {

    private final String producto="INSERT INTO productos (NOMBRE_PRODUCTO,PRECIO_UNIDAD,CANTIDAD) VALUES (?,?,?)";
    private final String verificarConsulta="SELECT NOMBRE_PRODUCTO FROM productos WHERE CANTIDAD>0 AND NOMBRE_PRODUCTO=?";
    private final String verificarEscaces="SELECT NOMBRE_PRODUCTO,CANTIDAD FROM productos WHERE CANTIDAD<=8";
    private final String updateCantidad="UPDATE productos SET CANTIDAD=(CANTIDAD+?) WHERE NOMBRE_PRODUCTO=?";
    private final String cleanProducto="DELETE FROM productos WHERE NOMBRE_PRODUCTO=?";
    private final String vendido="UPDATE productos SET CANTIDAD=(CANTIDAD-?) WHERE NOMBRE_PRODUCTO=?";

    private final String verificarVenta="SELECT NOMBRE_PRODUCTO, CANTIDAD FROM productos WHERE CANTIDAD >= ? AND NOMBRE_PRODUCTO = ?";

    protected PreparedStatement preparedStatement=null;
    protected Statement verificar=null;
    protected PreparedStatement update=null;
    protected PreparedStatement delete=null;
    protected PreparedStatement producto_vendido=null;


    Conexion conexion=new Conexion();
    Connection accesBD=conexion.dameConexion();
    protected ResultSet resultadosVerificar=null;

    public boolean existeProducto(String nombre_producto) {
        try {
            preparedStatement = accesBD.prepareStatement(verificarConsulta);
            preparedStatement.setString(1, nombre_producto);
            resultadosVerificar = preparedStatement.executeQuery();
            resultadosVerificar.beforeFirst();
            if (resultadosVerificar.next()) {
                //resultadosVerificar.close();
                return true;
            } else {
                //resultadosVerificar.close();
                return false;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
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
            //JOptionPane.showMessageDialog(null,ex.getMessage());
            System.out.println(ex.getMessage());
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
            //JOptionPane.showMessageDialog(null,ex.getMessage());
            System.out.println(ex.getMessage());
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
            //JOptionPane.showMessageDialog(null,ex.getMessage());
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public boolean verificarVenta(String nombre_producto, int cantidad) throws SQLException {

        preparedStatement=accesBD.prepareStatement(verificarVenta);
        preparedStatement.setInt(1,cantidad);
        preparedStatement.setString(2,nombre_producto);
        resultadosVerificar=preparedStatement.executeQuery();
        //System.out.println("SQl");
        resultadosVerificar.beforeFirst();
       boolean hasfirst = resultadosVerificar.first();
       //System.out.println(resultadosVerificar.isFirst());

        if(hasfirst){
            //JOptionPane.showMessageDialog(null,"Producto vendido con exito");
            productoVendido(nombre_producto,cantidad);
            //resultadosVerificar.close();
            return true;
        }else{
            //JOptionPane.showMessageDialog(null,"La cantidad de productos requeridos no se pueden vender, no hay suficientes unidades. unidades disponibles: "+resultadosVerificar.getInt("CANTIDAD"));
            System.out.println("La cantidad de productos requeridos no se pueden vender, no hay suficientes unidades disponibles: "+resultadosVerificar.getInt("CANTIDAD"));
            //resultadosVerificar.close();
            return false;
        }

    }
    public void productoVendido(String nombre_producto,int cantidad){
        try {
            producto_vendido=accesBD.prepareStatement(vendido);
            producto_vendido.setInt(1,cantidad);
            producto_vendido.setString(2, nombre_producto);
            producto_vendido.executeUpdate();
        }catch (Exception ex){
            //JOptionPane.showMessageDialog(null,ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }

    public void alertaEscaces()  {
        try {
        verificar=accesBD.createStatement();
        resultadosVerificar=verificar.executeQuery(verificarEscaces);

        if(resultadosVerificar.next()){
                //JOptionPane.showMessageDialog(null, "Debes abastecer EL INVENTARIO DE LA FABRICA de este producto esta escaceando " + resultadosVerificar.getString("NOMBRE_PRODUCTO"));
            System.out.println("Debes abastecer EL INVENTARIO DE LA FABRICA de este producto esta escaceando " + resultadosVerificar.getString("NOMBRE_PRODUCTO"));
        }else{
            //JOptionPane.showMessageDialog(null,"Cantidad de "+resultadosVerificar.getString("NOMBRE_PRODUCTO")+" disponible es "+resultadosVerificar.getInt("UNIDADES_DISPONIBLES"));
            System.out.println("Cantidad de "+resultadosVerificar.getString("NOMBRE_PRODUCTO")+" disponible es "+resultadosVerificar.getInt("UNIDADES_DISPONIBLES"));
        }
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
    }



}
