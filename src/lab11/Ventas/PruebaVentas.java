// ARCHIVO TEMPORAL: solo para probar Ventas. Borrar antes de entregar.
public class PruebaVentas {
    public static void main(String[] args) {
        DatabaseConectorVentas conector = new DatabaseConectorVentas();
        conector.abrirConexion();
        VentaController controller = new VentaController(conector.getConnection());

        System.out.println("--- 1. LISTAR (al empezar) ---");
        controller.listar();

        System.out.println("--- 2. REGISTRAR ---");
        controller.registrar(new VentaModel(0, 50.0, 5.5, 20.0, "C001", "2026-10-31"));

        System.out.println("--- 3. LISTAR (debe haber una venta mas) ---");
        controller.listar();

        // Pon aqui el codigo de la venta nueva (el ultimo que salio en el listar)
        int codigoPrueba = 4;

        System.out.println("--- 4. BUSCAR POR ID ---");
        controller.buscarPorId(codigoPrueba);

        System.out.println("--- 5. ACTUALIZAR ---");
        controller.actualizar(new VentaModel(codigoPrueba, 99.0, 9.9, 30.0, "C002", "2026-11-01"));
        controller.buscarPorId(codigoPrueba);

        System.out.println("--- 6. ELIMINAR ---");
        controller.eliminar(codigoPrueba);
        controller.buscarPorId(codigoPrueba);

        conector.cerrarConexion();
    }
}