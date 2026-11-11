package controlador;

import modelo.GastoFijo;
import modelo.TipoGasto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GastoFijoController {
    private Connection connection;

    private static final String SELECT_BASE =
            "SELECT g.id_gasto, g.fecha, g.monto, t.id_tipo, t.nombre "
            + "FROM gasto_fijo g INNER JOIN tipo_gasto t ON g.id_tipo = t.id_tipo ";

    public GastoFijoController(Connection connection) {
        this.connection = connection;
    }

    private GastoFijo construir(ResultSet rs) throws SQLException {
        TipoGasto tipo = new TipoGasto(rs.getInt("id_tipo"), rs.getString("nombre"));
        return new GastoFijo(rs.getInt("id_gasto"), rs.getString("fecha"), tipo, rs.getDouble("monto"));
    }

    public List<TipoGasto> listarTipos() throws SQLException {
        List<TipoGasto> tipos = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT id_tipo, nombre FROM tipo_gasto ORDER BY id_tipo");
            rs = ps.executeQuery();
            while (rs.next()) {
                tipos.add(new TipoGasto(rs.getInt("id_tipo"), rs.getString("nombre")));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return tipos;
    }

    public TipoGasto buscarTipo(int idTipo) throws SQLException {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT id_tipo, nombre FROM tipo_gasto WHERE id_tipo = ?");
            ps.setInt(1, idTipo);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new TipoGasto(rs.getInt("id_tipo"), rs.getString("nombre"));
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

    public List<GastoFijo> listar() throws SQLException {
        List<GastoFijo> lista = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SELECT_BASE + "ORDER BY g.fecha, g.id_gasto");
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

    public GastoFijo buscar(int idGasto) throws SQLException {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SELECT_BASE + "WHERE g.id_gasto = ?");
            ps.setInt(1, idGasto);
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

    public int registrar(GastoFijo gasto) throws SQLException {
        String sql = "INSERT INTO gasto_fijo (id_tipo, fecha, monto) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, gasto.getTipo().getIdTipo());
            ps.setString(2, gasto.getFecha());
            ps.setDouble(3, gasto.getMonto());
            return ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public int actualizar(GastoFijo gasto) throws SQLException {
        String sql = "UPDATE gasto_fijo SET id_tipo = ?, fecha = ?, monto = ? WHERE id_gasto = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, gasto.getTipo().getIdTipo());
            ps.setString(2, gasto.getFecha());
            ps.setDouble(3, gasto.getMonto());
            ps.setInt(4, gasto.getId());
            return ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public int eliminar(int idGasto) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("DELETE FROM gasto_fijo WHERE id_gasto = ?");
            ps.setInt(1, idGasto);
            return ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }
}
