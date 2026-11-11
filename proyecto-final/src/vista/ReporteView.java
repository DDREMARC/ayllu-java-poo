package vista;

import controlador.ReporteController;
import controlador.VentaController;
import modelo.Empleado;
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
            System.out.println("3. Flujo de caja detallado");
            System.out.println("0. Regresar");
            opcion = getLector().leerEntero("Seleccione una opcion: ");
            switch (opcion) {
                case 1: reporteGanancia(); break;
                case 2: reportePlanilla(); break;
                case 3: reporteFlujoDeCaja(); break;
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
        double vendido = reporteController.calcularIngresos(movimientos);
        double gastos = reporteController.calcularEgresos(movimientos);
        double cobrado = reporteController.calcularCobrado(ventas);
        double pendiente = reporteController.calcularPendiente(ventas);
        double planilla = reporteController.calcularPlanilla(reporteController.obtenerEmpleados());
        double caja = reporteController.sumarFlujo(reporteController.obtenerFlujoDeCaja());
        System.out.println();
        imprimirLinea("Total vendido:", vendido);
        imprimirLinea("   - Cobrado:", cobrado);
        imprimirLinea("   - Pendiente:", pendiente);
        imprimirLinea("Gastos fijos:", gastos);
        imprimirLinea("Planilla de empleados:", planilla);
        imprimirLinea("Ganancia real (caja):", caja);
        imprimirLinea("Ganancia proyectada:", vendido - gastos - planilla);
    }

    private void reportePlanilla() throws SQLException {
        List<Empleado> empleados = reporteController.obtenerEmpleados();
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        System.out.println("\nPlanilla del mes:");
        for (Empleado e : empleados) {
            System.out.println("   " + e.getNombre() + " (" + e.getCargo() + "): " + formatoMonto(e.calcularPago()));
        }
        imprimirLinea("Total planilla:", reporteController.calcularPlanilla(empleados));
    }

    private void reporteFlujoDeCaja() throws SQLException {
        List<IReportable> flujo = reporteController.obtenerFlujoDeCaja();
        if (flujo.isEmpty()) {
            System.out.println("No hay datos para el flujo de caja.");
            return;
        }
        System.out.println("\nFlujo de caja (entra + / sale -):");
        for (IReportable r : flujo) {
            double monto = r.reporteMonto();
            String signo = monto >= 0 ? "+" : "-";
            System.out.println("   " + signo + " " + formatoMonto(Math.abs(monto)) + "   " + r.reporteTexto());
        }
        imprimirLinea("Saldo de caja:", reporteController.sumarFlujo(flujo));
    }

    private void imprimirLinea(String etiqueta, double monto) {
        System.out.println(String.format("%-24s S/ %10.2f", etiqueta, monto));
    }
}
