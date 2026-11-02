import java.sql.*;

public class DatabaseConectorVentas {
    private String url = "jdbc:mysql://localhost:3306/ayllu_pollos";
    private Connection connection;
    private String usuario = "root";
    private String contraseña = "";

    public void abrirConexion() {
        try {
            connection = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("La conexión ha sido exitosa");
        } catch (SQLException error) {
            System.out.println("Error en la base de datos: " + error.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void cerrarConexion() {
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException error) {
                System.out.println("Error en la base de datos: " + error.getMessage());
            }
        }
    }
}
