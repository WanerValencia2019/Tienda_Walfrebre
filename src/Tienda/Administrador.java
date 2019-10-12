package Tienda;

import java.sql.ResultSet;

public class Administrador {
    private String nombre="Walfrebre",usuario="admin",contraseña="admin",id;


    public boolean changePassword(String contraseñaAnterior,String nuevaContraseña ){
        if(contraseñaAnterior.equals(this.contraseña)){
            this.contraseña=nuevaContraseña;
            return true;
        }else{
            return false;
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getNombre() {
        return nombre;
    }


}
