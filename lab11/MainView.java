import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

// Clase VISTA: muestra el menú, lee lo que escribe el usuario y llama a los
// controladores. No ejecuta SQL directamente.
public class MainView {
    private Connection conexion;
    private final Scanner sc = new Scanner(System.in).useLocale(Locale.US);

    private VentaController ventaController;
    private PersonaController personaController;
    private GastoFijoController gastoFijoController;

    public void iniciar() {
        try {
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ayllu_pollos", "root", ""); // COLOCAR TU XAMPP
            System.out.println("Conexión exitosa");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
            return;
        }

        ventaController = new VentaController(conexion);
        personaController = new PersonaController(conexion);
        gastoFijoController = new GastoFijoController(conexion);

        int opcion;
        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Administrar ventas");
            System.out.println("2. Administrar personas (clientes y empleados)");
            System.out.println("3. Administrar gastos fijos");
            System.out.println("0. Salir");

            opcion = leerEntero("\nSeleccione una opcion: ");

            switch (opcion) {
                case 1:
                    menuVentas();
                    break;
                case 2:
                    menuPersonas();
                    break;
                case 3:
                    menuGastosFijos();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 0);

        cerrar();
    }

    // ===================== VENTAS =====================

    private void menuVentas() {
        int opcion;
        do {
            System.out.println("\n--- MENU VENTAS ---");
            System.out.println("1. Registrar venta");
            System.out.println("2. Listar ventas");
            System.out.println("3. Buscar venta");
            System.out.println("4. Actualizar venta");
            System.out.println("5. Eliminar venta");
            System.out.println("0. Volver al menu principal");

            opcion = leerEntero("\nSeleccione una opcion: ");

            switch (opcion) {
                case 1: {
                    double precio = leerDouble("Ingrese el precio: ");
                    double peso = leerDouble("Ingrese el peso del pollo: ");
                    double pagoPollo = leerDouble("Ingrese el pago del pollo: ");
                    String fecha = leerTexto("Ingrese la fecha [AAAA-MM-DD]: ");
                    String codigoCliente = leerTexto("Ingrese el codigo del cliente: ");
                    boolean ok = ventaController.registrar(precio, peso, pagoPollo, fecha, codigoCliente);
                    System.out.println(ok ? "Venta registrada" : "No se pudo registrar la venta");
                    break;
                }
                case 2: {
                    ArrayList<VentaModel> ventas = ventaController.listar();
                    if (ventas.isEmpty()) {
                        System.out.println("No hay ventas registradas todavia.");
                    } else {
                        for (VentaModel v : ventas) {
                            v.mostrar();
                            System.out.println("---");
                        }
                    }
                    break;
                }
                case 3: {
                    int codigo = leerEntero("Ingrese el codigo de venta: ");
                    VentaModel v = ventaController.buscarPorId(codigo);
                    if (v != null) {
                        v.mostrar();
                    } else {
                        System.out.println("No se encontro la venta " + codigo);
                    }
                    break;
                }
                case 4: {
                    int codigo = leerEntero("Ingrese el codigo de venta: ");
                    double nuevoPago = leerDouble("Ingrese el nuevo monto a pagar del pollo: ");
                    boolean ok = ventaController.actualizarPago(codigo, nuevoPago);
                    System.out.println(ok ? "Venta actualizada" : "No se encontro la venta");
                    break;
                }
                case 5: {
                    int codigo = leerEntero("Ingrese el codigo de la venta a eliminar: ");
                    boolean ok = ventaController.eliminar(codigo);
                    System.out.println(ok ? "Venta eliminada" : "No se encontro la venta");
                    break;
                }
                case 0:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 0);
    }

    // ===================== PERSONAS (clientes y empleados) =====================

    private void menuPersonas() {
        int opcion;
        do {
            System.out.println("\n--- MENU PERSONAS ---");
            System.out.println("1. Registrar persona");
            System.out.println("2. Listar personas");
            System.out.println("3. Buscar persona");
            System.out.println("4. Actualizar persona");
            System.out.println("5. Eliminar persona");
            System.out.println("0. Volver al menu principal");

            opcion = leerEntero("\nSeleccione una opcion: ");

            switch (opcion) {
                case 1: {
                    String codigo = leerTexto("Ingrese el codigo de la persona: ");
                    String nombre = leerTexto("Ingrese el nombre: ");
                    String telefono = leerTexto("Ingrese el numero de telefono: ");
                    int tipo = leerEntero("Es empleado? (1 = si, 0 = no): ");
                    boolean ok;
                    if (tipo == 1) {
                        String cargo = leerTexto("Ingrese el cargo: ");
                        double pagoDia = leerDouble("Ingrese el pago por dia: ");
                        int dias = leerEntero("Ingrese los dias trabajados: ");
                        double bono = leerDouble("Ingrese el bono (0 si no tiene): ");
                        ok = personaController.registrarEmpleado(codigo, nombre, telefono, cargo, pagoDia, dias, bono);
                    } else {
                        String zona = leerTexto("Ingrese la zona de residencia: ");
                        double deuda = leerDouble("Ingrese la deuda inicial (0 si no tiene): ");
                        ok = personaController.registrarCliente(codigo, nombre, telefono, zona, deuda);
                    }
                    System.out.println(ok ? "Persona registrada" : "No se pudo registrar la persona");
                    break;
                }
                case 2: {
                    ArrayList<PersonaModel> personas = personaController.listar();
                    if (personas.isEmpty()) {
                        System.out.println("No hay personas registradas todavia.");
                    } else {
                        for (PersonaModel p : personas) {
                            p.mostrar();
                            System.out.println("---");
                        }
                    }
                    break;
                }
                case 3: {
                    String codigo = leerTexto("Ingrese el codigo de la persona: ");
                    PersonaModel p = personaController.buscarPorId(codigo);
                    if (p != null) {
                        p.mostrar();
                    } else {
                        System.out.println("No se encontro la persona " + codigo);
                    }
                    break;
                }
                case 4: {
                    String codigo = leerTexto("Ingrese el codigo de la persona a actualizar: ");
                    PersonaModel actual = personaController.buscarPorId(codigo);
                    if (actual == null) {
                        System.out.println("No se encontro la persona " + codigo);
                        break;
                    }
                    boolean ok;
                    if (actual.getEsEmpleado() == 1) {
                        int dias = leerEntero("Ingrese los nuevos dias trabajados: ");
                        double bono = leerDouble("Ingrese el nuevo bono: ");
                        ok = personaController.actualizarEmpleado(codigo, dias, bono);
                    } else {
                        double deuda = leerDouble("Ingrese la nueva deuda total: ");
                        ok = personaController.actualizarCliente(codigo, deuda);
                    }
                    System.out.println(ok ? "Persona actualizada" : "No se hizo la actualizacion");
                    break;
                }
                case 5: {
                    String codigo = leerTexto("Ingrese el codigo de la persona a eliminar: ");
                    boolean ok = personaController.eliminar(codigo);
                    System.out.println(ok ? "Persona eliminada" : "No se encontro la persona");
                    break;
                }
                case 0:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 0);
    }

    // ===================== GASTOS FIJOS =====================

    private void menuGastosFijos() {
        int opcion;
        do {
            System.out.println("\n--- MENU GASTOS FIJOS ---");
            System.out.println("1. Registrar gasto");
            System.out.println("2. Listar gastos");
            System.out.println("3. Buscar gasto por id");
            System.out.println("4. Actualizar gasto");
            System.out.println("5. Eliminar gasto");
            System.out.println("0. Volver al menu principal");

            opcion = leerEntero("\nSeleccione una opcion: ");

            switch (opcion) {
                case 1: {
                    int idTipo = leerEntero("Id del tipo de gasto (1=Implementos, 2=Combustible y peajes, 3=Agua y gas): ");
                    double monto = leerDouble("Monto: ");
                    boolean ok = gastoFijoController.registrar(idTipo, monto);
                    System.out.println(ok ? "Gasto registrado correctamente" : "No se pudo registrar el gasto");
                    break;
                }
                case 2: {
                    ArrayList<GastoFijoModel> gastos = gastoFijoController.listar();
                    if (gastos.isEmpty()) {
                        System.out.println("No hay gastos registrados.");
                    } else {
                        for (GastoFijoModel g : gastos) {
                            g.mostrar();
                        }
                    }
                    break;
                }
                case 3: {
                    int id = leerEntero("Id del gasto: ");
                    GastoFijoModel g = gastoFijoController.buscarPorId(id);
                    if (g != null) {
                        g.mostrar();
                    } else {
                        System.out.println("No se encontro el registro solicitado.");
                    }
                    break;
                }
                case 4: {
                    int id = leerEntero("Id del gasto a actualizar: ");
                    int idTipo = leerEntero("Nuevo id de tipo de gasto: ");
                    double monto = leerDouble("Nuevo monto: ");
                    boolean ok = gastoFijoController.actualizar(id, idTipo, monto);
                    System.out.println(ok ? "Gasto actualizado correctamente" : "No se encontro el registro solicitado.");
                    break;
                }
                case 5: {
                    int id = leerEntero("Id del gasto a eliminar: ");
                    boolean ok = gastoFijoController.eliminar(id);
                    System.out.println(ok ? "Gasto eliminado correctamente" : "No se encontro el registro solicitado.");
                    break;
                }
                case 0:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 0);
    }

    // ===================== Entrada de datos protegida =====================

    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (sc.hasNextInt()) {
                int valor = sc.nextInt();
                sc.nextLine();
                return valor;
            } else {
                System.out.println("Eso no es un numero. Intenta de nuevo.");
                sc.nextLine();
            }
        }
    }

    private double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (sc.hasNextDouble()) {
                double valor = sc.nextDouble();
                sc.nextLine();
                return valor;
            } else {
                System.out.println("Eso no es un numero valido. Intenta de nuevo.");
                sc.nextLine();
            }
        }
    }

    private String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String valor = sc.nextLine();
            if (!valor.trim().isEmpty()) {
                return valor;
            } else {
                System.out.println("Este dato no puede quedar vacio. Intenta de nuevo.");
            }
        }
    }

    private void cerrar() {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexion: " + e.getMessage());
        }
        sc.close();
    }
}
