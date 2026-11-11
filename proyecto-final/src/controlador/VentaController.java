package controlador;

import modelo.Cliente;
import modelo.Venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentaController {
    private Connection connection;

    private static final String SELECT_BASE =
            "SELECT v.Codigo_venta, v.Precio, v.Peso, v.Pago_pollo, v.Fecha, "
            + "p.codigo_persona, p.nombre, p.numero_telefono, p.zona_residencia, p.deuda_total "
            + "FROM ventas v INNER JOIN personas p ON v.Codigo_Cliente = p.codigo_persona ";

    public VentaController(Connection connection) {
        this.connection = connection;
    }

    private Venta construir(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente(rs.getString("codigo_persona"), rs.getString("nombre"),
                rs.getString("numero_telefono"), rs.getString("zona_residencia"), rs.getDouble("deuda_total"));
        return new Venta(rs.getInt("Codigo_venta"), rs.getString("Fecha"), cliente,
                rs.getDouble("Peso"), rs.getDouble("Precio"), rs.getDouble("Pago_pollo"));
    }

    public List<Venta> listar() throws SQLException {
        List<Venta> lista = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SELECT_BASE + "ORDER BY v.Fecha, v.Codigo_venta");
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(construir(rs));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return lista;
    }

    public Venta buscar(int codigoVenta) throws SQLException {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SELECT_BASE + "WHERE v.Codigo_venta = ?");
            ps.setInt(1, codigoVenta);
            rs = ps.executeQuery();
            if (rs.next()) {
                return construir(rs);
            }
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
    }

    public int registrar(Venta venta) throws SQLException {
        boolean autoCommitOriginal = connection.getAutoCommit();
        connection.setAutoCommit(false);
        try {
            int filas = insertarVenta(venta);
            if (filas > 0 && venta.calcularDeuda() > 0) {
                ajustarDeuda(venta.getCliente().getCodigoPersona(), venta.calcularDeuda());
            }
            connection.commit();
            return filas;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(autoCommitOriginal);
        }
    }

    public int actualizar(Venta venta) throws SQLException {
        Venta anterior = buscar(venta.getId());
        if (anterior == null) {
            return 0;
        }
        boolean autoCommitOriginal = connection.getAutoCommit();
        connection.setAutoCommit(false);
        try {
            int filas = modificarVenta(venta);
            if (filas > 0) {
                ajustarDeuda(anterior.getCliente().getCodigoPersona(), -anterior.calcularDeuda());
                ajustarDeuda(venta.getCliente().getCodigoPersona(), venta.calcularDeuda());
            }
            connection.commit();
            return filas;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(autoCommitOriginal);
        }
    }

    public int eliminar(int codigoVenta) throws SQLException {
        Venta anterior = buscar(codigoVenta);
        if (anterior == null) {
            return 0;
        }
        boolean autoCommitOriginal = connection.getAutoCommit();
        connection.setAutoCommit(false);
        try {
            int filas = borrarVenta(codigoVenta);
            if (filas > 0) {
                ajustarDeuda(anterior.getCliente().getCodigoPersona(), -anterior.calcularDeuda());
            }
            connection.commit();
            return filas;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(autoCommitOriginal);
        }
    }

    private int insertarVenta(Venta venta) throws SQLException {
        String sql = "INSERT INTO ventas (Precio, Peso, Pago_pollo, Fecha, Codigo_Cliente) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setDouble(1, venta.getPrecioKg());
            ps.setDouble(2, venta.getPesoKg());
            ps.setDouble(3, venta.getMontoPagado());
            ps.setString(4, venta.getFecha());
            ps.setString(5, venta.getCliente().getCodigoPersona());
            return ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    private int modificarVenta(Venta venta) throws SQLException {
        String sql = "UPDATE ventas SET Precio = ?, Peso = ?, Pago_pollo = ?, Fecha = ?, Codigo_Cliente = ? "
                + "WHERE Codigo_venta = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setDouble(1, venta.getPrecioKg());
            ps.setDouble(2, venta.getPesoKg());
            ps.setDouble(3, venta.getMontoPagado());
            ps.setString(4, venta.getFecha());
            ps.setString(5, venta.getCliente().getCodigoPersona());
            ps.setInt(6, venta.getId());
            return ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    private int borrarVenta(int codigoVenta) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("DELETE FROM ventas WHERE Codigo_venta = ?");
            ps.setInt(1, codigoVenta);
            return ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    private void ajustarDeuda(String codigoCliente, double diferencia) throws SQLException {
        if (diferencia == 0) {
            return;
        }
        String sql = "UPDATE personas SET deuda_total = GREATEST(deuda_total + ?, 0) WHERE codigo_persona = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setDouble(1, diferencia);
            ps.setString(2, codigoCliente);
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }
}
