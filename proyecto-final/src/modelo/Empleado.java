package modelo;

public class Empleado extends Persona implements IReportable {
    private String cargo;
    private double pagoPorDia;
    private int diasTrabajados;
    private double bono;

    public Empleado(String codigoPersona, String nombre, String numeroTelefono,
                    String cargo, double pagoPorDia, int diasTrabajados, double bono) {
        super(codigoPersona, nombre, numeroTelefono);
        this.cargo = cargo;
        this.pagoPorDia = pagoPorDia;
        this.diasTrabajados = diasTrabajados;
        this.bono = bono;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getPagoPorDia() {
        return pagoPorDia;
    }

    public void setPagoPorDia(double pagoPorDia) {
        this.pagoPorDia = pagoPorDia;
    }

    public int getDiasTrabajados() {
        return diasTrabajados;
    }

    public void setDiasTrabajados(int diasTrabajados) {
        this.diasTrabajados = diasTrabajados;
    }

    public double getBono() {
        return bono;
    }

    public void setBono(double bono) {
        this.bono = bono;
    }

    public double calcularPago() {
        return diasTrabajados * pagoPorDia + bono;
    }

    @Override
    public boolean esEmpleado() {
        return true;
    }

    @Override
    public String obtenerResumen() {
        return "Empleado | Cargo: " + cargo + " | Dias: " + diasTrabajados
                + " | Pago/dia: S/ " + String.format("%.2f", pagoPorDia)
                + " | Bono: S/ " + String.format("%.2f", bono);
    }

    @Override
    public String reporteTexto() {
        return "Sueldo de " + getNombre() + " (" + cargo + ")";
    }

    @Override
    public double reporteMonto() {
        return -calcularPago();
    }
}
