package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/ayllu_pollos";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "";

    private Connection connection;

    public void abrirConexion() throws SQLException {
        connection = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }

    public Connection getConnection() {
        return connection;
    }

    public void cerrarConexion() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
    }
}
