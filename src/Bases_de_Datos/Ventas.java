package Bases_de_Datos;

import javax.swing.*;
import java.sql.SQLException;

public class Ventas extends ProductosBBDD{
    Inventario fabrica=new Inventario();
    private final String ventasRealizadasLugar="SELECT NOMBRE_PRODUCTO FROM ventas WHERE lugar_compra=?";
    private  final String obtenerPrecioTienda="SELECT PRECIO_UNIDAD FROM productos WHERE NOMBRE_PRODUCTO=?";
    private  final String obtenerPrecioFabrica="SELECT PRECIO_UNIDAD FROM inventario_fabrica WHERE NOMBRE_PRODUCTO=?";
    private  final String vender="INSERT INTO ventas (IDENTIFICACION,NOMBRE_CLIENTE,NOMBRE_PRODUCTO,lugar_compra,CANTIDAD,VALOR_TOTAL,EFECTIVO_RECIBIDO,EFECTIVO_DEVUELTO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private  final String ventas="SELECT * FROM ventas";

    public void ventasRealizadas(String lugar)  {
        resultadosVerificar=null;
        int cantidad=0;
        try {
           preparedStatement=accesBD.prepareStatement(ventasRealizadasLugar);
           preparedStatement.setString(1,lugar);
           resultadosVerificar=preparedStatement.executeQuery();
            while (resultadosVerificar.next()){
                cantidad++;
            }
            JOptionPane.showMessageDialog(null,"Ventanas realizadas "+cantidad);
            resultadosVerificar.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }
    public double obtenerPrecio(String nombre_producto, String lugarCompra) throws SQLException {
        resultadosVerificar=null;
        double precio=0;
        if(lugarCompra.equals("fabrica")){
                if(fabrica.existeProducto(nombre_producto)) {
                    preparedStatement = accesBD.prepareStatement(obtenerPrecioFabrica);
                    preparedStatement.setString(1, nombre_producto);
                    resultadosVerificar = preparedStatement.executeQuery();
                    resultadosVerificar.beforeFirst();
                    resultadosVerificar.next();
                    precio = resultadosVerificar.getDouble("PRECIO_UNIDAD");
                    return precio;
                }else {
                    JOptionPane.showMessageDialog(null,"No se encuentra el producto");
                    return 0;
                }

        }
        else{
                if(lugarCompra.equals("tienda") && existeProducto(nombre_producto)) {

                    preparedStatement = accesBD.prepareStatement(obtenerPrecioTienda);
                    preparedStatement.setString(1, nombre_producto);
                    resultadosVerificar = preparedStatement.executeQuery();
                    resultadosVerificar.beforeFirst();
                    resultadosVerificar.next();
                    precio = resultadosVerificar.getDouble("PRECIO_UNIDAD");
                    return precio;
                }else{
                    JOptionPane.showMessageDialog(null,"No se encuentra el producto");
                    return 0;
                }
        }
    }
    public void vender(String id_cliente,String nombre_cliente,String nombre_producto,int cantidad,String lugar_compra,double dinero) {
        double precio=0;
        double valorApagar=0;
        double resto=0;
        try {
            if (lugar_compra.equals("fabrica")) {
                if (fabrica.existeProducto(nombre_producto)) {
                    precio=obtenerPrecio(nombre_producto,lugar_compra);
                    if(precio<=dinero){
                        if(fabrica.verificarVenta(nombre_producto,cantidad)) {
                            resto = dinero - precio;
                            valorApagar=precio*cantidad;
                            preparedStatement = accesBD.prepareStatement(vender);
                            preparedStatement.setString(1, id_cliente);
                            preparedStatement.setString(2, nombre_cliente);
                            preparedStatement.setString(3, nombre_producto);
                            preparedStatement.setString(4, lugar_compra);
                            preparedStatement.setInt(5, cantidad);
                            preparedStatement.setDouble(6,valorApagar);
                            preparedStatement.setDouble(7,dinero);
                            preparedStatement.setDouble(8,resto);
                            preparedStatement.executeUpdate();
                            JOptionPane.showMessageDialog(null,"VALOR A PAGAR "+valorApagar+": Devuelta "+resto);
                            fabrica.productoVendido(nombre_producto, cantidad);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Te hace falta dinero");
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"No se encuentra el producto");
                }
            } else if (lugar_compra.equals("tienda")) {
                if (existeProducto(nombre_producto)) {
                    precio=obtenerPrecio(nombre_producto,lugar_compra);
                    if(precio<=dinero){
                        if(verificarVenta(nombre_producto,cantidad)) {
                            resto = precio - dinero;
                            valorApagar=precio*cantidad;
                            preparedStatement = accesBD.prepareStatement(vender);
                            preparedStatement.setString(1, id_cliente);
                            preparedStatement.setString(2, nombre_cliente);
                            preparedStatement.setString(3, nombre_producto);
                            preparedStatement.setString(4, lugar_compra);
                            preparedStatement.setInt(5, cantidad);
                            preparedStatement.setDouble(6,valorApagar);
                            preparedStatement.setDouble(7,dinero);
                            preparedStatement.setDouble(8,resto);
                            preparedStatement.executeUpdate();
                            JOptionPane.showMessageDialog(null,"VALOR A PAGAR "+valorApagar+": Devuelta "+resto);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Te hace falta dinero");
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"No se encuentra el producto");
                }

            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public void ventas() throws SQLException {
        verificar=accesBD.createStatement();
        resultadosVerificar=verificar.executeQuery(ventas);

        while (resultadosVerificar.next()){
            System.out.println("Identificación: "+resultadosVerificar.getString("IDENTIFICACION")+" Nombre: "+resultadosVerificar.getString("NOMBRE_CLIENTE")+" Producto: "+resultadosVerificar.getString("NOMBRE_PRODUCTO")+" Lugar de Compra: "+resultadosVerificar.getString("lugar_compra")+" Unidades:"+resultadosVerificar.getInt("CANTIDAD")+" " +
                    " Valor A Pagar: "+resultadosVerificar.getDouble("VALOR_TOTAL")+" Dinero Recibido: "+resultadosVerificar.getDouble("EFECTIVO_RECIBIDO")+" Efectivo Devuelto: "+resultadosVerificar.getDouble("EFECTIVO_DEVUELTO"));
            System.out.println("\t");
        }

        resultadosVerificar.close();
    }
}
