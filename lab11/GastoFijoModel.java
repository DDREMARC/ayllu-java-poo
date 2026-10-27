// Clase MODELO: representa un gasto fijo del negocio (implementos, combustible, agua y gas).
public class GastoFijoModel {
    private int idGasto;
    private int idTipo;
    private String fecha;
    private double monto;

    public GastoFijoModel() {
    }

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

    public void mostrar() {
        System.out.println("ID: " + idGasto + " | Tipo: " + idTipo
                + " | Fecha: " + fecha + " | Monto: " + monto);
    }
}
