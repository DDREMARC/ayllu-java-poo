public class VentaModel {
    private int codigoVenta;
    private double precio;
    private double peso;
    private double pagoPollo;
    private String codigoCliente;
    private String fecha;

    public VentaModel(int codigoVenta, double precio, double peso, double pagoPollo, String codigoCliente, String fecha) {
        this.codigoVenta = codigoVenta;
        this.precio = precio;
        this.peso = peso;
        this.pagoPollo = pagoPollo;
        this.codigoCliente = codigoCliente;
        this.fecha = fecha;
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

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void mostrarVenta(){
        System.out.println("Venta: " + codigoVenta);
        System.out.println("Precio: " + precio);
        System.out.println("Peso: " + peso);
        System.out.println("Pago pollo: " + pagoPollo);
        System.out.println("Codigo: " + codigoCliente);
        System.out.println("Fecha: " + fecha);
    }
}
