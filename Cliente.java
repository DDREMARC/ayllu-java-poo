public class Cliente extends Persona {
    private String zonaResidencia;
    private float deudaTotal;

    public Cliente(String nombre, String numeroTelefono, String codigoPersona, String zonaResidencia, float deudaTotal) {
        super(nombre, numeroTelefono, codigoPersona);
        this.zonaResidencia = zonaResidencia;
        this.deudaTotal = deudaTotal;
    }

    //==================================================================================================================

    public String getZonaResidencia() {
        return zonaResidencia;
    }
    public void setZonaResidencia(String zonaResidencia) {
        this.zonaResidencia = zonaResidencia;
    }
    public float getDeudaTotal() {
        return deudaTotal;
    }
    public void setDeudaTotal(float deudaTotal) {
        this.deudaTotal = deudaTotal;
    }

    @Override
    public void mostrarDatos(){
        System.out.println("Nombre: " + getNombre());
        System.out.println("Telefono: " + getNumeroTelefono());
        System.out.println("Codigo: " + getCodigoPersona());
    }
    public String obtenerResumen(){
        return "Cliente: " + getNombre() + " | Zona: " + zonaResidencia + " | Deuda: " + deudaTotal;
    }


    //==================================================================================================================
    public void aumentarDeuda(float monto){
        deudaTotal += monto;
    }
    public void reducirDeuda(float monto){
        deudaTotal -= monto;
        if (deudaTotal < 0){
            deudaTotal = 0;
        }
    }
    public boolean tieneDeuda(){
        return deudaTotal > 0;
    }
}