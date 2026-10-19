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
            System.out.println("4. Registrar empleado");     //DAN
            System.out.println("5. Mostrar empleados");      //DAN
            System.out.println("6. Registrar venta");
            System.out.println("7. Registrar gasto fijo");
            System.out.println("8. Ver reportes");
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
                    registrarEmpleados(scanner, gestor);
                    break;
                case 5:
                    gestor.mostrarEmpleados();
                    break;
                 case 6:
                     System.out.println("Saliendo del programa...");
                     break;
                 case 8:
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

    // Registra un empleado y lo guarda en la lista
    public static void registrarEmpleados(Scanner scanner, GestorNegocio gestor) {
        System.out.println("--- Registrar empleado ---");
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.next();
        System.out.print("Ingrese telefono: ");
        String numeroTelefono = scanner.next();
        System.out.print("Ingrese codigo: ");
        String codigoPersona = scanner.next();
        System.out.print("Ingrese cargo: ");
        String cargo = scanner.next();
        System.out.print("Ingrese pago por dia: ");
        double pagoPorDia = scanner.nextDouble();
        System.out.print("Ingrese dias trabajados: ");
        int diasTrabajados = scanner.nextInt();
        System.out.print("Ingrese bono: ");
        double bono = scanner.nextDouble();

        gestor.registrarEmpleado(
                nombre,
                numeroTelefono,
                codigoPersona,
                cargo,
                pagoPorDia,
                diasTrabajados,
                bono
        );
    }
}
