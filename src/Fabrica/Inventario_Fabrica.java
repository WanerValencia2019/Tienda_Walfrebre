package Fabrica;
import Tienda.Administrador;
import Bases_de_Datos.Inventario;

import javax.swing.*;
import java.sql.SQLException;

public class Inventario_Fabrica extends Administrador{
    Inventario fabrica=new Inventario();

    public void addProductos(boolean admin,String nombre_producto,double precio,int unidades) throws SQLException {
        if(admin) {
            if (fabrica.existeProducto(nombre_producto)) {
                fabrica.actualizarCantidad(nombre_producto, unidades);
            } else {
                fabrica.addProductos(nombre_producto, precio, unidades);
            }
        }else{
            JOptionPane.showMessageDialog(null,"contraseña de administrador incorrecta");
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
