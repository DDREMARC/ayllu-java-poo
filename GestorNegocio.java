import java.time.LocalDate;
import java.util.ArrayList;

public class GestorNegocio {
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Empleado> empleados = new ArrayList<>();

    //LEO
    private ArrayList<Venta> ventas = new ArrayList<>();
    private ArrayList<GastoFijo> gastos = new ArrayList<>();
    private int siguienteIdVenta = 1;
    private int siguienteIdGasto = 1;

    public Cliente buscarCliente(String codigo) {
        for (Cliente cliente : clientes) {
            if (cliente.getCodigoPersona().equals(codigo)) {
                return cliente;
            }
        }
        return null;
    }

    public void registrarCliente(String nombre, String telefono, String codigo, String zonaResidencial, double deuda) {
        if (nombre.equals("") || telefono.equals("") || codigo.equals("") || zonaResidencial.equals("") || deuda < 0){
            System.out.println("Error: Datos incompletos o invalidos");
        } else if (buscarCliente(codigo) != null) {
            System.out.println("Error: Cliente ya existe");
        } else {
            clientes.add(new Cliente(nombre, telefono, codigo, zonaResidencial, deuda));
            System.out.println("Cliente registrado");
        }
    }

    public void mostrarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados...");
        }
        else {
            for (Cliente cliente : clientes) {
                cliente.mostrarDatos();
            }
        }
    }

    public void eliminarCliente(String codigo) {
        Cliente encontrado = buscarCliente(codigo);
        if (encontrado == null) {
            System.out.println("No se encontró el cliente");
        } else {
            // Las ventas que tenían a este cliente quedan sin cliente
            for (Venta venta : ventas) {
                if (venta.getCliente() == encontrado) {
                    venta.setCliente(null);
                }
            }
            clientes.remove(encontrado);
            System.out.println("Cliente eliminado");
        }
    }

    //==================================================================================================================
    // DAN: Empleados
    //==================================================================================================================

    public Empleado buscarEmpleado(String codigo) {
        for (Empleado empleado : empleados) {
            if (empleado.getCodigoPersona().equals(codigo)) {
                return empleado;
            }
        }
        return null;
    }

    // Registra un empleado en la lista (solo si los datos son válidos)
    public void registrarEmpleado(String nombre, String telefono, String codigo,
                                  String cargo, double pagoPorDia,
                                  int diasTrabajados, double bono) {
        if (nombre.equals("") || telefono.equals("") || codigo.equals("") || cargo.equals("")
                || pagoPorDia <= 0 || diasTrabajados < 0 || bono < 0) {
            System.out.println("Error: Datos incompletos o inválidos");
        } else if (buscarEmpleado(codigo) != null) {
            System.out.println("Error: Empleado ya existe");
        } else {
            empleados.add(new Empleado(nombre, telefono, codigo, cargo,
                    pagoPorDia, diasTrabajados, bono));
            System.out.println("Empleado registrado");
        }
    }

    public void eliminarEmpleado(String codigo) {
        Empleado encontrado = buscarEmpleado(codigo);
        if (encontrado == null) {
            System.out.println("No se encontró el empleado");
        } else {
            empleados.remove(encontrado);
            System.out.println("Empleado eliminado");
        }
    }

    // Muestra los empleados registrados
    public void mostrarEmpleados() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados...");
        } else {
            for (Empleado empleado : empleados) {
                System.out.println(empleado.reporteTexto());
            }
        }
    }

    //==================================================================================================================
    // LEO: Ventas y Gastos Fijos
    //==================================================================================================================

    public Venta buscarVenta(int id) {
        for (Venta venta : ventas) {
            if (venta.getId() == id) {
                return venta;
            }
        }
        return null;
    }

    // Registra una venta. Busca al cliente por código (si se indica) y avisa si no existe.
    public void registrarVenta(String descripcion, String codigoCliente, double pesoKg, double precioKg, double montoPagado) {
        if (descripcion.equals("") || pesoKg <= 0 || precioKg <= 0 || montoPagado < 0) {
            System.out.println("Error: Datos incompletos o inválidos");
            return;
        }

        double total = pesoKg * precioKg;
        if (montoPagado > total) {
            System.out.println("Error: El monto pagado no puede ser mayor al total de la venta");
            return;
        }

        Cliente cliente = null;
        if (!codigoCliente.equals("")) {
            cliente = buscarCliente(codigoCliente);
            if (cliente == null) {
                System.out.println("Cliente no encontrado, la venta se registrará sin cliente asociado");
            }
        }

        Venta venta = new Venta(siguienteIdVenta, LocalDate.now(), descripcion, cliente, pesoKg, precioKg, montoPagado);
        ventas.add(venta);
        siguienteIdVenta = siguienteIdVenta + 1;
        System.out.println("Venta registrada");
    }

    public void mostrarVentas() {
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas...");
        } else {
            for (Venta venta : ventas) {
                System.out.println(venta.reporteTexto());
            }
        }
    }

    // Modifica el monto pagado de una venta (registra un pago adicional)
    public void modificarVenta(int id, double nuevoMontoPagado) {
        Venta encontrada = buscarVenta(id);
        if (encontrada == null) {
            System.out.println("No se encontró la venta");
        } else if (nuevoMontoPagado < 0) {
            System.out.println("Error: El monto pagado no debe ser negativo");
        } else if (nuevoMontoPagado > encontrada.calcularTotal()) {
            System.out.println("Error: El monto pagado no puede ser mayor al total de la venta");
        } else {
            encontrada.setMontoPagado(nuevoMontoPagado);
            System.out.println("Venta modificada");
        }
    }

    public void eliminarVenta(int id) {
        Venta encontrada = buscarVenta(id);
        if (encontrada == null) {
            System.out.println("No se encontró la venta");
        } else {
            // La deuda de esta venta ya no cuenta para el cliente
            if (encontrada.getCliente() != null) {
                encontrada.getCliente().reducirDeuda(encontrada.calcularDeuda());
            }
            ventas.remove(encontrada);
            System.out.println("Venta eliminada");
        }
    }

    public GastoFijo buscarGasto(int id) {
        for (GastoFijo gasto : gastos) {
            if (gasto.getId() == id) {
                return gasto;
            }
        }
        return null;
    }

    public void registrarGasto(String descripcion, String tipo, double monto) {
        if (descripcion.equals("") || tipo.equals("") || monto <= 0) {
            System.out.println("Error: Datos incompletos o inválidos");
        } else {
            GastoFijo gasto = new GastoFijo(siguienteIdGasto, LocalDate.now(), descripcion, tipo, monto);
            // Si el tipo no era válido, GastoFijo lo deja en null: no se guarda
            if (gasto.getTipo() == null) {
                System.out.println("Error: El gasto no se registró");
            } else {
                gastos.add(gasto);
                siguienteIdGasto = siguienteIdGasto + 1;
                System.out.println("Gasto fijo registrado");
            }
        }
    }

    public void mostrarGastos() {
        if (gastos.isEmpty()) {
            System.out.println("No hay gastos registrados...");
        } else {
            for (GastoFijo gasto : gastos) {
                System.out.println(gasto.reporteTexto());
            }
        }
    }

    public void modificarGasto(int id, double nuevoMonto) {
        GastoFijo encontrado = buscarGasto(id);
        if (encontrado == null) {
            System.out.println("No se encontró el gasto");
        } else if (nuevoMonto <= 0) {
            System.out.println("Error: El monto debe ser mayor a cero");
        } else {
            encontrado.setMonto(nuevoMonto);
            System.out.println("Gasto modificado");
        }
    }

    public void eliminarGasto(int id) {
        GastoFijo encontrado = buscarGasto(id);
        if (encontrado == null) {
            System.out.println("No se encontró el gasto");
        } else {
            gastos.remove(encontrado);
            System.out.println("Gasto eliminado");
        }
    }

    //==================================================================================================================
    // LOS 3: Polimorfismo y Reportes
    //==================================================================================================================

    // Una lista de tipo Persona (clientes y empleados) y otra de tipo Movimiento (ventas y gastos)
    public void mostrarPersonasYMovimientos() {
        ArrayList<Persona> personas = new ArrayList<>();
        for (Cliente cliente : clientes) {
            personas.add(cliente);
        }
        for (Empleado empleado : empleados) {
            personas.add(empleado);
        }

        ArrayList<Movimiento> movimientos = new ArrayList<>();
        for (Venta venta : ventas) {
            movimientos.add(venta);
        }
        for (GastoFijo gasto : gastos) {
            movimientos.add(gasto);
        }

        System.out.println("--- Personas ---");
        if (personas.isEmpty()) {
            System.out.println("No hay personas registradas...");
        } else {
            for (Persona persona : personas) {
                System.out.println(persona.obtenerResumen());
            }
        }

        System.out.println("--- Movimientos ---");
        if (movimientos.isEmpty()) {
            System.out.println("No hay movimientos registrados...");
        } else {
            for (Movimiento movimiento : movimientos) {
                System.out.println(movimiento.mostrarMovimiento() + " | Total: S/ " + movimiento.calcularTotal());
            }
        }
    }

    // Reporte general: lista de tipo IReportable con empleados, ventas y gastos
    public void mostrarReportes() {
        ArrayList<IReportable> reportables = new ArrayList<>();
        for (Empleado empleado : empleados) {
            reportables.add(empleado);
        }
        for (Venta venta : ventas) {
            reportables.add(venta);
        }
        for (GastoFijo gasto : gastos) {
            reportables.add(gasto);
        }

        if (reportables.isEmpty()) {
            System.out.println("No hay datos para el reporte...");
            return;
        }

        System.out.println("--- REPORTE DEL MES ---");
        for (IReportable reportable : reportables) {
            System.out.println(reportable.reporteTexto());
        }

        // Cada tipo se suma por separado: las ventas entran y los gastos y sueldos salen
        int totalVentas = 0;
        for (Venta venta : ventas) {
            totalVentas = totalVentas + venta.reporteMontoMes();
        }
        int totalGastos = 0;
        for (GastoFijo gasto : gastos) {
            totalGastos = totalGastos + gasto.reporteMontoMes();
        }
        int totalSueldos = 0;
        for (Empleado empleado : empleados) {
            totalSueldos = totalSueldos + empleado.reporteMontoMes();
        }

        System.out.println("Ingresos por ventas: S/ " + totalVentas);
        System.out.println("Gastos fijos: S/ " + totalGastos);
        System.out.println("Pago al personal: S/ " + totalSueldos);
        System.out.println("Ganancia del mes: S/ " + (totalVentas - totalGastos - totalSueldos));
    }
}
