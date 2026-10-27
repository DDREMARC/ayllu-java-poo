import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// Clase CONTROLADORA: administra la colección temporal de ventas y hace el
// CRUD real contra la base de datos. Usa PreparedStatement en todo lo que
// lleva un dato ingresado por el usuario (insertar, buscar, actualizar,
// eliminar); Statement solo donde no hay parámetros (listar todo).
public class VentaController {
    private final Connection conexion;
    private final ArrayList<VentaModel> ventas = new ArrayList<>();

    public VentaController(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean registrar(double precio, double peso, double pagoPollo,
                              String fecha, String codigoCliente) {
        String sql = "INSERT INTO ventas (Precio, Peso, Pago_pollo, Fecha, Codigo_Cliente) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setDouble(1, precio);
            ps.setDouble(2, peso);
            ps.setDouble(3, pagoPollo);
            ps.setString(4, fecha);
            ps.setString(5, codigoCliente);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al registrar la venta: " + e.getMessage());
            return false;
        }
    }

    // No recibe parámetros del usuario, por eso usa Statement en vez de PreparedStatement
    public ArrayList<VentaModel> listar() {
        ventas.clear();
        String sql = "SELECT * FROM ventas";
        try (Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ventas.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar las ventas: " + e.getMessage());
        }
        return ventas;
    }

    public VentaModel buscarPorId(int codigoVenta) {
        String sql = "SELECT * FROM ventas WHERE Codigo_venta = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, codigoVenta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar la venta: " + e.getMessage());
        }
        return null;
    }

    public boolean actualizarPago(int codigoVenta, double nuevoPagoPollo) {
        String sql = "UPDATE ventas SET Pago_pollo = ? WHERE Codigo_venta = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setDouble(1, nuevoPagoPollo);
            ps.setInt(2, codigoVenta);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar la venta: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int codigoVenta) {
        String sql = "DELETE FROM ventas WHERE Codigo_venta = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, codigoVenta);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar la venta: " + e.getMessage());
            return false;
        }
    }

    private VentaModel mapear(ResultSet rs) throws SQLException {
        return new VentaModel(
                rs.getInt("Codigo_venta"),
                rs.getDouble("Precio"),
                rs.getDouble("Peso"),
                rs.getDouble("Pago_pollo"),
                rs.getString("Fecha"),
                rs.getString("Codigo_Cliente")
        );
    }
}
