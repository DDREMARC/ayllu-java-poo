package modelo;

public class Cliente extends Persona {
    private String zonaResidencia;
    private double deudaTotal;

    public Cliente(String codigoPersona, String nombre, String numeroTelefono,
                   String zonaResidencia, double deudaTotal) {
        super(codigoPersona, nombre, numeroTelefono);
        this.zonaResidencia = zonaResidencia;
        this.deudaTotal = deudaTotal;
    }

    public String getZonaResidencia() {
        return zonaResidencia;
    }

    public void setZonaResidencia(String zonaResidencia) {
        this.zonaResidencia = zonaResidencia;
    }

    public double getDeudaTotal() {
        return deudaTotal;
    }

    public void setDeudaTotal(double deudaTotal) {
        this.deudaTotal = deudaTotal;
    }

    public boolean tieneDeuda() {
        return deudaTotal > 0;
    }

    @Override
    public boolean esEmpleado() {
        return false;
    }

    @Override
    public String obtenerResumen() {
        return "Cliente | Zona: " + zonaResidencia + " | Deuda: S/ " + String.format("%.2f", deudaTotal);
    }
}
