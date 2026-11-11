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

    public List<Empleado> obtenerEmpleados() throws SQLException {
        List<Empleado> empleados = new ArrayList<>();
        for (Persona p : personaController.listar()) {
            if (p instanceof Empleado) {
                empleados.add((Empleado) p);
            }
        }
        return empleados;
    }

    public double calcularPlanilla(List<Empleado> empleados) {
        double total = 0;
        for (Empleado e : empleados) {
            total += e.calcularPago();
        }
        return total;
    }

    public List<IReportable> obtenerFlujoDeCaja() throws SQLException {
        List<IReportable> flujo = new ArrayList<>();
        flujo.addAll(ventaController.listar());
        flujo.addAll(gastoController.listar());
        flujo.addAll(obtenerEmpleados());
        return flujo;
    }

    public double sumarFlujo(List<IReportable> flujo) {
        double total = 0;
        for (IReportable r : flujo) {
            total += r.reporteMonto();
        }
        return total;
    }
}
