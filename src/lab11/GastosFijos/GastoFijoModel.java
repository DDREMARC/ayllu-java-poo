public class GastoFijoModel {
    private int idGasto;
    private int idTipo;
    private String fecha;
    private double monto;

    public GastoFijoModel(int idGasto, int idTipo, String fecha, double monto) {
        this.idGasto = idGasto;
        this.idTipo = idTipo;
        this.fecha = fecha;
        this.monto = monto;
    }

    public int getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(int idGasto) {
        this.idGasto = idGasto;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void mostrarGasto() {
        System.out.println("Gasto: " + idGasto);
        System.out.println("Tipo: " + idTipo);
        System.out.println("Fecha: " + fecha);
        System.out.println("Monto: " + monto);
    }
}