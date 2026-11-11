package modelo;

public abstract class Persona {
    private String codigoPersona;
    private String nombre;
    private String numeroTelefono;

    public Persona(String codigoPersona, String nombre, String numeroTelefono) {
        this.codigoPersona = codigoPersona;
        this.nombre = nombre;
        this.numeroTelefono = numeroTelefono;
    }

    public String getCodigoPersona() {
        return codigoPersona;
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

    public abstract boolean esEmpleado();

    public abstract String obtenerResumen();

    public void mostrarDatos() {
        System.out.println("Codigo: " + codigoPersona + " | Nombre: " + nombre
                + " | Telefono: " + numeroTelefono);
        System.out.println("   " + obtenerResumen());
    }
}
