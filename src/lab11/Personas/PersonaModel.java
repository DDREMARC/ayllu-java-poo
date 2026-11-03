// MODELO: representa UNA fila de la tabla "personas" (clientes y
// empleados juntos, igual que en el Lab 10).
//
// Columnas: codigo_persona, nombre, numero_telefono, es_empleado (1 o 0),
// y luego los campos que solo aplican segun el tipo:
//   es_empleado = 1 (empleado) -> cargo, pago_por_dia, dias_trabajados, bono
//   es_empleado = 0 (cliente)  -> zona_residencia, deuda_total
//
// Que falta hacer aqui:
// 1. Atributos privados para TODAS las columnas (aunque una persona solo
//    use la mitad, el modelo representa la tabla completa).
// 2. Constructor(es).
// 3. Getters y setters.
// 4. (Opcional) un metodo mostrar() que imprima distinto segun
//    es_empleado, como hacian en Personas.mostrarFila(...) del Lab 10.
public class PersonaModel {
    private String codigoPersona;
    private String nombre;
    private String numeroTelefono;
    private int esEmpleado;
    private String zonaResidencia;
    private double deudaTotal;
    private String cargo;
    private double pagoPorDia;
    private int diasTrabajados;
    private double bono;

    public PersonaModel(String codigoPersona, String nombre, String numeroTelefono, int esEmpleado,
                        String zonaResidencia, double deudaTotal,
                        String cargo, double pagoPorDia, int diasTrabajados, double bono) {
        this.codigoPersona = codigoPersona;
        this.nombre = nombre;
        this.numeroTelefono = numeroTelefono;
        this.esEmpleado = esEmpleado;
        this.zonaResidencia = zonaResidencia;
        this.deudaTotal = deudaTotal;
        this.cargo = cargo;
        this.pagoPorDia = pagoPorDia;
        this.diasTrabajados = diasTrabajados;
        this.bono = bono;
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

    public void mostrarPersona() {
        System.out.println("Codigo: " + codigoPersona);
        System.out.println("Nombre: " + nombre);
        System.out.println("Telefono: " + numeroTelefono);
        if (esEmpleado == 1) {
            System.out.println("Tipo: Empleado");
            System.out.println("Cargo: " + cargo);
            System.out.println("Pago por dia: " + pagoPorDia);
            System.out.println("Dias trabajados: " + diasTrabajados);
            System.out.println("Bono: " + bono);
        } else {
            System.out.println("Tipo: Cliente");
            System.out.println("Zona: " + zonaResidencia);
            System.out.println("Deuda total: " + deudaTotal);
        }
    }
}
