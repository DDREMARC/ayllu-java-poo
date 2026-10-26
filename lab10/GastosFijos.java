import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class gastosfijos {

    public static void menugastosfijos() {
        int opcion;
        do {
            System.out.println("\n--- ADMINISTRAR GASTOS FIJOS ---");
            System.out.println("1. Registrar gasto");
            System.out.println("2. Listar gastos");
            System.out.println("3. Buscar gasto por id");
            System.out.println("4. Actualizar gasto");
            System.out.println("5. Eliminar gasto");
            System.out.println("0. Regresar al menú principal");

            opcion = Ventas.leerEntero("\nSeleccione una opción: ");

            switch (opcion) {
                case 1: registrar(); break;
                case 2: listar(); break;
                case 3: buscar(); break;
                case 4: actualizar(); break;
                case 5: eliminar(); break;
                case 0: System.out.println("Regresando al menú principal..."); break;
                default: System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    // INSERT
    static void registrar() {
        int idTipo = Ventas.leerEntero("Id del tipo de gasto (1=Implementos, 2=Combustible y peajes, 3=Agua y gas): ");
        float monto = Ventas.leerFloat("Monto: ");

        String sql = "INSERT INTO gasto_fijo (id_tipo, monto) VALUES (" + idTipo + ", " + monto + ")";
        ejecutarCambio(sql, "Registro insertado correctamente.", "No se realizó ningún cambio.");
    }

    // SELECT (todos)
    static void listar() {
        try {
            Statement st = Ventas.conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM gasto_fijo");
            boolean hayDatos = false;
            while (rs.next()) {
                hayDatos = true;
                mostrarGasto(rs);
            }
            if (!hayDatos) {
                System.out.println("No hay registros.");
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    // SELECT (por id)
    static void buscar() {
        int id = Ventas.leerEntero("Id del gasto: ");
        try {
            Statement st = Ventas.conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM gasto_fijo WHERE id_gasto = " + id);
            if (rs.next()) {
                mostrarGasto(rs);
            } else {
                System.out.println("No se encontró el registro solicitado.");
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    // UPDATE
    static void actualizar() {
        int id = Ventas.leerEntero("Id del gasto a actualizar: ");
        int idTipo = Ventas.leerEntero("Nuevo id de tipo de gasto: ");
        float monto = Ventas.leerFloat("Nuevo monto: ");

        String sql = "UPDATE gasto_fijo SET id_tipo = " + idTipo + ", monto = " + monto
                + " WHERE id_gasto = " + id;
        ejecutarCambio(sql, "Registro actualizado correctamente.", "No se encontró el registro solicitado.");
    }

    // DELETE
    static void eliminar() {
        int id = Ventas.leerEntero("Id del gasto a eliminar: ");
        String sql = "DELETE FROM gasto_fijo WHERE id_gasto = " + id;
        ejecutarCambio(sql, "Registro eliminado correctamente.", "No se encontró el registro solicitado.");
    }

    // Muestra los datos de la fila actual del ResultSet
    static void mostrarGasto(ResultSet rs) throws SQLException {
        System.out.println("ID: " + rs.getInt("id_gasto")
                + " | Tipo: " + rs.getInt("id_tipo")
                + " | Fecha: " + rs.getDate("fecha")
                + " | Monto: " + rs.getDouble("monto"));
    }

    // Ejecuta INSERT, UPDATE o DELETE y muestra el resultado según las filas afectadas
    static void ejecutarCambio(String sql, String mensajeOk, String mensajeNada) {
        try {
            Statement st = Ventas.conexion.createStatement();
            int filas = st.executeUpdate(sql);
            if (filas > 0) {
                System.out.println(mensajeOk);
            } else {
                System.out.println(mensajeNada);
            }
            st.close();
        } catch (SQLException e) {
            System.out.println("Error en la operación: " + e.getMessage());
        }
    }
}