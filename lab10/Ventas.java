import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Scanner;
import java.sql.ResultSet;

public class Ventas {
    static Connection conexion;
    static Scanner sc = new Scanner(System.in).useLocale(Locale.US);

    // Submenú de la tabla ventas. Lo llama MenuPrincipal una vez que ya
    // conectó a la base de datos (deja el valor en Ventas.conexion).
    public static void menuVentas() {
        int opcion = 0;

        do {
            System.out.println("--- MENÚ VENTAS ---");
            System.out.println("1. Registrar venta");
            System.out.println("2. Listar ventas");
            System.out.println("3. Buscar venta");
            System.out.println("4. Actualizar venta");
            System.out.println("5. Eliminar venta");
            System.out.println("0. Volver al menú principal");

            opcion = leerEntero("\nSeleccione una opción: ");

            switch (opcion) {
                case 1:
                    registrarVentas();
                    break;
                case 2:
                    listarVentas();
                    break;
                case 3:
                    buscarVentas();
                    break;
                case 4:
                    actualizarVentas();
                    break;
                case 5:
                    eliminarVentas();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    // Pide un número entero y no deja avanzar hasta que la entrada sea válida
    public static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (sc.hasNextInt()) {
                int valor = sc.nextInt();
                sc.nextLine();
                return valor;
            } else {
                System.out.println("Eso no es un número. Intenta de nuevo.");
                sc.nextLine();
            }
        }
    }

    public static float leerFloat(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (sc.hasNextFloat()) {
                float valor = sc.nextFloat();
                sc.nextLine();
                return valor;
            } else {
                System.out.println("Eso no es un número válido. Intenta de nuevo.");
                sc.nextLine();
            }
        }
    }

    public static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String valor = sc.nextLine();
            if (!valor.trim().isEmpty()) {
                return valor;
            } else {
                System.out.println("Este dato no puede quedar vacío. Intenta de nuevo.");
            }
        }
    }

    public static void registrarVentas() {
        float precio = leerFloat("Ingrese el precio: ");
        float peso = leerFloat("Ingrese el peso del pollo: ");
        float pagoPollo = leerFloat("Ingrese el pago del pollo: ");
        String fecha = leerTexto("Ingrese la fecha [AAAA-MM-DD]: ");
        String codigoCliente = leerTexto("Ingrese el codigo del cliente: ");

        try {
            Statement stmt = conexion.createStatement();
            String sql = "INSERT INTO ventas (Precio, Peso, Pago_pollo, Fecha, Codigo_Cliente) VALUES ("
                    + precio + ", " + peso + ", " + pagoPollo + ", '" + fecha + "', '" + codigoCliente + "')";
            stmt.executeUpdate(sql);
            System.out.println("Venta registrada");
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void listarVentas() {
        try {
            Statement stmt = conexion.createStatement();
            String sql = "SELECT * FROM ventas";
            ResultSet rs = stmt.executeQuery(sql);

            if (!rs.isBeforeFirst()) {
                System.out.println("No hay ventas registradas todavía.");
            }
            while (rs.next()) {
                mostrarVenta(
                        rs.getInt("Codigo_venta"),
                        rs.getFloat("precio"),
                        rs.getFloat("peso"),
                        rs.getFloat("pago_pollo"),
                        rs.getString("fecha"),
                        rs.getString("codigo_cliente")
                );
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void buscarVentas() {
        int codigo_venta = leerEntero("Ingrese el codigo de venta: ");
        try {
            Statement stmt = conexion.createStatement();
            String sql = "SELECT * FROM ventas WHERE Codigo_venta = " + codigo_venta;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                mostrarVenta(
                        rs.getInt("Codigo_venta"),
                        rs.getFloat("precio"),
                        rs.getFloat("peso"),
                        rs.getFloat("pago_pollo"),
                        rs.getString("fecha"),
                        rs.getString("codigo_cliente")
                );
            } else {
                System.out.println("No se encontró el registro de venta " + codigo_venta);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void actualizarVentas() {
        int id = leerEntero("Ingrese el codigo de venta: ");
        float pagoPollo = leerFloat("Ingrese el nuevo monto a pagar del pollo: ");
        try {
            Statement stmt = conexion.createStatement();
            String sql = "UPDATE ventas SET Pago_pollo = " + pagoPollo + " WHERE Codigo_venta = " + id;
            int filas = stmt.executeUpdate(sql);

            if (filas > 0) {
                System.out.println("Venta actualizada");
            } else {
                System.out.println("No se hizo la actualización");
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void eliminarVentas() {
        int id = leerEntero("Ingrese el código de la venta a eliminar: ");
        try {
            Statement stmt = conexion.createStatement();
            String sql = "DELETE FROM ventas WHERE Codigo_venta = " + id;
            int filas = stmt.executeUpdate(sql);
            if (filas > 0) {
                System.out.println("Venta eliminada");
            } else {
                System.out.println("No se encontró la venta");
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void mostrarVenta(int codigoVenta, float precio, float peso, float pagoPollo,
                                     String fecha, String codigoCliente) {
        float total = peso * precio;
        float deuda = total - pagoPollo;

        System.out.println("Codigo de venta: " + codigoVenta);
        System.out.println("Precio: " + precio);
        System.out.println("Peso: " + peso);
        System.out.println("Pago de pollo: " + pagoPollo);
        System.out.println("Fecha: " + fecha);
        System.out.println("Codigo de cliente: " + codigoCliente);
        System.out.println("Total de la venta: " + total);
        System.out.println("Deuda pendiente: " + deuda);
    }
}
