package vista;

import conexion.DatabaseConnector;
import controlador.GastoFijoController;
import controlador.PersonaController;
import controlador.ReporteController;
import controlador.VentaController;

import java.sql.SQLException;
import java.util.Scanner;

public class MainView {

    public void ejecutar() {
        Scanner sc = new Scanner(System.in);
        Lector lector = new Lector(sc);
        DatabaseConnector conector = new DatabaseConnector();

        try {
            conector.abrirConexion();
            System.out.println("Conexion exitosa a la base de datos ayllu_pollos.");
        } catch (SQLException e) {
            System.out.println("No se pudo conectar a la base de datos: " + e.getMessage());
            sc.close();
            return;
        }

        PersonaController personaController = new PersonaController(conector.getConnection());
        VentaController ventaController = new VentaController(conector.getConnection());
        GastoFijoController gastoController = new GastoFijoController(conector.getConnection());
        ReporteController reporteController = new ReporteController(ventaController, gastoController, personaController);

        VistaBase personaView = new PersonaView(lector, personaController);
        VistaBase ventaView = new VentaView(lector, ventaController, personaController);
        VistaBase gastoView = new GastoFijoView(lector, gastoController);
        VistaBase reporteView = new ReporteView(lector, reporteController, ventaController);

        int opcion;
        do {
            System.out.println("\n===== AYLLU POLLOS - MENU PRINCIPAL =====");
            System.out.println("1. Administrar personas (clientes y empleados)");
            System.out.println("2. Administrar ventas");
            System.out.println("3. Administrar gastos fijos");
            System.out.println("4. Reportes");
            System.out.println("0. Salir");
            opcion = lector.leerEntero("Seleccione una opcion: ");

            try {
                switch (opcion) {
                    case 1: personaView.mostrarMenu(); break;
                    case 2: ventaView.mostrarMenu(); break;
                    case 3: gastoView.mostrarMenu(); break;
                    case 4: reporteView.mostrarMenu(); break;
                    case 0: System.out.println("Saliendo del sistema..."); break;
                    default: System.out.println("Opcion no valida.");
                }
            } catch (SQLException e) {
                System.out.println("Error en la base de datos: " + e.getMessage());
            }
        } while (opcion != 0);

        conector.cerrarConexion();
        sc.close();
    }
}
