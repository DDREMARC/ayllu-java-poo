public abstract class Persona {
    private String nombre;
    private String numeroTelefono;
    private String codigoPersona;

    public Persona(String nombre, String numeroTelefono, String codigoPersona) {
        setNombre(nombre);
        setNumeroTelefono(numeroTelefono);
        this.codigoPersona = codigoPersona;
    }
    //==================================================================================================================

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        if (nombre != null && !nombre.equals("")) {
            this.nombre = nombre;
        } else {
            System.out.println("El nombre es obligatorio");
        }
    }
    public String getNumeroTelefono() {
        return numeroTelefono;
    }
    public void setNumeroTelefono(String numeroTelefono) {
        if (numeroTelefono != null && !numeroTelefono.equals("")) {
            this.numeroTelefono = numeroTelefono;
        } else  {
            System.out.println("El telefono es obligatorio");
        }

    }
    public String getCodigoPersona() {
        return codigoPersona;
    }

    //==================================================================================================================

    public abstract String obtenerResumen();

    public void mostrarDatos(){
        System.out.println("Nombre: " + getNombre());
        System.out.println("Telefono: " + getNumeroTelefono());
        System.out.println("Codigo: " + getCodigoPersona());
    }

}
