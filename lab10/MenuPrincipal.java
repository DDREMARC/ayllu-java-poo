import java.sql.DriverManager;
import java.sql.SQLException;

public class MenuPrincipal {
    public static void main(String[] args) {

        try {
            Ventas.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ayllu_pollos", "root", ""); //COLOCAR TU XAMP
            System.out.println("Conexión exitosa");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
            return;
        }

        int opcion = 0;

        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Administrar ventas");
            System.out.println("2. Administrar personas (clientes y empleados)");
            System.out.println("3. Administrar gastos fijos (próximamente)");
            System.out.println("0. Salir");

            opcion = Ventas.leerEntero("\nSeleccione una opción: ");

            switch (opcion) {
                case 1:
                    Ventas.menuVentas();
                    break;
                case 2:
                    Personas.menuPersonas();
                    break;
                case 3:
                    System.out.println("Esta tabla todavía no está lista.");
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 0);

        // Cerrar recursos compartidos al salir del programa
        try {
            if (Ventas.conexion != null) {
                Ventas.conexion.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
        Ventas.sc.close();
    }
}
