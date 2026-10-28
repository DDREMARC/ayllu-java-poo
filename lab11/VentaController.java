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

    // TODO: url, usuario, contraseña y este metodo se mueven a DatabaseConnector.java (aqui la conexion llega por el constructor)
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
            if (filasCambiadas > 0){
                System.out.println("Se ha registrado con exito");
                // TODO: anotar aqui la venta en la lista ventasModelo (lista, punto, add, la venta entre parentesis)
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

    // TODO: arreglar este metodo y renombrarlo a buscarPorId:
    //  - en el SQL y en los get("...") usar los nombres de la base de datos (Codigo_venta, Precio, Peso, Pago_pollo, Codigo_Cliente, Fecha)
    //  - el tipo de texto es String (no "getString"), y el codigo del cliente se lee con el get de texto
    //  - armar un VentaModel con los 6 datos (en el orden de su constructor) y mostrarlo con mostrarVenta()
    //  - avisar si no se encontro ninguna venta con ese codigo
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

    // TODO: listar()    -> Statement normal, limpiar la lista, llenarla con un VentaModel por fila y mostrarla
    // TODO: actualizar(VentaModel) -> PreparedStatement (UPDATE ... WHERE Codigo_venta = ?), cuidar el orden de los ?
    // TODO: eliminar(int codigo)   -> PreparedStatement (DELETE ... WHERE Codigo_venta = ?)
}