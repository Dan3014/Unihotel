package basedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Conexion instancia; // Singleton
    private Connection conexion;
    private final String URL = "jdbc:mysql://localhost:3306/dbcliente"; // Corrige el formato de la URL
    private final String USER = "root"; // Cambia esto por tu usuario de MySQL
    private final String PASSWORD = ""; // Cambia esto por tu contraseña de MySQL

    // Constructor privado para aplicar patrón Singleton
    private Conexion() {
        try {
            // Carga del driver (opcional en versiones nuevas, pero no está de más)
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }

    // Método para obtener la instancia
    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    // Devuelve la conexión
    public Connection conectar() {
        return conexion;
    }

    // Cierra la conexión si está abierta
    public void desconectar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
