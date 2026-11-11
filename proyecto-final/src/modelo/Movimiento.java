package modelo;

public abstract class Movimiento {
    private int id;
    private String fecha;

    public Movimiento(int id, String fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public abstract double calcularTotal();

    public abstract boolean esIngreso();

    public abstract String obtenerDescripcion();

    public String mostrarMovimiento() {
        String signo = esIngreso() ? "+" : "-";
        return "[" + fecha + "] " + obtenerDescripcion() + " | " + signo + " S/ "
                + String.format("%.2f", calcularTotal());
    }
}
