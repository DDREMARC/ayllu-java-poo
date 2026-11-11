package vista;

import controlador.PersonaController;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Persona;

import java.sql.SQLException;
import java.util.List;

public class PersonaView extends VistaBase {
    private PersonaController personaController;

    public PersonaView(Lector lector, PersonaController personaController) {
        super(lector);
        this.personaController = personaController;
    }

    @Override
    public void mostrarMenu() throws SQLException {
        int opcion;
        do {
            System.out.println("\n--- PERSONAS ---");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Registrar empleado");
            System.out.println("3. Listar personas");
            System.out.println("4. Buscar por codigo");
            System.out.println("5. Actualizar persona");
            System.out.println("6. Eliminar persona");
            System.out.println("0. Regresar");
            opcion = getLector().leerEntero("Seleccione una opcion: ");
            switch (opcion) {
                case 1: registrar(false); break;
                case 2: registrar(true); break;
                case 3: listar(); break;
                case 4: buscar(); break;
                case 5: actualizar(); break;
                case 6: eliminar(); break;
                case 0: break;
                default: System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
    }

    private void registrar(boolean esEmpleado) throws SQLException {
        Lector lector = getLector();
        String codigo = lector.leerTexto("Codigo (ejemplo C004 o E004): ");
        if (personaController.buscar(codigo) != null) {
            System.out.println("Ya existe una persona con el codigo " + codigo + ".");
            return;
        }
        String nombre = lector.leerTexto("Nombre: ");
        String telefono = lector.leerTexto("Telefono: ");
        Persona persona;
        if (esEmpleado) {
            String cargo = lector.leerTexto("Cargo: ");
            double pagoPorDia = lector.leerDecimal("Pago por dia: ");
            int dias = lector.leerEntero("Dias trabajados: ");
            double bono = lector.leerDecimal("Bono: ");
            persona = new Empleado(codigo, nombre, telefono, cargo, pagoPorDia, dias, bono);
        } else {
            String zona = lector.leerTexto("Zona de residencia: ");
            double deuda = lector.leerDecimal("Deuda inicial: ");
            persona = new Cliente(codigo, nombre, telefono, zona, deuda);
        }
        mostrarResultado(personaController.registrar(persona), "Persona registrada correctamente.");
    }

    private void listar() throws SQLException {
        List<Persona> lista = personaController.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay personas registradas.");
            return;
        }
        System.out.println("\nTotal: " + lista.size() + " persona(s)");
        for (Persona p : lista) {
            p.mostrarDatos();
        }
    }

    private void buscar() throws SQLException {
        String codigo = getLector().leerTexto("Codigo a buscar: ");
        Persona p = personaController.buscar(codigo);
        if (p == null) {
            System.out.println("No se encontro ninguna persona con el codigo " + codigo + ".");
        } else {
            p.mostrarDatos();
        }
    }

    private void actualizar() throws SQLException {
        Lector lector = getLector();
        String codigo = lector.leerTexto("Codigo de la persona a actualizar: ");
        Persona actual = personaController.buscar(codigo);
        if (actual == null) {
            System.out.println("No se encontro ninguna persona con el codigo " + codigo + ".");
            return;
        }
        System.out.println("Datos actuales:");
        actual.mostrarDatos();
        System.out.println("Ingrese los nuevos datos:");
        actual.setNombre(lector.leerTexto("Nombre: "));
        actual.setNumeroTelefono(lector.leerTexto("Telefono: "));
        if (actual instanceof Empleado) {
            Empleado e = (Empleado) actual;
            e.setCargo(lector.leerTexto("Cargo: "));
            e.setPagoPorDia(lector.leerDecimal("Pago por dia: "));
            e.setDiasTrabajados(lector.leerEntero("Dias trabajados: "));
            e.setBono(lector.leerDecimal("Bono: "));
        } else {
            Cliente c = (Cliente) actual;
            c.setZonaResidencia(lector.leerTexto("Zona de residencia: "));
            c.setDeudaTotal(lector.leerDecimal("Deuda total: "));
        }
        mostrarResultado(personaController.actualizar(actual), "Persona actualizada correctamente.");
    }

    private void eliminar() throws SQLException {
        Lector lector = getLector();
        String codigo = lector.leerTexto("Codigo de la persona a eliminar: ");
        Persona p = personaController.buscar(codigo);
        if (p == null) {
            System.out.println("No se encontro ninguna persona con el codigo " + codigo + ".");
            return;
        }
        p.mostrarDatos();
        if (p instanceof Cliente) {
            int ventas = personaController.contarVentas(codigo);
            if (ventas > 0) {
                System.out.println("AVISO: este cliente tiene " + ventas
                        + " venta(s) registrada(s). Elimine o reasigne sus ventas antes de borrarlo.");
                return;
            }
            if (((Cliente) p).tieneDeuda()) {
                System.out.println("AVISO: este cliente aun tiene deuda pendiente.");
            }
        }
        if (lector.confirmar("Confirma eliminar a " + p.getNombre() + "?")) {
            mostrarResultado(personaController.eliminar(codigo), "Persona eliminada correctamente.");
        } else {
            System.out.println("Operacion cancelada.");
        }
    }
}
