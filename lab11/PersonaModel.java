// Clase MODELO: representa una fila de la tabla "personas".
// La tabla guarda clientes y empleados juntos; esEmpleado (1 o 0) dice
// cuál de los dos grupos de campos aplica (los del otro grupo quedan en 0/vacío).
public class PersonaModel {
    private String codigoPersona;
    private String nombre;
    private String numeroTelefono;
    private int esEmpleado; // 1 = empleado, 0 = cliente

    // Solo aplican si esEmpleado == 1
    private String cargo;
    private double pagoPorDia;
    private int diasTrabajados;
    private double bono;

    // Solo aplican si esEmpleado == 0
    private String zonaResidencia;
    private double deudaTotal;

    public PersonaModel() {
    }

    public String getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(String codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public int getEsEmpleado() {
        return esEmpleado;
    }

    public void setEsEmpleado(int esEmpleado) {
        this.esEmpleado = esEmpleado;
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

    public double getPagoTotalEmpleado() {
        return diasTrabajados * pagoPorDia + bono;
    }

    public void mostrar() {
        System.out.println("Codigo:   " + codigoPersona);
        System.out.println("Nombre:   " + nombre);
        System.out.println("Telefono: " + numeroTelefono);
        if (esEmpleado == 1) {
            System.out.println("Tipo:            EMPLEADO");
            System.out.println("Cargo:           " + cargo);
            System.out.println("Pago por dia:    " + pagoPorDia);
            System.out.println("Dias trabajados: " + diasTrabajados);
            System.out.println("Bono:            " + bono);
            System.out.println("Pago total:      " + getPagoTotalEmpleado());
        } else {
            System.out.println("Tipo:               CLIENTE");
            System.out.println("Zona de residencia: " + zonaResidencia);
            System.out.println("Deuda total:        " + deudaTotal);
        }
    }
}
