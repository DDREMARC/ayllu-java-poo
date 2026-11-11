package vista;

import controlador.PersonaController;
import controlador.VentaController;
import modelo.Cliente;
import modelo.Venta;

import java.sql.SQLException;
import java.util.List;

public class VentaView extends VistaBase {
    private VentaController ventaController;
    private PersonaController personaController;

    public VentaView(Lector lector, VentaController ventaController, PersonaController personaController) {
        super(lector);
        this.ventaController = ventaController;
        this.personaController = personaController;
    }

    @Override
    public void mostrarMenu() throws SQLException {
        int opcion;
        do {
            System.out.println("\n--- VENTAS ---");
            System.out.println("1. Registrar venta");
            System.out.println("2. Listar ventas");
            System.out.println("3. Buscar venta por codigo");
            System.out.println("4. Actualizar venta");
            System.out.println("5. Eliminar venta");
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

    private Cliente pedirCliente() throws SQLException {
        String codigo = getLector().leerTexto("Codigo del cliente: ");
        Cliente cliente = personaController.buscarCliente(codigo);
        if (cliente == null) {
            System.out.println("No existe un cliente con el codigo " + codigo + ".");
        }
        return cliente;
    }

    private double pedirMontoPagado(double total) {
        while (true) {
            double pagado = getLector().leerDecimal("Monto pagado: ");
            if (pagado <= total) {
                return pagado;
            }
            System.out.println("El monto pagado no puede ser mayor al total (" + formatoMonto(total) + ").");
        }
    }

    private void registrar() throws SQLException {
        Lector lector = getLector();
        Cliente cliente = pedirCliente();
        if (cliente == null) {
            return;
        }
        String fecha = lector.leerFecha("Fecha (AAAA-MM-DD): ");
        double peso = lector.leerDecimal("Peso en kg: ");
        double precio = lector.leerDecimal("Precio por kg: ");
        double total = peso * precio;
        System.out.println("Total de la venta: " + formatoMonto(total));
        double pagado = pedirMontoPagado(total);
        Venta venta = new Venta(0, fecha, cliente, peso, precio, pagado);
        int filas = ventaController.registrar(venta);
        mostrarResultado(filas, "Venta registrada correctamente.");
        if (filas > 0 && venta.calcularDeuda() > 0) {
            System.out.println("El cliente queda debiendo " + formatoMonto(venta.calcularDeuda())
                    + " por esta venta (se sumo a su deuda total).");
        }
    }

    private void listar() throws SQLException {
        List<Venta> lista = ventaController.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }
        System.out.println("\nTotal: " + lista.size() + " venta(s)");
        for (Venta v : lista) {
            v.mostrarVenta();
        }
    }

    private void buscar() throws SQLException {
        int codigo = getLector().leerEntero("Codigo de la venta: ");
        Venta v = ventaController.buscar(codigo);
        if (v == null) {
            System.out.println("No se encontro la venta " + codigo + ".");
        } else {
            v.mostrarVenta();
        }
    }

    private void actualizar() throws SQLException {
        Lector lector = getLector();
        int codigo = lector.leerEntero("Codigo de la venta a actualizar: ");
        Venta actual = ventaController.buscar(codigo);
        if (actual == null) {
            System.out.println("No se encontro la venta " + codigo + ".");
            return;
        }
        System.out.println("Datos actuales:");
        actual.mostrarVenta();
        System.out.println("Ingrese los nuevos datos:");
        Cliente cliente = pedirCliente();
        if (cliente == null) {
            return;
        }
        actual.setCliente(cliente);
        actual.setFecha(lector.leerFecha("Fecha (AAAA-MM-DD): "));
        actual.setPesoKg(lector.leerDecimal("Peso en kg: "));
        actual.setPrecioKg(lector.leerDecimal("Precio por kg: "));
        System.out.println("Nuevo total: " + formatoMonto(actual.calcularTotal()));
        actual.setMontoPagado(pedirMontoPagado(actual.calcularTotal()));
        mostrarResultado(ventaController.actualizar(actual), "Venta actualizada correctamente.");
    }

    private void eliminar() throws SQLException {
        Lector lector = getLector();
        int codigo = lector.leerEntero("Codigo de la venta a eliminar: ");
        Venta v = ventaController.buscar(codigo);
        if (v == null) {
            System.out.println("No se encontro la venta " + codigo + ".");
            return;
        }
        v.mostrarVenta();
        if (lector.confirmar("Confirma eliminar esta venta?")) {
            mostrarResultado(ventaController.eliminar(codigo), "Venta eliminada correctamente.");
        } else {
            System.out.println("Operacion cancelada.");
        }
    }
}
