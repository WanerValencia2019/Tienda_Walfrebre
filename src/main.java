import Tienda.Productos;
import Fabrica.Inventario_Fabrica;
import ventas.Venta;

import Bases_de_Datos.*;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner write=new Scanner(System.in);
        Productos productos=new Productos();
        Inventario_Fabrica inventarioFabrica=new Inventario_Fabrica();
        //Venta venta=new Venta();
        /*Productos cliente=new Productos();
        ProductosBBDD cl=new ProductosBBDD(); */
        Ventas venta=new Ventas();

        try {
            //String mensaje=cliente.agregarProductos(true,"hola","prueba",20000,4);
            //System.out.println(mensaje);
        }catch (Exception ex){

        }
        try {
            //cl.alertaEscaces();
           // venta.ventas();

            venta.vender("11280240080","Waner Andrés","nombre",2,"fabrica",23343.0);
            venta.ventasRealizadas("fabrica");
        }catch (Exception EXX){
           System.out.println(EXX.getMessage());
        }

     /*   byte opc=0;
        while (opc!=6){
            System.out.println("<--------> BIENVENIDO AL SISTEMA WALFREBRE <---------->");
            System.out.println("1. Realizar Venta");
            System.out.println("2. Numero de ventas realizadas");
            System.out.println("3. Ver registro de ventas realizadas en la Tienda");
            System.out.println("4. Ver registro de ventas realizadas en la Tienda");
            System.out.println("5. Agregar Productos a la fabrica");
            System.out.println("7. Agregar Productos a la tienda");
            System.out.println("9. Salir");
            System.out.print("\n Opcion-->");
            opc=write.nextByte();

            switch (opc){
                case 1:
                    String id,nombre,producto,lugar;
                    int cantidad=0;
                    write.nextLine();
                    System.out.print("Identificacón: ");
                    id=write.nextLine();
                    System.out.print("Nombre Cliente: ");
                    nombre=write.nextLine();
                    System.out.print("Nombre Producto: ");
                    producto=write.nextLine();
                    System.out.print("Unidades: ");
                    cantidad=write.nextInt();
                    write.nextLine();
                    System.out.print("Lugar de compra(tienda o fabrica): ");
                    lugar=write.nextLine();
                    venta.vender(id,nombre,producto,cantidad,lugar,2000);
                    break;
                default:
                    System.out.println("opcion no disponible");
                    break;
            }

        } */


    }
}
