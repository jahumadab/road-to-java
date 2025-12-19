package libros_fil.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    public static Connection getConexion(){

        Connection conexion  = null;
        String baseDeDatos = "libros_fil";
        String url = "jdbc:mysql://localhost:3306/" + baseDeDatos;
        String usuario = "root";
        String password = "admin";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
        }catch(Exception e){
            System.out.println("Error al conectarse " + e.getMessage());

        }


        return conexion;
    }

    public static void main(String[] args) {
        Connection conexion = Conexion.getConexion();
        if(conexion != null)
            System.out.println("Conexion exitosa: " + conexion);
        else
            System.out.println("Error al  conectarse");
    }

}
