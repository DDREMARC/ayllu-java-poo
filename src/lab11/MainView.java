import java.util.Scanner;

public class MainView {
    public void ejecutar(){
        Scanner sc = new Scanner(System.in);
        DatabaseConnector dc = new DatabaseConnector();
        dc.abrirConexion();

        VentaController ventaController = new VentaController(dc.getConnection());
        PersonaController personaController = new PersonaController(dc.getConnection());
        GastoFijoController gastoController = new GastoFijoController(dc.getConnection());

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
                    menuPersonas(sc, personaController);
                    break;
                case 3:
                    menuGastos(sc, gastoController);
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

    // Lee una linea completa (para nombres con espacios) y salta las lineas vacias
    private String leerTexto(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        String texto = sc.nextLine();
        while (texto.trim().isEmpty()) {
            texto = sc.nextLine();
        }
        return texto.trim();
    }

    private void menuPersonas(Scanner sc, PersonaController controller) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("--- Menu de personas ---");
            System.out.println("1. Registrar");
            System.out.println("2. Listar");
            System.out.println("3. Buscar");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            opcion = leerEntero(sc, "> ");

            switch (opcion) {
                case 1: {
                    String codigo = leerTexto(sc, "Codigo de persona: ");
                    String nombre = leerTexto(sc, "Nombre: ");
                    String telefono = leerTexto(sc, "Telefono: ");
                    int tipo = leerEntero(sc, "Es empleado? (1 = si, 0 = no): ");

                    if (tipo == 1) {
                        String cargo = leerTexto(sc, "Cargo: ");
                        double pagoPorDia = leerDouble(sc, "Pago por dia: ");
                        int dias = leerEntero(sc, "Dias trabajados: ");
                        double bono = leerDouble(sc, "Bono: ");
                        controller.registrar(new PersonaModel(codigo, nombre, telefono, 1, null, 0, cargo, pagoPorDia, dias, bono));
                    } else if (tipo == 0) {
                        String zona = leerTexto(sc, "Zona de residencia: ");
                        double deuda = leerDouble(sc, "Deuda inicial: ");
                        controller.registrar(new PersonaModel(codigo, nombre, telefono, 0, zona, deuda, null, 0, 0, 0));
                    } else {
                        System.out.println("Tipo invalido, escribe 1 o 0");
                    }
                    break;
                }
                case 2:
                    controller.listar();
                    break;
                case 3: {
                    String codigo = leerTexto(sc, "Codigo de persona: ");
                    controller.buscarPorId(codigo);
                    break;
                }
                case 4: {
                    String codigo = leerTexto(sc, "Codigo de persona a actualizar: ");
                    int tipo = controller.obtenerTipo(codigo);

                    if (tipo == -1) {
                        System.out.println("No se encontro el registro");
                    } else {
                        String nombre = leerTexto(sc, "Nombre: ");
                        String telefono = leerTexto(sc, "Telefono: ");

                        if (tipo == 1) {
                            String cargo = leerTexto(sc, "Cargo: ");
                            double pagoPorDia = leerDouble(sc, "Pago por dia: ");
                            int dias = leerEntero(sc, "Dias trabajados: ");
                            double bono = leerDouble(sc, "Bono: ");
                            controller.actualizar(new PersonaModel(codigo, nombre, telefono, 1, null, 0, cargo, pagoPorDia, dias, bono));
                        } else {
                            String zona = leerTexto(sc, "Zona de residencia: ");
                            double deuda = leerDouble(sc, "Deuda total: ");
                            controller.actualizar(new PersonaModel(codigo, nombre, telefono, 0, zona, deuda, null, 0, 0, 0));
                        }
                    }
                    break;
                }
                case 5: {
                    String codigo = leerTexto(sc, "Codigo de persona a eliminar: ");
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

    private void menuGastos(Scanner sc, GastoFijoController controller) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("--- Menu de gastos fijos ---");
            System.out.println("1. Registrar");
            System.out.println("2. Listar");
            System.out.println("3. Buscar");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("0. Volver");
            opcion = leerEntero(sc, "> ");

            switch (opcion) {
                case 1: {
                    int tipo = leerEntero(sc, "Tipo (1=Implementos, 2=Combustible y peajes, 3=Agua y gas): ");
                    double monto = leerDouble(sc, "Monto: ");
                    System.out.print("Fecha (AAAA-MM-DD): ");
                    String fecha = sc.next();

                    GastoFijoModel gastoModel = new GastoFijoModel(0, tipo, fecha, monto);
                    controller.registrar(gastoModel);
                    break;
                }
                case 2:
                    controller.listar();
                    break;
                case 3: {
                    int id = leerEntero(sc, "Id del gasto: ");
                    controller.buscarPorId(id);
                    break;
                }
                case 4: {
                    int id = leerEntero(sc, "Id del gasto a actualizar: ");
                    int tipo = leerEntero(sc, "Tipo (1=Implementos, 2=Combustible y peajes, 3=Agua y gas): ");
                    double monto = leerDouble(sc, "Monto: ");
                    System.out.print("Fecha (AAAA-MM-DD): ");
                    String fecha = sc.next();

                    GastoFijoModel gastoModel = new GastoFijoModel(id, tipo, fecha, monto);
                    controller.actualizar(gastoModel);
                    break;
                }
                case 5: {
                    int id = leerEntero(sc, "Id del gasto a eliminar: ");
                    controller.eliminar(id);
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
