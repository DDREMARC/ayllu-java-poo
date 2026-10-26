// Los 3 actores del paquete java.sql vistos en el hub de la Semana 10 (tabla "Los 5 actores de java.sql"):
// Statement envía SQL al DBMS, ResultSet guarda la tabla de respuesta, SQLException es la excepción checked.
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Tabla única que guarda clientes y empleados.
// La columna es_empleado (INT, 0 o 1) decide a qué grupo pertenece la fila:
//   es_empleado = 1 → empleado (usa cargo, pago_por_dia, dias_trabajados, bono)
//   es_empleado = 0 → cliente  (usa zona_residencia, deuda_total)
// Los campos del grupo que no aplica se guardan como NULL en MySQL.
public class Personas {

    public static void menuPersonas() {
        int opcion = 0;

        do {
            System.out.println("--- MENÚ PERSONAS ---");
            System.out.println("1. Registrar persona");
            System.out.println("2. Listar personas");
            System.out.println("3. Buscar persona");
            System.out.println("4. Actualizar persona");
            System.out.println("5. Eliminar persona");
            System.out.println("0. Volver al menú principal");

            opcion = Ventas.leerEntero("\nSeleccione una opción: ");

            switch (opcion) {
                case 1:
                    registrarPersona();
                    break;
                case 2:
                    listarPersonas();
                    break;
                case 3:
                    buscarPersona();
                    break;
                case 4:
                    actualizarPersona();
                    break;
                case 5:
                    eliminarPersona();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    public static void registrarPersona() {
        String codigo   = Ventas.leerTexto("Ingrese el código de la persona: ");
        String nombre   = Ventas.leerTexto("Ingrese el nombre: ");
        String telefono = Ventas.leerTexto("Ingrese el número de teléfono: ");

        // El booleano decide la rama (empleado o cliente).
        int tipo = Ventas.leerEntero("¿Es empleado? (1 = sí, 0 = no): ");

        try {
            // createStatement() y executeUpdate() → hub S10, mismo patrón que el ejemplo de INSERT del profe.
            Statement stmt = Ventas.conexion.createStatement();
            String sql;

            if (tipo == 1) {
                String cargo   = Ventas.leerTexto("Ingrese el cargo: ");
                float  pagoDia = Ventas.leerFloat("Ingrese el pago por día: ");
                int    dias    = Ventas.leerEntero("Ingrese los días trabajados: ");
                float  bono    = Ventas.leerFloat("Ingrese el bono (0 si no tiene): ");

                sql = "INSERT INTO personas (codigo_persona, nombre, numero_telefono, es_empleado, cargo, pago_por_dia, dias_trabajados, bono) VALUES ('"
                        + codigo + "', '" + nombre + "', '" + telefono + "', 1, '"
                        + cargo + "', " + pagoDia + ", " + dias + ", " + bono + ")";
            } else {
                String zona  = Ventas.leerTexto("Ingrese la zona de residencia: ");
                float  deuda = Ventas.leerFloat("Ingrese la deuda inicial (0 si no tiene): ");

                sql = "INSERT INTO personas (codigo_persona, nombre, numero_telefono, es_empleado, zona_residencia, deuda_total) VALUES ('"
                        + codigo + "', '" + nombre + "', '" + telefono + "', 0, '"
                        + zona + "', " + deuda + ")";
            }

            stmt.executeUpdate(sql);
            System.out.println("Persona registrada");
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void listarPersonas() {
        try {
            // executeQuery() devuelve un ResultSet → hub S10, "Ejecutar SELECT y leer ResultSet".
            Statement stmt = Ventas.conexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM personas");

            // Bandera para avisar si la tabla está vacía (rs.next() devuelve false si no hay filas → hub S10, callout "rs.next()").
            boolean hayFilas = false;
            while (rs.next()) {
                hayFilas = true;
                mostrarFila(rs);
            }
            if (!hayFilas) {
                System.out.println("No hay personas registradas todavía.");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            // SQLException es checked → el compilador obliga a capturarla (hub S10, "Manejo de excepciones").
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void buscarPersona() {
        String codigo = Ventas.leerTexto("Ingrese el código de la persona: ");

        try {
            Statement stmt = Ventas.conexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM personas WHERE codigo_persona = '" + codigo + "'");

            if (rs.next()) {
                mostrarFila(rs);
            } else {
                System.out.println("No se encontró la persona " + codigo);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void actualizarPersona() {
        String codigo = Ventas.leerTexto("Ingrese el código de la persona a actualizar: ");

        try {
            // Primero averiguamos si es cliente o empleado para saber qué campos actualizar.
            Statement stmtLee = Ventas.conexion.createStatement();
            ResultSet rs = stmtLee.executeQuery("SELECT es_empleado FROM personas WHERE codigo_persona = '" + codigo + "'");

            if (!rs.next()) {
                System.out.println("No se encontró la persona " + codigo);
                rs.close();
                stmtLee.close();
                return;
            }

            // rs.getInt() → hub S10, tabla "Métodos del ResultSet según tipo".
            int esEmpleado = rs.getInt("es_empleado");
            rs.close();
            stmtLee.close();

            Statement stmt = Ventas.conexion.createStatement();
            String sql;

            if (esEmpleado == 1) {
                int   dias = Ventas.leerEntero("Ingrese los nuevos días trabajados: ");
                float bono = Ventas.leerFloat("Ingrese el nuevo bono: ");
                sql = "UPDATE personas SET dias_trabajados = " + dias
                        + ", bono = " + bono + " WHERE codigo_persona = '" + codigo + "'";
            } else {
                float deuda = Ventas.leerFloat("Ingrese la nueva deuda total: ");
                sql = "UPDATE personas SET deuda_total = " + deuda + " WHERE codigo_persona = '" + codigo + "'";
            }

            int filas = stmt.executeUpdate(sql);
            if (filas > 0) {
                System.out.println("Persona actualizada");
            } else {
                System.out.println("No se hizo la actualización");
            }
            stmt.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void eliminarPersona() {
        String codigo = Ventas.leerTexto("Ingrese el código de la persona a eliminar: ");

        try {
            Statement stmt = Ventas.conexion.createStatement();
            int filas = stmt.executeUpdate("DELETE FROM personas WHERE codigo_persona = '" + codigo + "'");

            if (filas > 0) {
                System.out.println("Persona eliminada");
            } else {
                System.out.println("No se encontró la persona");
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Muestra una fila. Según es_empleado (0 o 1) imprime los campos de cliente o de empleado.
    public static void mostrarFila(ResultSet rs) throws SQLException {
        String codigo   = rs.getString("codigo_persona");
        String nombre   = rs.getString("nombre");
        String telefono = rs.getString("numero_telefono");
        int esEmpleado  = rs.getInt("es_empleado");

        System.out.println("Código:   " + codigo);
        System.out.println("Nombre:   " + nombre);
        System.out.println("Teléfono: " + telefono);

        if (esEmpleado == 1) {
            System.out.println("Tipo:            EMPLEADO");
            System.out.println("Cargo:           " + rs.getString("cargo"));
            System.out.println("Pago por día:    " + rs.getFloat("pago_por_dia"));
            System.out.println("Días trabajados: " + rs.getInt("dias_trabajados"));
            System.out.println("Bono:            " + rs.getFloat("bono"));
            float pagoTotal = rs.getInt("dias_trabajados") * rs.getFloat("pago_por_dia") + rs.getFloat("bono");
            System.out.println("Pago total:      " + pagoTotal);
        } else {
            System.out.println("Tipo:               CLIENTE");
            System.out.println("Zona de residencia: " + rs.getString("zona_residencia"));
            System.out.println("Deuda total:        " + rs.getFloat("deuda_total"));
        }
        System.out.println("---");
    }
}
