import java.util.Scanner;

public class MenuConsola {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        //GABRIEL
        ArrayList<Cliente> clientes = new ArrayList<>();

        do {
            System.out.println("--- MENÚ PRINCIPAL ---");
            System.out.println("1. Registrar cliente");       //GABRIEL
            System.out.println("2. Mostrar cliente");         //GABRIEL
            System.out.println("3. Registrar empleado");
            System.out.println("4. Registrar venta");
            System.out.println("5. Registrar gasto fijo");
            System.out.println("6. Ver reportes");
            System.out.println("0. Salir");                   //GABRIEL
            System.out.print("\nSeleccione una opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Ejecutando opción 1...\n");
                    break;
                case 2:
                    System.out.println("Ejecutando opción 2...\n");
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.\n");
            }
        } while (opcion != 3);

        scanner.close();
    }
}
