import java.util.Scanner;
import java.util.ArrayList;

public class ConsolaMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        //GABRIEL
        ArrayList<Cliente> clientes = new ArrayList<>();

        do {
            System.out.println("--- MENÚ PRINCIPAL ---");
            System.out.println("1. Registrar cliente");       //ANGEL
            System.out.println("2. Mostrar cliente");         //ANGEL
            System.out.println("3. Registrar empleado");
            System.out.println("4. Registrar venta");
            System.out.println("5. Registrar gasto fijo");
            System.out.println("6. Ver reportes");
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
                        System.out.print("Ingrese el deuda del cliente: ");
                        float deudaTotal = scanner.nextFloat();
                        //==============================================================================================
                        clientes.add(new Cliente(nombre, numeroTelefono, codigoPersona, zonaResidencia, deudaTotal));
                        System.out.println("Registrado exitosamente...");
                        System.out.println(" ");

                   break;
                case 2:
                    if (clientes.isEmpty()) {
                        System.out.println("No hay clientes registrados...");
                    }
                    else {
                        for (Cliente cliente : clientes) {
                            cliente.mostrarDatos();
                        }
                    }
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                 case 4:
                     System.out.println("Saliendo del programa...");
                     break;
                 case 5:
                     System.out.println("Saliendo del programa...");
                     break;
                 case 6:
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
