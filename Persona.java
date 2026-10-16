public abstract class Persona {
    private String nombre;
    private String numeroTelefono;
    private String codigoPersona;

    public Persona(String nombre, String numeroTelefono, String codigoPersona) {
        this.nombre = nombre;
        this.numeroTelefono = numeroTelefono;
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

    public String getCodigoPersona() {
        return codigoPersona;
    }


    public void mostrarDatos(){
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Telefono: " + this.numeroTelefono);
    }

    public float dinero(){
        return 0;
    }
}