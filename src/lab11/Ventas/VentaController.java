import java.sql.*;
import java.util.ArrayList;

public class VentaController {
    private Connection connection;
    private ArrayList<VentaModel> ventasModelo;

    public VentaController(Connection connection) {
        this.connection = connection;
        this.ventasModelo = new ArrayList<>();
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
                ventasModelo.add(ventaModel);
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

    //COMPLETADO
    public void buscarPorId(int codigoConsultar){
        try{
            String query = "SELECT * FROM ventas where Codigo_venta = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,codigoConsultar);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                int codigoVenta = resultSet.getInt("Codigo_venta");
                double precio = resultSet.getDouble("Precio");
                double peso = resultSet.getDouble("Peso");
                double pagoPollo = resultSet.getDouble("Pago_pollo");
                String codigoCliente = resultSet.getString("Codigo_Cliente");
                String fecha = resultSet.getString("Fecha");
                VentaModel venta = new VentaModel(codigoVenta, precio, peso, pagoPollo, codigoCliente, fecha);
                venta.mostrarVenta();
            }
            else {
                System.out.println("No se encontro el registro");
            }
            preparedStatement.close();
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
        }
    }

    public void listar(){
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ventas");
            ventasModelo.clear();

            while (resultSet.next()){
                int codigoVenta = resultSet.getInt("Codigo_venta");
                double precio = resultSet.getDouble("Precio");
                double peso = resultSet.getDouble("Peso");
                double pagoPollo = resultSet.getDouble("Pago_pollo");
                String codigoCliente = resultSet.getString("Codigo_Cliente");
                String fecha = resultSet.getString("Fecha");
                VentaModel venta = new VentaModel(codigoVenta, precio, peso, pagoPollo, codigoCliente, fecha);
                ventasModelo.add(venta);

            }
            for (int i = 0; i < ventasModelo.size(); i++){
                ventasModelo.get(i).mostrarVenta();
            }
            resultSet.close();
            statement.close();
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
        }
    }


    public void actualizar(VentaModel ventaModel){
        try{
            String query = "UPDATE ventas SET Precio = ?, Peso = ?, Pago_pollo = ?, Codigo_cliente = ?, Fecha = ? WHERE Codigo_venta = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, ventaModel.getPrecio());
            preparedStatement.setDouble(2, ventaModel.getPeso());
            preparedStatement.setDouble(3, ventaModel.getPagoPollo());
            preparedStatement.setString(4, ventaModel.getCodigoCliente());
            preparedStatement.setString(5, ventaModel.getFecha());
            preparedStatement.setInt(6, ventaModel.getCodigoVenta());

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("Tabla ventas actualiza: " + filasAfectadas);
            }
            else {
                System.out.println("No se hizo los cambios");
            }
            preparedStatement.close();
        }catch (SQLException error){
            System.out.println(error.getMessage());
        }
    }

    public void eliminar(int codigoVenta){
        try {
            String query = "DELETE FROM ventas where Codigo_venta = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, codigoVenta);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("Se ha eliminado con exito");
            }
            else {
                System.out.println("No se encontro el registro");
            }
            preparedStatement.close();
        }catch (SQLException error){
            System.out.println(error.getMessage());
        }
    }
}
