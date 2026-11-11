package vista;

import controlador.ReporteController;
import controlador.VentaController;
import modelo.IReportable;
import modelo.Movimiento;
import modelo.Venta;

import java.sql.SQLException;
import java.util.List;

public class ReporteView extends VistaBase {
    private ReporteController reporteController;
    private VentaController ventaController;

    public ReporteView(Lector lector, ReporteController reporteController, VentaController ventaController) {
        super(lector);
        this.reporteController = reporteController;
        this.ventaController = ventaController;
    }

    @Override
    public void mostrarMenu() throws SQLException {
        int opcion;
        do {
            System.out.println("\n--- REPORTES ---");
            System.out.println("1. Movimientos y ganancia del negocio");
            System.out.println("2. Planilla de empleados");
            System.out.println("0. Regresar");
            opcion = getLector().leerEntero("Seleccione una opcion: ");
            switch (opcion) {
                case 1: reporteGanancia(); break;
                case 2: reportePlanilla(); break;
                case 0: break;
                default: System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
    }

    private void reporteGanancia() throws SQLException {
        List<Movimiento> movimientos = reporteController.obtenerMovimientos();
        if (movimientos.isEmpty()) {
            System.out.println("No hay movimientos registrados.");
            return;
        }
        System.out.println("\nMovimientos del negocio:");
        for (Movimiento m : movimientos) {
            System.out.println("   " + m.mostrarMovimiento());
        }
        List<Venta> ventas = ventaController.listar();
        double ingresos = reporteController.calcularIngresos(movimientos);
        double egresos = reporteController.calcularEgresos(movimientos);
        double cobrado = reporteController.calcularCobrado(ventas);
        double pendiente = reporteController.calcularPendiente(ventas);
        System.out.println();
        imprimirLinea("Total vendido:", ingresos);
        imprimirLinea("   - Cobrado:", cobrado);
        imprimirLinea("   - Pendiente:", pendiente);
        imprimirLinea("Total gastos fijos:", egresos);
        imprimirLinea("Ganancia real (caja):", cobrado - egresos);
        imprimirLinea("Ganancia proyectada:", ingresos - egresos);
    }

    private void reportePlanilla() throws SQLException {
        List<IReportable> planilla = reporteController.obtenerPlanilla();
        if (planilla.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        System.out.println("\nPlanilla del mes:");
        for (IReportable r : planilla) {
            System.out.println("   " + r.reporteTexto() + ": " + formatoMonto(r.reporteMonto()));
        }
        imprimirLinea("Total planilla:", reporteController.sumarReporte(planilla));
    }

    private void imprimirLinea(String etiqueta, double monto) {
        System.out.println(String.format("%-24s S/ %10.2f", etiqueta, monto));
    }
}
