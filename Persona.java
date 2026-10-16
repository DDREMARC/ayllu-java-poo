public abstract class Persona {
    private String nombre;
    private String numeroTelefono;
    private String codidoPersona;

    public Persona(String nombre, String numeroTelefono, String codidoPersona) {
        this.nombre = nombre;
        this.numeroTelefono = numeroTelefono;
        this.codidoPersona = codidoPersona;
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

    public String getCodidoPersona() {
        return codidoPersona;
    }

    public void setCodidoPersona(String codidoPersona) {
        this.codidoPersona = codidoPersona;
    }

    public void mostrarDatos(){
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Telefono: " + this.numeroTelefono);
    }

    public void dinero(){
        return 0;
    }
}