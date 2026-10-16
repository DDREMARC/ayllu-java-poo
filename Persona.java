public abstract class Persona {
    protected String nombre;
    protected String numeroTelefono;

    public Persona(String nombre, String numeroTelefono) {
        this.nombre = nombre;
        this.numeroTelefono = numeroTelefono;
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

    public abstract void mostrarDatos(){
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Telefono: " + this.numeroTelefono);
    }

    public void dinero(){
        return 0;
    }
}