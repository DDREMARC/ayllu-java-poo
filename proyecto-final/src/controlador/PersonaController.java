package controlador;

import modelo.Cliente;
import modelo.Empleado;
import modelo.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaController {
    private Connection connection;

    public PersonaController(Connection connection) {
        this.connection = connection;
    }

    private Persona construir(ResultSet rs) throws SQLException {
        String codigo = rs.getString("codigo_persona");
        String nombre = rs.getString("nombre");
        String telefono = rs.getString("numero_telefono");
        if (rs.getInt("es_empleado") == 1) {
            return new Empleado(codigo, nombre, telefono,
                    rs.getString("cargo"), rs.getDouble("pago_por_dia"),
                    rs.getInt("dias_trabajados"), rs.getDouble("bono"));
        }
        return new Cliente(codigo, nombre, telefono,
                rs.getString("zona_residencia"), rs.getDouble("deuda_total"));
    }

    public List<Persona> listar() throws SQLException {
        List<Persona> lista = new ArrayList<>();
        String sql = "SELECT * FROM personas ORDER BY es_empleado, codigo_persona";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lista.add(construir(rs));
        }
        rs.close();
        ps.close();
        return lista;
    }

    public Persona buscar(String codigo) throws SQLException {
        String sql = "SELECT * FROM personas WHERE codigo_persona = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, codigo);
        ResultSet rs = ps.executeQuery();
        Persona persona = null;
        if (rs.next()) {
            persona = construir(rs);
        }
        rs.close();
        ps.close();
        return persona;
    }

    public Cliente buscarCliente(String codigo) throws SQLException {
        Persona p = buscar(codigo);
        if (p instanceof Cliente) {
            return (Cliente) p;
        }
        return null;
    }

    public int registrar(Persona persona) throws SQLException {
        PreparedStatement ps;
        if (persona.esEmpleado()) {
            Empleado e = (Empleado) persona;
            String sql = "INSERT INTO personas (codigo_persona, nombre, numero_telefono, es_empleado, "
                    + "cargo, pago_por_dia, dias_trabajados, bono) VALUES (?, ?, ?, 1, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, e.getCodigoPersona());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getNumeroTelefono());
            ps.setString(4, e.getCargo());
            ps.setDouble(5, e.getPagoPorDia());
            ps.setInt(6, e.getDiasTrabajados());
            ps.setDouble(7, e.getBono());
        } else {
            Cliente c = (Cliente) persona;
            String sql = "INSERT INTO personas (codigo_persona, nombre, numero_telefono, es_empleado, "
                    + "zona_residencia, deuda_total) VALUES (?, ?, ?, 0, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, c.getCodigoPersona());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getNumeroTelefono());
            ps.setString(4, c.getZonaResidencia());
            ps.setDouble(5, c.getDeudaTotal());
        }
        int filas = ps.executeUpdate();
        ps.close();
        return filas;
    }

    public int actualizar(Persona persona) throws SQLException {
        PreparedStatement ps;
        if (persona.esEmpleado()) {
            Empleado e = (Empleado) persona;
            String sql = "UPDATE personas SET nombre = ?, numero_telefono = ?, cargo = ?, pago_por_dia = ?, "
                    + "dias_trabajados = ?, bono = ? WHERE codigo_persona = ? AND es_empleado = 1";
            ps = connection.prepareStatement(sql);
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getNumeroTelefono());
            ps.setString(3, e.getCargo());
            ps.setDouble(4, e.getPagoPorDia());
            ps.setInt(5, e.getDiasTrabajados());
            ps.setDouble(6, e.getBono());
            ps.setString(7, e.getCodigoPersona());
        } else {
            Cliente c = (Cliente) persona;
            String sql = "UPDATE personas SET nombre = ?, numero_telefono = ?, zona_residencia = ?, "
                    + "deuda_total = ? WHERE codigo_persona = ? AND es_empleado = 0";
            ps = connection.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getNumeroTelefono());
            ps.setString(3, c.getZonaResidencia());
            ps.setDouble(4, c.getDeudaTotal());
            ps.setString(5, c.getCodigoPersona());
        }
        int filas = ps.executeUpdate();
        ps.close();
        return filas;
    }

    public int eliminar(String codigo) throws SQLException {
        String sql = "DELETE FROM personas WHERE codigo_persona = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, codigo);
        int filas = ps.executeUpdate();
        ps.close();
        return filas;
    }

    public int contarVentas(String codigoCliente) throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM ventas WHERE Codigo_Cliente = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, codigoCliente);
        ResultSet rs = ps.executeQuery();
        int total = 0;
        if (rs.next()) {
            total = rs.getInt("total");
        }
        rs.close();
        ps.close();
        return total;
    }
}
