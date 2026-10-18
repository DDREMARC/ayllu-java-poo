public class Cliente extends Persona {
    private String zonaResidencia;
    private double deudaTotal;

    public Cliente(String nombre, String numeroTelefono, String codigoPersona, String zonaResidencia, double deudaTotal) {
        super(nombre, numeroTelefono, codigoPersona);
        setZonaResidencia(zonaResidencia);
        setDeudaTotal(deudaTotal);
    }

    //==================================================================================================================

    public String getZonaResidencia() {
        return zonaResidencia;
    }
    public void setZonaResidencia(String zonaResidencia) {
        if (zonaResidencia != null && !zonaResidencia.equals("")) {
            this.zonaResidencia = zonaResidencia;
        } else {
            System.out.println("Debe ingresar una zona residencia");
        }
    }
    public double getDeudaTotal() {
        return deudaTotal;
    }
    public void setDeudaTotal(double deudaTotal) {
        if (deudaTotal >= 0) {
            this.deudaTotal = deudaTotal;
        } else  {
            System.out.println("La deuda no debe ser negativo");
        }
    }

    @Override
    public void mostrarDatos(){
        System.out.println("Nombre: " + getNombre());
        System.out.println("Telefono: " + getNumeroTelefono());
        System.out.println("Codigo: " + getCodigoPersona());
        System.out.println("Zona Residencia: " + getZonaResidencia());
        System.out.println("Deuda Total: " + getDeudaTotal());
    }
    @Override
    public String obtenerResumen(){
        return "Cliente: " + getNombre() + " | Zona: " + zonaResidencia + " | Deuda: " + deudaTotal;
    }


    //==================================================================================================================
    public void aumentarDeuda(double monto){
        deudaTotal += monto;
    }
    public void reducirDeuda(double monto){
        deudaTotal -= monto;
        if (deudaTotal < 0){
            deudaTotal = 0;
        }
    }
    public boolean tieneDeuda(){
        return deudaTotal > 0;
    }
}