package Bases_de_Datos;

import javax.swing.*;
import java.sql.*;

public class Inventario extends  ProductosBBDD {
    private final String verificarConsulta="SELECT NOMBRE_PRODUCTO FROM inventario_fabrica WHERE NOMBRE_PRODUCTO=?";
    private final String updateCantidad="UPDATE inventario_fabrica SET UNIDADES_DISPONIBLES=(UNIDADES_DISPONIBLES+?) WHERE NOMBRE_PRODUCTO=?";
    private final String producto="INSERT INTO inventario_fabrica (NOMBRE_PRODUCTO,PRECIO_UNIDAD,UNIDADES_DISPONIBLES) VALUES (?,?,?)";
    private final String vendido="UPDATE inventario_fabrica SET UNIDADES_DISPONIBLES=(UNIDADES_DISPONIBLES-?) WHERE NOMBRE_PRODUCTO=?";
    private final String verificarVenta="SELECT NOMBRE_PRODUCTO,UNIDADES_DISPONIBLES FROM inventario_fabrica WHERE UNIDADES_DISPONIBLES>=? AND NOMBRE_PRODUCTO=?";
    private final String verificarEscaces="SELECT NOMBRE_PRODUCTO,UNIDADES_DISPONIBLES FROM inventario_fabrica WHERE UNIDADES_DISPONIBLES<=8";
    Conexion conexion=new Conexion();
    Connection accesBD=conexion.dameConexion();

    @Override
    public boolean existeProducto(String nombre_producto) {
        try {
            resultadosVerificar = null;
            preparedStatement = accesBD.prepareStatement(verificarConsulta);
            preparedStatement.setString(1, nombre_producto);
            resultadosVerificar = preparedStatement.executeQuery();
            resultadosVerificar.beforeFirst();
            if (resultadosVerificar.next()) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizarCantidad(String nombre_producto, int unidades) {
        try {
            update=accesBD.prepareStatement(updateCantidad);
            update.setInt(1,unidades);
            update.setString(2,nombre_producto);
            update.executeUpdate();
            return true;
        }catch (Exception ex){
            //JOptionPane.showMessageDialog(null,ex.getMessage());
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean addProductos(String nombre_producto,double precio,int unidades) {
        try {
            preparedStatement=accesBD.prepareStatement(producto);
            preparedStatement.setString(1,nombre_producto);
            preparedStatement.setDouble(2,precio);
            preparedStatement.setInt(3,unidades);
            preparedStatement.executeUpdate();
            return true;
        }catch (Exception ex){
            //JOptionPane.showMessageDialog(null,ex.getMessage());
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public void productoVendido(String nombre_producto, int cantidad) {
        try {
            producto_vendido=accesBD.prepareStatement(vendido);
            producto_vendido.setInt(1,cantidad);
            producto_vendido.setString(2,nombre_producto);
            producto_vendido.executeUpdate();
            System.out.println("vendido");
        }catch (Exception ex){
            //JOptionPane.showMessageDialog(null,ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public boolean verificarVenta(String nombre_producto, int cantidad)  {
        try {

            resultadosVerificar = null;
            preparedStatement = accesBD.prepareStatement(verificarVenta);
            preparedStatement.setInt(1, cantidad);
            preparedStatement.setString(2, nombre_producto);
            resultadosVerificar = preparedStatement.executeQuery();
            resultadosVerificar.beforeFirst();
            if (resultadosVerificar.next()) {
                //JOptionPane.showMessageDialog(null,"Producto vendido con exito");
                System.out.println("Producto vendido con exito");
                //resultadosVerificar.close();
                return true;
            } else {
                //JOptionPane.showMessageDialog(null,"La cantidad de productos requeridos no se pueden vender, no hay suficientes unidades, disponible: "+resultadosVerificar.getString("UNIDADES_DISPONIBLES"));
                System.out.println("La cantidad de productos requeridos no se pueden vender, no hay suficientes unidades, disponible: " + resultadosVerificar.getString("UNIDADES_DISPONIBLES"));
                //resultadosVerificar.close();
                return false;
            }
        }catch (Exception ex){
            System.out.println("La cantidad de productos requeridos no se pueden vender, no hay suficientes unidades, disponible: ");
            return false;
        }

    }
   public void alertaEscaces()  {
        try {
            verificar=accesBD.createStatement();
            resultadosVerificar = verificar.executeQuery(verificarEscaces);
            resultadosVerificar.beforeFirst();
            if (resultadosVerificar.next()) {
                resultadosVerificar.beforeFirst();
                while (resultadosVerificar.next()) {
                    //JOptionPane.showMessageDialog(null, "Debes abastecer EL INVENTARIO DE LA FABRICA de este producto esta escaceando " + resultadosVerificar.getString("NOMBRE_PRODUCTO"));
                    System.out.println("Debes abastecer EL INVENTARIO DE LA FABRICA de este producto esta escaceando " + resultadosVerificar.getString("NOMBRE_PRODUCTO"));
                }
            } else {
                //JOptionPane.showMessageDialog(null, "Cantidad de " + resultadosVerificar.getString("NOMBRE_PRODUCTO") + " disponible es " + resultadosVerificar.getString("UNIDADES_DISPONIBLES"));
                System.out.println("Cantidad de " + resultadosVerificar.getString("NOMBRE_PRODUCTO") + " disponible es " + resultadosVerificar.getString("UNIDADES_DISPONIBLES"));
            }
        }catch (Exception ex){

        }
    }
}
