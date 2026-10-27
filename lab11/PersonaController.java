import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// Clase CONTROLADORA de la tabla "personas" (clientes y empleados).
// Todo lo que lleva un dato ingresado por el usuario va con PreparedStatement.
public class PersonaController {
    private final Connection conexion;
    private final ArrayList<PersonaModel> personas = new ArrayList<>();

    public PersonaController(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean registrarEmpleado(String codigo, String nombre, String telefono,
                                      String cargo, double pagoPorDia, int diasTrabajados, double bono) {
        String sql = "INSERT INTO personas (codigo_persona, nombre, numero_telefono, es_empleado, "
                + "cargo, pago_por_dia, dias_trabajados, bono) VALUES (?, ?, ?, 1, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ps.setString(2, nombre);
            ps.setString(3, telefono);
            ps.setString(4, cargo);
            ps.setDouble(5, pagoPorDia);
            ps.setInt(6, diasTrabajados);
            ps.setDouble(7, bono);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al registrar el empleado: " + e.getMessage());
            return false;
        }
    }

    public boolean registrarCliente(String codigo, String nombre, String telefono,
                                      String zonaResidencia, double deudaInicial) {
        String sql = "INSERT INTO personas (codigo_persona, nombre, numero_telefono, es_empleado, "
                + "zona_residencia, deuda_total) VALUES (?, ?, ?, 0, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ps.setString(2, nombre);
            ps.setString(3, telefono);
            ps.setString(4, zonaResidencia);
            ps.setDouble(5, deudaInicial);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al registrar el cliente: " + e.getMessage());
            return false;
        }
    }

    // No recibe parámetros del usuario, por eso usa Statement en vez de PreparedStatement
    public ArrayList<PersonaModel> listar() {
        personas.clear();
        String sql = "SELECT * FROM personas";
        try (Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                personas.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar las personas: " + e.getMessage());
        }
        return personas;
    }

    public PersonaModel buscarPorId(String codigoPersona) {
        String sql = "SELECT * FROM personas WHERE codigo_persona = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codigoPersona);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar la persona: " + e.getMessage());
        }
        return null;
    }

    public boolean actualizarEmpleado(String codigo, int diasTrabajados, double bono) {
        String sql = "UPDATE personas SET dias_trabajados = ?, bono = ? WHERE codigo_persona = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, diasTrabajados);
            ps.setDouble(2, bono);
            ps.setString(3, codigo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el empleado: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarCliente(String codigo, double nuevaDeudaTotal) {
        String sql = "UPDATE personas SET deuda_total = ? WHERE codigo_persona = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setDouble(1, nuevaDeudaTotal);
            ps.setString(2, codigo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(String codigoPersona) {
        String sql = "DELETE FROM personas WHERE codigo_persona = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codigoPersona);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar la persona: " + e.getMessage());
            return false;
        }
    }

    private PersonaModel mapear(ResultSet rs) throws SQLException {
        PersonaModel p = new PersonaModel();
        p.setCodigoPersona(rs.getString("codigo_persona"));
        p.setNombre(rs.getString("nombre"));
        p.setNumeroTelefono(rs.getString("numero_telefono"));
        p.setEsEmpleado(rs.getInt("es_empleado"));
        p.setCargo(rs.getString("cargo"));
        p.setPagoPorDia(rs.getDouble("pago_por_dia"));
        p.setDiasTrabajados(rs.getInt("dias_trabajados"));
        p.setBono(rs.getDouble("bono"));
        p.setZonaResidencia(rs.getString("zona_residencia"));
        p.setDeudaTotal(rs.getDouble("deuda_total"));
        return p;
    }
}
