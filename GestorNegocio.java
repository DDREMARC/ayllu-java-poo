import java.util.ArrayList;

public class GestorNegocio {
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Empleado> empleados = new ArrayList<>();

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
            clientes.remove(encontrado);
            System.out.println("Cliente eliminado");
        }
    }

    // Registra un empleado en la lista
    public void registrarEmpleado(String nombre, String telefono, String codigo,
                                   String cargo, double pagoPorDia,
                                   int diasTrabajados, double bono) {
        empleados.add(new Empleado(nombre, telefono, codigo, cargo,
                pagoPorDia, diasTrabajados, bono));
        System.out.println("Empleado registrado");
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
}