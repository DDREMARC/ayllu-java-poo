package vista;

import controlador.GastoFijoController;
import modelo.GastoFijo;
import modelo.TipoGasto;

import java.sql.SQLException;
import java.util.List;

public class GastoFijoView extends VistaBase {
    private GastoFijoController gastoController;

    public GastoFijoView(Lector lector, GastoFijoController gastoController) {
        super(lector);
        this.gastoController = gastoController;
    }

    @Override
    public void mostrarMenu() throws SQLException {
        int opcion;
        do {
            System.out.println("\n--- GASTOS FIJOS ---");
            System.out.println("1. Registrar gasto");
            System.out.println("2. Listar gastos");
            System.out.println("3. Buscar gasto por id");
            System.out.println("4. Actualizar gasto");
            System.out.println("5. Eliminar gasto");
            System.out.println("0. Regresar");
            opcion = getLector().leerEntero("Seleccione una opcion: ");
            switch (opcion) {
                case 1: registrar(); break;
                case 2: listar(); break;
                case 3: buscar(); break;
                case 4: actualizar(); break;
                case 5: eliminar(); break;
                case 0: break;
                default: System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
    }

    private TipoGasto pedirTipoGasto() throws SQLException {
        System.out.println("Tipos de gasto disponibles:");
        for (TipoGasto t : gastoController.listarTipos()) {
            System.out.println("   " + t.getIdTipo() + ". " + t.getNombre());
        }
        int id = getLector().leerEntero("Id del tipo de gasto: ");
        TipoGasto tipo = gastoController.buscarTipo(id);
        if (tipo == null) {
            System.out.println("No existe el tipo de gasto " + id + ".");
        }
        return tipo;
    }

    private void registrar() throws SQLException {
        Lector lector = getLector();
        TipoGasto tipo = pedirTipoGasto();
        if (tipo == null) {
            return;
        }
        String fecha = lector.leerFecha("Fecha (AAAA-MM-DD): ");
        double monto = lector.leerDecimal("Monto: ");
        mostrarResultado(gastoController.registrar(new GastoFijo(0, fecha, tipo, monto)),
                "Gasto registrado correctamente.");
    }

    private void listar() throws SQLException {
        List<GastoFijo> lista = gastoController.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay gastos registrados.");
            return;
        }
        System.out.println("\nTotal: " + lista.size() + " gasto(s)");
        for (GastoFijo g : lista) {
            g.mostrarGasto();
        }
    }

    private void buscar() throws SQLException {
        int id = getLector().leerEntero("Id del gasto: ");
        GastoFijo g = gastoController.buscar(id);
        if (g == null) {
            System.out.println("No se encontro el gasto " + id + ".");
        } else {
            g.mostrarGasto();
        }
    }

    private void actualizar() throws SQLException {
        Lector lector = getLector();
        int id = lector.leerEntero("Id del gasto a actualizar: ");
        GastoFijo actual = gastoController.buscar(id);
        if (actual == null) {
            System.out.println("No se encontro el gasto " + id + ".");
            return;
        }
        System.out.println("Datos actuales:");
        actual.mostrarGasto();
        System.out.println("Ingrese los nuevos datos:");
        TipoGasto tipo = pedirTipoGasto();
        if (tipo == null) {
            return;
        }
        actual.setTipo(tipo);
        actual.setFecha(lector.leerFecha("Fecha (AAAA-MM-DD): "));
        actual.setMonto(lector.leerDecimal("Monto: "));
        mostrarResultado(gastoController.actualizar(actual), "Gasto actualizado correctamente.");
    }

    private void eliminar() throws SQLException {
        Lector lector = getLector();
        int id = lector.leerEntero("Id del gasto a eliminar: ");
        GastoFijo g = gastoController.buscar(id);
        if (g == null) {
            System.out.println("No se encontro el gasto " + id + ".");
            return;
        }
        g.mostrarGasto();
        if (lector.confirmar("Confirma eliminar este gasto?")) {
            mostrarResultado(gastoController.eliminar(id), "Gasto eliminado correctamente.");
        } else {
            System.out.println("Operacion cancelada.");
        }
    }
}
