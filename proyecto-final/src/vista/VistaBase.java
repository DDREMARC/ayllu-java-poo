package vista;

import java.sql.SQLException;

public abstract class VistaBase {
    private Lector lector;

    public VistaBase(Lector lector) {
        this.lector = lector;
    }

    public Lector getLector() {
        return lector;
    }

    public abstract void mostrarMenu() throws SQLException;

    public void mostrarResultado(int filas, String mensajeExito) {
        if (filas > 0) {
            System.out.println(mensajeExito);
        } else {
            System.out.println("No se realizo ningun cambio.");
        }
    }

    public String formatoMonto(double monto) {
        return "S/ " + String.format("%.2f", monto);
    }
}
