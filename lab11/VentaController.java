import java.sql.*;
import java.util.ArrayList;

public class VentaController {
    private String url = "jdbc:mysql://localhost:3306/ayllu_pollos";
    private Connection connection;
    private String usuario = "root";
    private String contraseña = "";
    private ArrayList<VentaModel> ventasModelo;

    public VentaController(Connection connection) {
        this.connection = connection;
        this.ventasModelo = new ArrayList<>();
    }

    public void conectarBaseDatos(){
        try{
            connection = DriverManager.getConnection(
                    url, usuario, contraseña);
            System.out.println("Se estableció la conexión con la base de datos");
        } catch (
                SQLException error) {
            System.out.println(error.getMessage());
        }
    }

    public void registrar(VentaModel ventaModel){
        try {
            String query = "INSERT INTO ventas (Precio, Peso, Pago_pollo, Fecha, Codigo_Cliente) VALUES( ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setDouble(1, ventaModel.getPrecio());
            preparedStatement.setDouble(2, ventaModel.getPeso());
            preparedStatement.setDouble(3, ventaModel.getPagoPollo());
            preparedStatement.setString(4, ventaModel.getFecha());
            preparedStatement.setString(5, ventaModel.getCodigoCliente());

            int filasCambiadas = preparedStatement.executeUpdate();
            ArrayList<VentaModel> ventaModel = new Arraylist<>();
            registrar.add();
            if (filasCambiadas > 0){
                System.out.println("Se ha registrado con exito");
                add.lista
            }
            else {
                System.out.println("No se hizo los cambios");
            }
            preparedStatement.close();
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
        }
    }

    public void selectCodigo_venta(int codigoConsultar){
        try{
            String query = "SELECT * FROM ventas where codigoVenta = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,codigoConsultar);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int codigoVenta = resultSet.getInt("codigoVenta");
                double precio = resultSet.getDouble("precio");
                double peso = resultSet.getDouble("peso");
                double pagoPollo = resultSet.getDouble("pagoPollo");
                getString codigoCliente = resultSet.getDouble("codigoCliente");
                getString fecha = resultSet.getString("fecha");
                System.out.println(codigoVenta + " -" + precio + " - " + peso + " - " + pagoPollo + " - " + codigoCliente  + " - " + fecha);
            }
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
        }
    }

    /*public void insertProducto(int codigo, String nombre, float precio){
        try{
            String query = "INSERT INTO productos VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, codigo);
            preparedStatement.setString(2, nombre);
            preparedStatement.setFloat(3, precio);
            int filasAfectadas = preparedStatement.executeUpdate();
            System.out.println("Filas afectadas: " + filasAfectadas);
        }catch (SQLException error){
            System.out.println(error.getMessage());
        }
    }*/

    public void updateProducto(int codigo, String nombre, float precio){
        try{
            String query = "UPDATE productos SET nombre = ?, precio = ? where codigo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nombre);
            preparedStatement.setFloat(2, precio);
            preparedStatement.setInt(3, codigo);
            int filasAfectadas = preparedStatement.executeUpdate();
            System.out.println("Filas afectadas: " + filasAfectadas);
        }catch (SQLException error){
            System.out.println(error.getMessage());
        }
    }

    public void deleteProducto(int codigo){
        try {
            String query = "DELETE FROM productos where codigo = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, codigo);
            int filasAfectadas = preparedStatement.executeUpdate();
            System.out.println("Filas afectadas: " + filasAfectadas);
        }catch (SQLException error){
            System.out.println(error.getMessage());
        }
    }
}