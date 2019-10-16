package ventas;

import Bases_de_Datos.Ventas;

import java.sql.SQLException;
import java.util.Scanner;

public class Venta {
    Scanner write=new Scanner(System.in);
    Ventas venta=new Ventas();

    public void ventasRealizadas(String lugar){
        venta.ventasRealizadas(lugar);
    }
    public void vender(String id_cliente,String nombre_cliente,String nombre_producto,int cantidad,String lugar_compra){
        venta.vender(id_cliente, nombre_cliente, nombre_producto, cantidad, lugar_compra);
    }
    public void ventas(String lugar) throws SQLException {
        venta.ventas(lugar);
    }
}
