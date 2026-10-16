public class Cliente extends Persona {
    private String zonaResidencia;
    private float deudaTotal;

    public Cliente(String nombre, String numeroTelefono, String codigoPersona, String zonaResidencia, float deudaTotal) {
        super(nombre, numeroTelefono, codigoPersona);
        this.zonaResidencia = zonaResidencia;
        this.deudaTotal = deudaTotal;
    }

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

    public void dinero(deudaFinal) {
        Scanner aumetarDeuda = new Scanner(System.in);
        float duedaNueva = System.out.print("Monto de deuda: ")
        float deudaNueva +deudaTotal = deudaFinal
        return deudaFinal;
    }
    public void deudaPendiente(){
        if(deudaTotal < 0){
            System.out.println("Usted no tiene deuda pendiente");
            break;
        }
        else {
            this.dueudaFinal;
            break;
        }
    }
}