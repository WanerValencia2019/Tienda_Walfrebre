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
        Ventas venta=new Ventas();
        /*Productos cliente=new Productos();
        ProductosBBDD cl=new ProductosBBDD();
        Ventas venta=new Ventas();*/

        try {
            //String mensaje=cliente.agregarProductos(true,"hola","prueba",20000,4);
            //System.out.println(mensaje);
        }catch (Exception ex){

        }
        /*try {
            //cl.alertaEscaces();
           // venta.ventas();
            System.out.println(productos.agregarProductos(true,"effember","lol",100000,1));
            inventarioFabrica.addProductos(true,"effember",100000,1);
            venta.vender("14377438773","Freicer","effember",2,"fabrica");
            venta.ventasRealizadas("fabrica");
            venta.ventas();
        }catch (Exception EXX){
           //System.out.println(EXX.getMessage());
        }*/


       byte opc=1;
        while (opc!=0){

            System.out.println("<--------> BIENVENIDO AL SISTEMA WALFREBRE <---------->");
            System.out.println("1. Realizar Venta");
            System.out.println("2. Numero de ventas realizadas en la fabrica ");
            System.out.println("3. Numero de ventas realizadas en la tienda");
            System.out.println("4. Ver registro de ventas realizadas en la Tienda");
            System.out.println("5. Ver registro de ventas realizadas en la Fabrica");
            System.out.println("6. Agregar Productos a la tienda");
            System.out.println("7. Agregar Productos a la fabrica");
            System.out.println("8. Borrar producto de la tienda ");
            System.out.println("0. Salir");
            System.out.print("\n Opcion-->");
            try {

                opc = write.nextByte();

                switch (opc) {
                    case 1:
                        venta.vender("143777733", "Andrés Valencia", "camisa polo azul", 2, "tienda");
                        break;
                    case 2:
                        venta.ventasRealizadas("fabrica");
                        break;

                    case 3:
                        venta.ventasRealizadas("tienda");
                        break;

                    case 4:
                            venta.ventas("tienda");
                        break;

                    case 5:
                        venta.ventas("fabrica");
                        break;

                    case 6:
                        String contraseña="",nombre="",descripcion="";
                        double precio=0;
                        int cantidad=0;
                        write.nextLine();
                        System.out.print("Ingresa la contraseña del administrador: ");
                        contraseña=write.nextLine();

                        if(productos.verificarAdministrador(contraseña)){
                            System.out.print("Nombre del producto: ");
                            nombre=write.nextLine();
                            System.out.print("Descripción: ");
                            descripcion=write.nextLine();
                            System.out.print("Precio por unidad: ");
                            precio=write.nextDouble();
                            System.out.print("Unidades: ");
                            cantidad=write.nextInt();
                            System.out.println(productos.agregarProductos(true,nombre,descripcion,precio,cantidad));
                        }else{
                            System.out.println("Contraseña incorrecta");
                        }
                        break;

                    case 7:
                        String contraseña2="",nombre2="";
                        double precio2=0;
                        int cantidad2=0;
                        write.nextLine();
                        System.out.print("Ingresa la contraseña del administrador: ");
                        contraseña=write.nextLine();

                        if(productos.verificarAdministrador(contraseña)){
                            System.out.print("Nombre del producto: ");
                            nombre2=write.nextLine();

                            System.out.print("Precio por unidad: ");
                            precio2=write.nextDouble();

                            System.out.print("Unidades: ");
                            cantidad2=write.nextInt();

                            inventarioFabrica.addProductos(true,nombre2,precio2,cantidad2);
                        }else{
                            System.out.println("Contraseña incorrecta");
                        }
                        break;
                    case 8:
                        String contraseña3;
                        String nombre3;
                        write.nextLine();

                        System.out.print("Ingresa la contraseña del administrador: ");
                        contraseña3=write.nextLine();

                        if(productos.verificarAdministrador(contraseña3)){
                            System.out.print("Nombre del producto: ");
                            nombre3=write.nextLine();
                            System.out.println(productos.eliminarProducto(true,nombre3));
                        }
                        else{
                            System.out.println("Contraseña incorrecta");
                        }
                        break;
                    default:
                        System.out.println("opcion no disponible");
                        break;
                }
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }


    }
}
