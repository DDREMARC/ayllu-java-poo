import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// Clase CONTROLADORA de la tabla "gasto_fijo".
public class GastoFijoController {
    private final Connection conexion;
    private final ArrayList<GastoFijoModel> gastos = new ArrayList<>();

    public GastoFijoController(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean registrar(int idTipo, double monto) {
        String sql = "INSERT INTO gasto_fijo (id_tipo, monto) VALUES (?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idTipo);
            ps.setDouble(2, monto);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al registrar el gasto: " + e.getMessage());
            return false;
        }
    }

    // No recibe parámetros del usuario, por eso usa Statement en vez de PreparedStatement
    public ArrayList<GastoFijoModel> listar() {
        gastos.clear();
        String sql = "SELECT * FROM gasto_fijo";
        try (Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                gastos.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los gastos: " + e.getMessage());
        }
        return gastos;
    }

    public GastoFijoModel buscarPorId(int idGasto) {
        String sql = "SELECT * FROM gasto_fijo WHERE id_gasto = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idGasto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el gasto: " + e.getMessage());
        }
        return null;
    }

    public boolean actualizar(int idGasto, int idTipo, double monto) {
        String sql = "UPDATE gasto_fijo SET id_tipo = ?, monto = ? WHERE id_gasto = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idTipo);
            ps.setDouble(2, monto);
            ps.setInt(3, idGasto);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el gasto: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idGasto) {
        String sql = "DELETE FROM gasto_fijo WHERE id_gasto = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idGasto);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar el gasto: " + e.getMessage());
            return false;
        }
    }

    private GastoFijoModel mapear(ResultSet rs) throws SQLException {
        return new GastoFijoModel(
                rs.getInt("id_gasto"),
                rs.getInt("id_tipo"),
                rs.getString("fecha"),
                rs.getDouble("monto")
        );
    }
}
