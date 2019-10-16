package Tienda;
import Bases_de_Datos.ProductosBBDD;

import javax.swing.*;
import java.sql.SQLException;

public class Productos extends Administrador{
    ProductosBBDD productos=new ProductosBBDD();
    public String nombre_producto,descripcion;
    private double precio;
    private int cantidad;

    public String agregarProductos(Boolean admin,String nombre_producto,String descripcion,double precio, int cantidad) throws SQLException {
        if(admin) {
            if (productos.existeProducto(nombre_producto)) {
                productos.actualizarCantidad(nombre_producto, cantidad);
                return "Actualizado con exito, ya existia el producto";
            } else {
                productos.addProductos(nombre_producto, descripcion, precio, cantidad);
                return "Se ha agregado con exito el producto";
            }
        }else{
            //JOptionPane.showMessageDialog(null,"Contraseña de administador incorrecta");
            System.out.println("Contraseña de administador incorrecta");
            return " ";
        }
    }
    public String eliminarProducto(Boolean admin,String nombre_producto) throws SQLException {
        if(admin) {
            if (productos.existeProducto(nombre_producto)) {
                productos.deleteProducto(nombre_producto);
                return "Producto eliminado con exito";
            } else {
                return "No se puede borrar el producto por que no existe";
            }
        }else{
            //JOptionPane.showMessageDialog(null,"Contraseña de administador incorrecta");
            System.out.println("Contraseña de administador incorrecta");
            return "";
        }
    }

    public boolean verificarAdministrador(String contraseña){
        if(contraseña.equals(this.getContraseña())){
            return true;
        }else{
            return false;
        }
    }
}
