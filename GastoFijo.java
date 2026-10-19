public class GastoFijo extends Movimiento implements IReportable {
    private String tipo;
    private double monto;

    public GastoFijo(int id, String fecha, String descripcion, String tipo, double monto) {
        super(id, fecha, descripcion);
        setTipo(tipo);
        setMonto(monto);
    }

    // Getters y Setters con validaciones
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo != null && esTipoValido(tipo)) {
            this.tipo = tipo.toLowerCase().trim();
        } else {
            System.out.println("Tipo de gasto no válido. Tipos permitidos: implementos, combustible y peajes, agua y gas, otros");
        }
    }

    // Compara el tipo ingresado con los tipos válidos usando equals()
    private boolean esTipoValido(String tipo) {
        String tipoNormalizado = tipo.toLowerCase().trim();

        if (tipoNormalizado.equals("implementos")) {
            return true;
        } else if (tipoNormalizado.equals("combustible y peajes")) {
            return true;
        } else if (tipoNormalizado.equals("agua y gas")) {
            return true;
        } else if (tipoNormalizado.equals("otros")) {
            return true;
        } else {
            return false;
        }
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        if (monto >= 0) {
            this.monto = monto;
        } else {
            System.out.println("El monto no debe ser negativo");
        }
    }

    // Implementación de métodos abstractos de Movimiento
    @Override
    public double calcularTotal() {
        return monto;
    }

    @Override
    public boolean esIngreso() {
        return false;
    }

    // Implementación de métodos de la interfaz IReportable
    @Override
    public String reporteTexto() {
        return "Gasto Fijo (" + tipo + "): " + getDescripcion() + " - Total: S/ " + monto;
    }

    @Override
    public int reporteMontoMes() {
        return (int) Math.round(monto);
    }
}