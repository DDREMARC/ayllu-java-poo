// Hereda los datos de Persona y cumple los métodos de IReportable
public class Empleado extends Persona implements IReportable {
    // Atributos propios del empleado
    private String cargo;
    private double pagoPorDia;
    private int diasTrabajados;
    private double bono;

    // Constructor con datos heredados y propios

    public Empleado(String nombre, String numeroTelefono, String codigoPersona, String cargo, double pagoPorDia, int diasTrabajados, double bono) {

        // Envía los datos heredados al constructor de Persona
        super(nombre, numeroTelefono, codigoPersona);

        // Guarda los datos propios del empleado usando setters para validar
        setCargo(cargo);
        setPagoPorDia(pagoPorDia);
        setDiasTrabajados(diasTrabajados);
        setBono(bono);
    }

    // Métodos generales

    // Devuelve un reporte escrito del empleado
    @Override
    public String reporteTexto() {
        return "Empleado: " + getNombre()
                + ", Cargo: " + cargo
                + ", Pago mensual: " + calcularPago();

    }

    // Devuelve el pago mensual como número entero
    @Override
    public int reporteMontoMes() {
        return (int) Math.round(calcularPago());
    }

    // Implementa el método abstracto de Persona
    @Override
    public String obtenerResumen() {
        return "Empleado: " + getNombre() + ", Cargo: " + cargo;

    }

    // Agrega días trabajados si son mayores que cero
    public void registrarDias(int dias) {
        if (dias > 0) {
            diasTrabajados += dias;
        }
    }

    // Cambia el bono si no es negativo
    public void asignarBono(double bono) {
        setBono(bono);
    }

    // Calcula: días trabajados por pago diario más bono
    public double calcularPago() {
        return diasTrabajados * pagoPorDia + bono;
    }

    // Getter y setter del cargo
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        if (cargo != null && !cargo.equals("")) {
            this.cargo = cargo;
        } else {
            System.out.println("El cargo no puede estar vacío");
        }
    }

    // Getter y setter del pago por día
    public double getPagoPorDia() {
        return pagoPorDia;
    }

    public void setPagoPorDia(double pagoPorDia) {
        if (pagoPorDia > 0) {
            this.pagoPorDia = pagoPorDia;
        } else {
            System.out.println("El pago por día debe ser mayor a cero");
        }
    }

    // Getter y setter de los días trabajados
    public int getDiasTrabajados() {
        return diasTrabajados;
    }

    public void setDiasTrabajados(int diasTrabajados) {
        if (diasTrabajados >= 0) {
            this.diasTrabajados = diasTrabajados;
        } else {
            System.out.println("Los días trabajados no pueden ser negativos");
        }
    }

    // Getter y setter del bono
    public double getBono() {
        return bono;
    }

    public void setBono(double bono) {
        if (bono >= 0) {
            this.bono = bono;
        } else {
            System.out.println("El bono no puede ser negativo");
        }
    }
}