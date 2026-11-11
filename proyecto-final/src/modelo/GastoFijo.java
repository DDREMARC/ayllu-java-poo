package modelo;

public class GastoFijo extends Movimiento implements IReportable {
    private TipoGasto tipo;
    private double monto;

    public GastoFijo(int id, String fecha, TipoGasto tipo, double monto) {
        super(id, fecha);
        this.tipo = tipo;
        this.monto = monto;
    }

    public TipoGasto getTipo() {
        return tipo;
    }

    public void setTipo(TipoGasto tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public double calcularTotal() {
        return monto;
    }

    @Override
    public boolean esIngreso() {
        return false;
    }

    @Override
    public String obtenerDescripcion() {
        return "Gasto #" + getId() + " - " + tipo.getNombre();
    }

    @Override
    public String reporteTexto() {
        return "Gasto fijo " + tipo.getNombre() + " del " + getFecha();
    }

    @Override
    public double reporteMonto() {
        return -monto;
    }

    public void mostrarGasto() {
        System.out.println("Id: " + getId() + " | Fecha: " + getFecha()
                + " | Tipo: " + tipo.getIdTipo() + " - " + tipo.getNombre()
                + " | Monto: S/ " + String.format("%.2f", monto));
    }
}
