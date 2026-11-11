package modelo;

public class Venta extends Movimiento implements IReportable {
    private Cliente cliente;
    private double pesoKg;
    private double precioKg;
    private double montoPagado;

    public Venta(int id, String fecha, Cliente cliente, double pesoKg, double precioKg, double montoPagado) {
        super(id, fecha);
        this.cliente = cliente;
        this.pesoKg = pesoKg;
        this.precioKg = precioKg;
        this.montoPagado = montoPagado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public double getPrecioKg() {
        return precioKg;
    }

    public void setPrecioKg(double precioKg) {
        this.precioKg = precioKg;
    }

    public double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public double calcularDeuda() {
        double deuda = calcularTotal() - montoPagado;
        return deuda > 0 ? deuda : 0;
    }

    @Override
    public double calcularTotal() {
        return pesoKg * precioKg;
    }

    @Override
    public boolean esIngreso() {
        return true;
    }

    @Override
    public String obtenerDescripcion() {
        return "Venta #" + getId() + " a " + cliente.getNombre() + " (" + pesoKg + " kg x S/ " + precioKg + ")";
    }

    @Override
    public String reporteTexto() {
        return "Cobro de venta #" + getId() + " a " + cliente.getNombre();
    }

    @Override
    public double reporteMonto() {
        return montoPagado;
    }

    public void mostrarVenta() {
        System.out.println("Codigo: " + getId() + " | Fecha: " + getFecha()
                + " | Cliente: " + cliente.getCodigoPersona() + " - " + cliente.getNombre());
        System.out.println("   Peso: " + pesoKg + " kg | Precio/kg: S/ " + String.format("%.2f", precioKg)
                + " | Total: S/ " + String.format("%.2f", calcularTotal())
                + " | Pagado: S/ " + String.format("%.2f", montoPagado)
                + " | Deuda: S/ " + String.format("%.2f", calcularDeuda()));
    }
}
