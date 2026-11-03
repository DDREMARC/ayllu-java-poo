import java.sql.*;
import java.util.ArrayList;

public class GastoFijoController {
    private Connection connection;
    private ArrayList<GastoFijoModel> gastosModelo;

    public GastoFijoController(Connection connection) {
        this.connection = connection;
        this.gastosModelo = new ArrayList<>();
    }

    // Solo se aceptan los tipos 1, 2 y 3 (se revisa aqui, en un solo lugar)
    private boolean tipoValido(int idTipo) {
        if (idTipo >= 1 && idTipo <= 3) {
            return true;
        }
        System.out.println("Tipo invalido. Use 1 (Implementos), 2 (Combustible y peajes) o 3 (Agua y gas)");
        return false;
    }

    public void registrar(GastoFijoModel gastoModel) {
        if (!tipoValido(gastoModel.getIdTipo())) {
            return;
        }
        try {
            String query = "INSERT INTO gasto_fijo (id_tipo, fecha, monto) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, gastoModel.getIdTipo());
            preparedStatement.setString(2, gastoModel.getFecha());
            preparedStatement.setDouble(3, gastoModel.getMonto());

            int filasCambiadas = preparedStatement.executeUpdate();
            if (filasCambiadas > 0) {
                System.out.println("Se ha registrado con exito");
                gastosModelo.add(gastoModel);
            } else {
                System.out.println("No se hizo los cambios");
            }
            preparedStatement.close();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
    }

    public void listar() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM gasto_fijo");
            gastosModelo.clear();

            while (resultSet.next()) {
                int idGasto = resultSet.getInt("id_gasto");
                int idTipo = resultSet.getInt("id_tipo");
                String fecha = resultSet.getString("fecha");
                double monto = resultSet.getDouble("monto");
                GastoFijoModel gasto = new GastoFijoModel(idGasto, idTipo, fecha, monto);
                gastosModelo.add(gasto);
            }
            for (int i = 0; i < gastosModelo.size(); i++) {
                gastosModelo.get(i).mostrarGasto();
            }
            resultSet.close();
            statement.close();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
    }

    public void buscarPorId(int idGasto) {
        try {
            String query = "SELECT * FROM gasto_fijo WHERE id_gasto = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idGasto);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id_gasto");
                int idTipo = resultSet.getInt("id_tipo");
                String fecha = resultSet.getString("fecha");
                double monto = resultSet.getDouble("monto");
                GastoFijoModel gasto = new GastoFijoModel(id, idTipo, fecha, monto);
                gasto.mostrarGasto();
            } else {
                System.out.println("No se encontro el registro");
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
    }

    public void actualizar(GastoFijoModel gastoModel) {
        if (!tipoValido(gastoModel.getIdTipo())) {
            return;
        }
        try {
            String query = "UPDATE gasto_fijo SET id_tipo = ?, fecha = ?, monto = ? WHERE id_gasto = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, gastoModel.getIdTipo());
            preparedStatement.setString(2, gastoModel.getFecha());
            preparedStatement.setDouble(3, gastoModel.getMonto());
            preparedStatement.setInt(4, gastoModel.getIdGasto());

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Tabla gasto_fijo actualizada: " + filasAfectadas);
            } else {
                System.out.println("No se hizo los cambios");
            }
            preparedStatement.close();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
    }

    public void eliminar(int idGasto) {
        try {
            String query = "DELETE FROM gasto_fijo WHERE id_gasto = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idGasto);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Se ha eliminado con exito");
            } else {
                System.out.println("No se encontro el registro");
            }
            preparedStatement.close();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
    }
}
