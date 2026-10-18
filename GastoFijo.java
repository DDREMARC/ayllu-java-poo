import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class GastoFijo extends Movimiento implements IReportable {
    private String tipo;
    private double monto;

    // Lista de tipos válidos según la especificación
    private static final List<String> TIPOS_VALIDOS = Arrays.asList(
            "implementos",
            "combustible y peajes",
            "agua y gas",
            "otros"
    );

    public GastoFijo(int id, LocalDate fecha, String descripcion, String tipo, double monto) {
        super(id, fecha, descripcion);
        setTipo(tipo);
        setMonto(monto);
    }

    // Getters y Setters con validaciones
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo != null && TIPOS_VALIDOS.contains(tipo.toLowerCase().trim())) {
            this.tipo = tipo.toLowerCase().trim();
        } else {
            throw new IllegalArgumentException("Tipo de gasto no válido. Tipos permitidos: " + TIPOS_VALIDOS);
        }
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        if (monto >= 0) {
            this.monto = monto;
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
        return "Gasto Fijo (" + tipo + "): " + getDescripcion() + " - Total: $" + monto;
    }

    @Override
    public int reporteMontoMes() {
        return (int) Math.round(monto);
    }
}