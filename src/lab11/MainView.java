import java.util.Scanner;

public class MainView {
    public void ejecutar(){
        Scanner sc = new Scanner(System.in);
        DatabaseConnector dc = new DatabaseConnector();
        dc.abrirConexion();

        VentaController ventaController = new VentaController(dc.getConnection());

        int opcion = -1;
        while(opcion!=0) {
            System.out.println("Menu del laboratorio 11: ");
            System.out.println("1. Ventas");
            System.out.println("2. Personas");
            System.out.println("3. Gastos");
            System.out.println("0. Terminar programa");
            System.out.print("> ");

            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
            } else {
                sc.next();
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    menuVentas(sc, ventaController);
                    break;
                case 2:
                    System.out.println("Pendiente personas");
                    break;
                case 3:
                    System.out.println("Pendiente gastos");
                    break;
                case 0:
                    System.out.println("Programa cerrado...");
                    break;
                default:
                    System.out.println("Opción invalida");
                    break;
            }
        }
        dc.cerrarConexion();
        sc.close();
    }

    private int leerEntero(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Escribe un numero: ");
        }
        return sc.nextInt();
    }

    private double leerDouble(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextDouble()) {
            sc.next();
            System.out.print("Escribe un numero: ");
        }
        return sc.nextDouble();
    }

    private void menuVentas (Scanner sc, VentaController controller) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("--- Menu de ventas ---");
            System.out.println("1. Registrar");
            System.out.println("2. Listar");
            System.out.println("3. Buscar");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            opcion = leerEntero(sc, "> ");

            switch (opcion) {
                case 1: {
                    double precio = leerDouble(sc, "Precio: ");
                    double peso = leerDouble(sc, "Peso: ");
                    double pago = leerDouble(sc, "Pago del pollo: ");
                    System.out.print("Codigo de cliente: ");
                    String cliente = sc.next();
                    System.out.print("Fecha (AAAA-MM-DD): ");
                    String fecha = sc.next();

                    VentaModel ventaModel = new VentaModel(0, precio, peso, pago, cliente, fecha);
                    controller.registrar(ventaModel);
                    break;
                }
                case 2:
                    controller.listar();
                    break;
                case 3: {
                    int codigo = leerEntero(sc, "Codigo de venta: ");
                    controller.buscarPorId(codigo);
                    break;
                }
                case 4: {
                    int id = leerEntero(sc, "Codigo de venta a actualizar: ");
                    double precio = leerDouble(sc, "Precio: ");
                    double peso = leerDouble(sc, "Peso: ");
                    double pago = leerDouble(sc, "Pago del pollo: ");
                    System.out.print("Codigo de cliente: ");
                    String cliente = sc.next();
                    System.out.print("Fecha (AAAA-MM-DD): ");
                    String fecha = sc.next();

                    VentaModel ventaModel = new VentaModel(id, precio, peso, pago, cliente, fecha);
                    controller.actualizar(ventaModel);
                    break;
                }
                case 5: {
                    int codigo = leerEntero(sc, "Codigo de venta a eliminar: ");
                    controller.eliminar(codigo);
                    break;
                }
                case 0:
                    System.out.println("Volviendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
    }
}
