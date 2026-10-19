import java.time.LocalDate;

public class Venta extends Movimiento implements IReportable {
    private Cliente cliente;
    private double pesoKg;
    private double precioKg;
    private double montoPagado;

    public Venta(int id, LocalDate fecha, String descripcion, Cliente cliente, double pesoKg, double precioKg, double montoPagado) {
        super(id, fecha, descripcion);
        this.cliente = cliente;

        if (pesoKg > 0) {
            this.pesoKg = pesoKg;
        }
        if (precioKg > 0) {
            this.precioKg = precioKg;
        }

        double total = calcularTotal();
        if (montoPagado >= 0 && montoPagado <= total) {
            this.montoPagado = montoPagado;
        } else {
            this.montoPagado = 0;
        }

        // Registrar la deuda inicial en el cliente si existe diferencia
        double deudaInicial = total - this.montoPagado;
        if (this.cliente != null && deudaInicial > 0) {
            this.cliente.aumentarDeuda(deudaInicial);
        }
    }

    // Getters y Setters
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(double nuevoPeso) {
        if (nuevoPeso > 0) {
            double deudaAnterior = calcularDeuda();
            this.pesoKg = nuevoPeso;
            double nuevaDeuda = calcularDeuda();
            double diferencia = nuevaDeuda - deudaAnterior;

            if (cliente != null) {
                if (diferencia > 0) {
                    cliente.aumentarDeuda(diferencia);
                } else if (diferencia < 0) {
                    cliente.reducirDeuda(-diferencia);
                }
            }
        }
    }

    public double getPrecioKg() {
        return precioKg;
    }

    public void setPrecioKg(double nuevoPrecio) {
        if (nuevoPrecio > 0) {
            double deudaAnterior = calcularDeuda();
            this.precioKg = nuevoPrecio;
            double nuevaDeuda = calcularDeuda();
            double diferencia = nuevaDeuda - deudaAnterior;

            if (cliente != null) {
                if (diferencia > 0) {
                    cliente.aumentarDeuda(diferencia);
                } else if (diferencia < 0) {
                    cliente.reducirDeuda(-diferencia);
                }
            }
        }
    }

    public double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(double montoPagado) {
        double totalActual = calcularTotal();
        if (montoPagado >= 0 && montoPagado <= totalActual) {
            double diferencia = montoPagado - this.montoPagado;
            this.montoPagado = montoPagado;
            if (cliente != null) {
                if (diferencia > 0) {
                    cliente.reducirDeuda(diferencia);
                } else if (diferencia < 0) {
                    cliente.aumentarDeuda(-diferencia);
                }
            }
        }
    }

    // Métodos de apoyo para calcular deuda
    public double calcularDeuda() {
        return calcularTotal() - montoPagado;
    }

    // Implementación de métodos abstractos de Movimiento
    @Override
    public double calcularTotal() {
        return pesoKg * precioKg;
    }

    @Override
    public boolean esIngreso() {
        return true;
    }

    // Implementación de métodos de la interfaz IReportable
    @Override
    public String reporteTexto() {
        String nombreCliente;
        if (cliente != null) {
            nombreCliente = cliente.getNombre();
        } else {
            nombreCliente = "Sin cliente";
        }
        return "Venta realizada a " + nombreCliente + " por un total de $" + calcularTotal();
    }

    @Override
    public int reporteMontoMes() {
        return (int) Math.round(calcularTotal());
    }
}