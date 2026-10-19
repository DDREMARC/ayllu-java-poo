
Consolamain · JAVA
import java.util.Scanner;

public class ConsolaMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        //GABRIEL
        GestorNegocio gestor = new GestorNegocio();

        do {
            System.out.println("--- MENÚ PRINCIPAL ---");
            System.out.println("1. Registrar cliente");       //ANGEL
            System.out.println("2. Mostrar clientes");        //ANGEL
            System.out.println("3. Eliminar cliente");        //ANGEL
            System.out.println("4. Registrar empleado");
            System.out.println("5. Registrar venta");
            System.out.println("6. Mostrar ventas");
            System.out.println("7. Modificar venta (registrar pago)");
            System.out.println("8. Eliminar venta");
            System.out.println("9. Registrar gasto fijo");
            System.out.println("10. Mostrar gastos fijos");
            System.out.println("11. Modificar gasto fijo");
            System.out.println("12. Eliminar gasto fijo");
            System.out.println("13. Ver reportes");
            System.out.println("0. Salir");                   //ANGEL
            System.out.print("\nSeleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("--- Registrar Cliente ---");
                    System.out.print("Ingrese nombre del cliente: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese el telefono del cliente: ");
                    String numeroTelefono = scanner.nextLine();
                    System.out.print("Ingrese el codigo del cliente: ");
                    String codigoPersona = scanner.nextLine();
                    System.out.print("Ingrese el distrito del cliente: ");
                    String zonaResidencia = scanner.nextLine();
                    System.out.print("Ingrese la deuda del cliente: ");
                    double deudaTotal = scanner.nextDouble();
                    scanner.nextLine();
                    gestor.registrarCliente(nombre, numeroTelefono, codigoPersona, zonaResidencia, deudaTotal);
                    break;
                case 2:
                    gestor.mostrarClientes();
                    break;
                case 3:
                    System.out.println("--- Eliminar Cliente ---");
                    System.out.print("Ingrese el codigo del cliente a eliminar: ");
                    String codigoEliminar = scanner.nextLine();
                    gestor.eliminarCliente(codigoEliminar);
                    break;
                case 4:
                    System.out.println("--- Registrar Empleado ---");
                    System.out.print("Ingrese nombre del empleado: ");
                    String nombreEmpleado = scanner.nextLine();
                    System.out.print("Ingrese el telefono del empleado: ");
                    String telefonoEmpleado = scanner.nextLine();
                    System.out.print("Ingrese el codigo del empleado: ");
                    String codigoEmpleado = scanner.nextLine();
                    System.out.print("Ingrese el cargo del empleado: ");
                    String cargoEmpleado = scanner.nextLine();
                    System.out.print("Ingrese el pago por dia: ");
                    double pagoPorDia = scanner.nextDouble();
                    System.out.print("Ingrese los dias trabajados: ");
                    int diasTrabajados = scanner.nextInt();
                    System.out.print("Ingrese el bono: ");
                    double bono = scanner.nextDouble();
                    scanner.nextLine();
                    gestor.registrarEmpleado(nombreEmpleado, telefonoEmpleado, codigoEmpleado, cargoEmpleado, pagoPorDia, diasTrabajados, bono);
                    break;
                case 5:
                    System.out.println("--- Registrar Venta ---");
                    System.out.print("Ingrese la descripción de la venta: ");
                    String descripcionVenta = scanner.nextLine();
                    System.out.print("Ingrese la fecha de la venta (ej: 18/10/2026): ");
                    String fechaVenta = scanner.nextLine();
                    System.out.print("Ingrese el código del cliente (dejar vacío si no aplica): ");
                    String codigoClienteVenta = scanner.nextLine();
                    System.out.print("Ingrese el peso en Kg: ");
                    double pesoKg = scanner.nextDouble();
                    System.out.print("Ingrese el precio por Kg: ");
                    double precioKg = scanner.nextDouble();
                    System.out.print("Ingrese el monto pagado: ");
                    double montoPagado = scanner.nextDouble();
                    scanner.nextLine();
                    gestor.registrarVenta(descripcionVenta, fechaVenta, codigoClienteVenta, pesoKg, precioKg, montoPagado);
                    break;
                case 6:
                    System.out.println("--- Ventas Registradas ---");
                    gestor.mostrarVentas();
                    break;
                case 7:
                    System.out.println("--- Modificar Venta ---");
                    System.out.print("Ingrese el ID de la venta: ");
                    int idVentaModificar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese el nuevo monto pagado: ");
                    double nuevoMontoPagado = scanner.nextDouble();
                    scanner.nextLine();
                    gestor.modificarVenta(idVentaModificar, nuevoMontoPagado);
                    break;
                case 8:
                    System.out.println("--- Eliminar Venta ---");
                    System.out.print("Ingrese el ID de la venta a eliminar: ");
                    int idVentaEliminar = scanner.nextInt();
                    scanner.nextLine();
                    gestor.eliminarVenta(idVentaEliminar);
                    break;
                case 9:
                    System.out.println("--- Registrar Gasto Fijo ---");
                    System.out.print("Ingrese la descripción del gasto: ");
                    String descripcionGasto = scanner.nextLine();
                    System.out.print("Ingrese la fecha del gasto (ej: 18/10/2026): ");
                    String fechaGasto = scanner.nextLine();
                    System.out.println("Tipos válidos: implementos, combustible y peajes, agua y gas, otros");
                    System.out.print("Ingrese el tipo de gasto: ");
                    String tipoGasto = scanner.nextLine();
                    System.out.print("Ingrese el monto del gasto: ");
                    double montoGasto = scanner.nextDouble();
                    scanner.nextLine();
                    gestor.registrarGasto(descripcionGasto, fechaGasto, tipoGasto, montoGasto);
                    break;
                case 10:
                    System.out.println("--- Gastos Fijos Registrados ---");
                    gestor.mostrarGastos();
                    break;
                case 11:
                    System.out.println("--- Modificar Gasto Fijo ---");
                    System.out.print("Ingrese el ID del gasto: ");
                    int idGastoModificar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese el nuevo monto: ");
                    double nuevoMontoGasto = scanner.nextDouble();
                    scanner.nextLine();
                    gestor.modificarGasto(idGastoModificar, nuevoMontoGasto);
                    break;
                case 12:
                    System.out.println("--- Eliminar Gasto Fijo ---");
                    System.out.print("Ingrese el ID del gasto a eliminar: ");
                    int idGastoEliminar = scanner.nextInt();
                    scanner.nextLine();
                    gestor.eliminarGasto(idGastoEliminar);
                    break;
                case 13:
                    System.out.println("Saliendo del programa...");
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.\n");
            }
        } while (opcion != 0);

        scanner.close();
    }
}

Claude terminó la respuesta
