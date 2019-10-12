import Tienda.Productos;

import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException {
        Productos cliente=new Productos();

        String mensaje=cliente.agregarProductos(true,"hola","prueba",20000,4);
        System.out.println(mensaje);

    }
}
