package controlador;

import modelo.Empleado;
import modelo.IReportable;
import modelo.Movimiento;
import modelo.Persona;
import modelo.Venta;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporteController {
    private VentaController ventaController;
    private GastoFijoController gastoController;
    private PersonaController personaController;

    public ReporteController(VentaController ventaController, GastoFijoController gastoController,
                             PersonaController personaController) {
        this.ventaController = ventaController;
        this.gastoController = gastoController;
        this.personaController = personaController;
    }

    public List<Movimiento> obtenerMovimientos() throws SQLException {
        List<Movimiento> movimientos = new ArrayList<>();
        movimientos.addAll(ventaController.listar());
        movimientos.addAll(gastoController.listar());
        return movimientos;
    }

    public double calcularIngresos(List<Movimiento> movimientos) {
        double total = 0;
        for (Movimiento m : movimientos) {
            if (m.esIngreso()) {
                total += m.calcularTotal();
            }
        }
        return total;
    }

    public double calcularEgresos(List<Movimiento> movimientos) {
        double total = 0;
        for (Movimiento m : movimientos) {
            if (!m.esIngreso()) {
                total += m.calcularTotal();
            }
        }
        return total;
    }

    public double calcularCobrado(List<Venta> ventas) {
        double total = 0;
        for (Venta v : ventas) {
            total += v.getMontoPagado();
        }
        return total;
    }

    public double calcularPendiente(List<Venta> ventas) {
        double total = 0;
        for (Venta v : ventas) {
            total += v.calcularDeuda();
        }
        return total;
    }

    public List<IReportable> obtenerPlanilla() throws SQLException {
        List<IReportable> planilla = new ArrayList<>();
        for (Persona p : personaController.listar()) {
            if (p instanceof Empleado) {
                planilla.add((Empleado) p);
            }
        }
        return planilla;
    }

    public double sumarReporte(List<IReportable> items) {
        double total = 0;
        for (IReportable r : items) {
            total += r.reporteMonto();
        }
        return total;
    }
}
