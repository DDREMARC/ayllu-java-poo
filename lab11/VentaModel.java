// Clase MODELO: solo guarda los datos de una venta. No se conecta a la base de datos.
public class VentaModel {
    private int codigoVenta;
    private double precio;
    private double peso;
    private double pagoPollo;
    private String fecha;
    private String codigoCliente;

    public VentaModel() {
    }

    public VentaModel(int codigoVenta, double precio, double peso, double pagoPollo,
                       String fecha, String codigoCliente) {
        this.codigoVenta = codigoVenta;
        this.precio = precio;
        this.peso = peso;
        this.pagoPollo = pagoPollo;
        this.fecha = fecha;
        this.codigoCliente = codigoCliente;
    }

    public int getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(int codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getPagoPollo() {
        return pagoPollo;
    }

    public void setPagoPollo(double pagoPollo) {
        this.pagoPollo = pagoPollo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    // Calculados a partir de los datos guardados, no se guardan en la BD
    public double getTotal() {
        return peso * precio;
    }

    public double getDeuda() {
        return getTotal() - pagoPollo;
    }

    public void mostrar() {
        System.out.println("Codigo de venta: " + codigoVenta);
        System.out.println("Precio: " + precio);
        System.out.println("Peso: " + peso);
        System.out.println("Pago de pollo: " + pagoPollo);
        System.out.println("Fecha: " + fecha);
        System.out.println("Codigo de cliente: " + codigoCliente);
        System.out.println("Total de la venta: " + getTotal());
        System.out.println("Deuda pendiente: " + getDeuda());
    }
}
