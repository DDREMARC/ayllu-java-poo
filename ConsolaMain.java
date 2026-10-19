import java.util.Scanner;

public class ConsolaMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        GestorNegocio gestor = new GestorNegocio();

        do {
            System.out.println("--- MENÚ PRINCIPAL ---");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Mostrar clientes");
            System.out.println("3. Buscar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("5. Registrar empleado");
            System.out.println("6. Mostrar empleados");
            System.out.println("7. Eliminar empleado");
            System.out.println("8. Registrar venta");
            System.out.println("9. Mostrar ventas");
            System.out.println("10. Modificar pago de una venta");
            System.out.println("11. Eliminar venta");
            System.out.println("12. Registrar gasto fijo");
            System.out.println("13. Mostrar gastos fijos");
            System.out.println("14. Modificar monto de un gasto");
            System.out.println("15. Eliminar gasto");
            System.out.println("16. Ver personas y movimientos");
            System.out.println("17. Ver reportes");
            System.out.println("0. Salir");
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
                    System.out.println("--- Buscar Cliente ---");
                    System.out.print("Ingrese el codigo del cliente a buscar: ");
                    String codigoBuscar = scanner.nextLine();
                    Cliente clienteBuscado = gestor.buscarCliente(codigoBuscar);
                    if (clienteBuscado == null) {
                        System.out.println("No se encontró el cliente");
                    } else {
                        clienteBuscado.mostrarDatos();
                    }
                    break;
                case 4:
                    System.out.println("--- Eliminar Cliente ---");
                    System.out.print("Ingrese el codigo del cliente a eliminar: ");
                    String codigoEliminar = scanner.nextLine();
                    gestor.eliminarCliente(codigoEliminar);
                    break;
                case 5:
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
                case 6:
                    gestor.mostrarEmpleados();
                    break;
                case 7:
                    System.out.println("--- Eliminar Empleado ---");
                    System.out.print("Ingrese el codigo del empleado a eliminar: ");
                    String codigoEmpleadoEliminar = scanner.nextLine();
                    gestor.eliminarEmpleado(codigoEmpleadoEliminar);
                    break;
                case 8:
                    System.out.println("--- Registrar Venta ---");
                    System.out.print("Ingrese la descripción de la venta: ");
                    String descripcionVenta = scanner.nextLine();
                    System.out.print("Ingrese el código del cliente (dejar vacío si no aplica): ");
                    String codigoClienteVenta = scanner.nextLine();
                    System.out.print("Ingrese el peso en Kg: ");
                    double pesoKg = scanner.nextDouble();
                    System.out.print("Ingrese el precio por Kg: ");
                    double precioKg = scanner.nextDouble();
                    System.out.print("Ingrese el monto pagado: ");
                    double montoPagado = scanner.nextDouble();
                    scanner.nextLine();
                    gestor.registrarVenta(descripcionVenta, codigoClienteVenta, pesoKg, precioKg, montoPagado);
                    break;
                case 9:
                    gestor.mostrarVentas();
                    break;
                case 10:
                    System.out.println("--- Modificar Pago de Venta ---");
                    System.out.print("Ingrese el id de la venta: ");
                    int idVentaModificar = scanner.nextInt();
                    System.out.print("Ingrese el nuevo monto total pagado: ");
                    double nuevoMontoPagado = scanner.nextDouble();
                    scanner.nextLine();
                    gestor.modificarVenta(idVentaModificar, nuevoMontoPagado);
                    break;
                case 11:
                    System.out.println("--- Eliminar Venta ---");
                    System.out.print("Ingrese el id de la venta a eliminar: ");
                    int idVentaEliminar = scanner.nextInt();
                    scanner.nextLine();
                    gestor.eliminarVenta(idVentaEliminar);
                    break;
                case 12:
                    System.out.println("--- Registrar Gasto Fijo ---");
                    System.out.print("Ingrese la descripción del gasto: ");
                    String descripcionGasto = scanner.nextLine();
                    System.out.println("Tipos válidos: implementos, combustible y peajes, agua y gas, otros");
                    System.out.print("Ingrese el tipo de gasto: ");
                    String tipoGasto = scanner.nextLine();
                    System.out.print("Ingrese el monto del gasto: ");
                    double montoGasto = scanner.nextDouble();
                    scanner.nextLine();
                    gestor.registrarGasto(descripcionGasto, tipoGasto, montoGasto);
                    break;
                case 13:
                    gestor.mostrarGastos();
                    break;
                case 14:
                    System.out.println("--- Modificar Monto de Gasto ---");
                    System.out.print("Ingrese el id del gasto: ");
                    int idGastoModificar = scanner.nextInt();
                    System.out.print("Ingrese el nuevo monto: ");
                    double nuevoMontoGasto = scanner.nextDouble();
                    scanner.nextLine();
                    gestor.modificarGasto(idGastoModificar, nuevoMontoGasto);
                    break;
                case 15:
                    System.out.println("--- Eliminar Gasto ---");
                    System.out.print("Ingrese el id del gasto a eliminar: ");
                    int idGastoEliminar = scanner.nextInt();
                    scanner.nextLine();
                    gestor.eliminarGasto(idGastoEliminar);
                    break;
                case 16:
                    gestor.mostrarPersonasYMovimientos();
                    break;
                case 17:
                    gestor.mostrarReportes();
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