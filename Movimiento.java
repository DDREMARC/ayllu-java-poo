public abstract class Movimiento {
    private int id;
    private String fecha;
    private String descripcion;

    //constructor

    public Movimiento(int id, String fecha, String descripcion) {
        setId(id);
        setFecha(fecha);
        setDescripcion(descripcion);
    }

    //getters para obtener los valores debido al encapsulamiento

    public int getId() {return id;}
    public String getFecha() {return fecha;}
    public String getDescripcion() {return descripcion;}

    //setters básicos parametrando atributos para evitar errores

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            System.out.println("El id debe ser mayor a cero");
        }
    }

    public void setFecha(String fecha) {
        if (fecha != null && !fecha.equals("")) {
            this.fecha = fecha;
        } else {
            System.out.println("La fecha es obligatoria");
        }
    }

    public void setDescripcion(String descripcion) {
        if (descripcion != null && !descripcion.equals("")) {
            this.descripcion = descripcion;
        } else {
            System.out.println("La descripcion es obligatoria");
        }
    }

    //metodo mostrar movimiento (info) cuando usemos el main

    public String mostrarMovimiento() {
        return "ID: " + id
                + ", Fecha: " + fecha
                + ", Descripción: " + descripcion;
    }

    //metodos abstracto: Calculo

    public abstract double calcularTotal();
    public abstract boolean esIngreso();
}