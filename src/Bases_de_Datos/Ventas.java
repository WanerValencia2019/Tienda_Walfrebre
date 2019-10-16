package Bases_de_Datos;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Scanner;

public class Ventas extends ProductosBBDD{
    Scanner writer=new Scanner(System.in);
    Inventario fabrica=new Inventario();
    private final String ventasRealizadasLugar="SELECT NOMBRE_PRODUCTO FROM ventas WHERE lugar_compra=?";
    private  final String obtenerPrecioTienda="SELECT PRECIO_UNIDAD FROM productos WHERE NOMBRE_PRODUCTO=?";
    private  final String obtenerPrecioFabrica="SELECT PRECIO_UNIDAD FROM inventario_fabrica WHERE NOMBRE_PRODUCTO=?";
    private  final String vender="INSERT INTO ventas (IDENTIFICACION,NOMBRE_CLIENTE,NOMBRE_PRODUCTO,lugar_compra,CANTIDAD,VALOR_TOTAL,EFECTIVO_RECIBIDO,EFECTIVO_DEVUELTO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private  final String ventas="SELECT * FROM ventas WHERE lugar_compra=?";

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
            //JOptionPane.showMessageDialog(null,"Ventanas realizadas "+cantidad);
            System.out.println("Ventanas realizadas "+cantidad);
            resultadosVerificar.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            //JOptionPane.showMessageDialog(null,ex.getMessage());
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
                    //JOptionPane.showMessageDialog(null,"No se encuentra el producto");
                    System.out.println("No se encuentra el producto");
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
                    //JOptionPane.showMessageDialog(null,"No se encuentra el producto");
                    System.out.println("No se encuentra el producto");
                    return 0;
                }
        }
    }
    public double pedirEfectivo(){
        double dinero=0;
        System.out.print("Efectivo del usuario: ");
        dinero=writer.nextDouble();

        return dinero;
    }
    public void vender(String id_cliente,String nombre_cliente,String nombre_producto,int cantidad,String lugar_compra) {
        double precio=0;
        double valorApagar=0;
        double resto=0;
        double dinero=0;
        try {
            if (lugar_compra.equals("fabrica")) {
                if (fabrica.existeProducto(nombre_producto)) {
                    precio=obtenerPrecio(nombre_producto,lugar_compra);
                    valorApagar=precio*cantidad;
                    //JOptionPane.showMessageDialog(null," valor a pagar "+valorApagar);
                    System.out.println("VALOR A PAGAR "+valorApagar);
                    System.out.print(valorApagar);
                    dinero=pedirEfectivo();
                    if(dinero >= valorApagar){
                        if(fabrica.verificarVenta(nombre_producto,cantidad)) {
                            resto = dinero-valorApagar;
                          /* System.out.println(dinero);
                            System.out.println(valorApagar);*/
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
                            fabrica.productoVendido(nombre_producto, cantidad);
                           //JOptionPane.showMessageDialog(null,"VALOR A PAGAR "+valorApagar+": Devuelta "+resto);
                            System.out.println("VALOR A PAGAR "+valorApagar+": Devuelta "+resto);
                            //System.out.println("VALOR A PAGAR "+valorApagar+": Devuelta "+resto);
                        }else{
                            //JOptionPane.showMessageDialog(null,"La cantidad de producto solicitado excede nuestro catalogo");
                            System.out.println("La cantidad de producto solicitado excede nuestro catalogo");
                        }
                    }else{
                        //JOptionPane.showMessageDialog(null,"Te hace falta dinero");
                        System.out.println("Te hace falta dinero");
                    }
                }else {
                    //JOptionPane.showMessageDialog(null,"No se encuentra el producto");
                    System.out.println("No se encuentra el producto");
                }
            } else {
                if(lugar_compra.equals("tienda")) {
                    if (existeProducto(nombre_producto)) {
                        precio = obtenerPrecio(nombre_producto, lugar_compra);
                        valorApagar = precio * cantidad;
                        //JOptionPane.showMessageDialog(null," valor a pagar "+valorApagar);
                        System.out.println(" valor a pagar " + valorApagar);
                        System.out.print(valorApagar);
                        dinero = pedirEfectivo();
                        if (dinero >= valorApagar) {
                            System.out.print(valorApagar);
                            if (verificarVenta(nombre_producto, cantidad)) {
                                resto = dinero - valorApagar;
                                System.out.println(dinero);
                                System.out.println(valorApagar);
                                preparedStatement = null;
                                preparedStatement = accesBD.prepareStatement(vender);
                                preparedStatement.setString(1, id_cliente);
                                preparedStatement.setString(2, nombre_cliente);
                                preparedStatement.setString(3, nombre_producto);
                                preparedStatement.setString(4, lugar_compra);
                                preparedStatement.setInt(5, cantidad);
                                preparedStatement.setDouble(6, valorApagar);
                                preparedStatement.setDouble(7, dinero);
                                preparedStatement.setDouble(8, resto);
                                preparedStatement.executeUpdate();
                                productoVendido(nombre_producto, cantidad);
                                //JOptionPane.showMessageDialog(null,"VALOR A PAGAR "+valorApagar+": Devuelta "+resto);
                                System.out.println("VALOR A PAGAR " + valorApagar + ": Devuelta " + resto);
                            } else {
                                //JOptionPane.showMessageDialog(null,"La cantidad de producto solicitado excede nuestro catalogo");
                                System.out.println("La cantidad de producto solicitado excede nuestro catalogo");
                            }

                        } else {
                            //JOptionPane.showMessageDialog(null,"Te hace falta dinero");
                            System.out.println("Te hace falta dinero");
                        }
                    } else {
                        //JOptionPane.showMessageDialog(null,"No se encuentra el producto");
                        System.out.println("No se encuentra el producto");
                    }
                }

            }
        }catch (SQLException ex){
           System.out.println(ex.getMessage());
        }
    }
    public void ventas(String lugar) throws SQLException {
        preparedStatement=accesBD.prepareStatement(ventas);
        preparedStatement.setString(1,lugar);
        resultadosVerificar=preparedStatement.executeQuery();
        resultadosVerificar.beforeFirst();
        if(resultadosVerificar.next()) {
            resultadosVerificar.beforeFirst();
            while (resultadosVerificar.next()) {
                System.out.println("Identificación: " + resultadosVerificar.getString("IDENTIFICACION") + " Nombre: " + resultadosVerificar.getString("NOMBRE_CLIENTE") + " Producto: " + resultadosVerificar.getString("NOMBRE_PRODUCTO") + " Lugar de Compra: " + resultadosVerificar.getString("lugar_compra") + " Unidades:" + resultadosVerificar.getInt("CANTIDAD") + " " +
                        " Valor A Pagar: " + resultadosVerificar.getDouble("VALOR_TOTAL") + " Dinero Recibido: " + resultadosVerificar.getDouble("EFECTIVO_RECIBIDO") + " Efectivo Devuelto: " + resultadosVerificar.getDouble("EFECTIVO_DEVUELTO"));
                System.out.println("\t");
            }
        }else{
            System.out.println("No hay ventas ventas en "+lugar);
            //JOptionPane.showMessageDialog(null,"No hay ventas ventas en "+lugar);
        }


        resultadosVerificar.close();
    }
}
