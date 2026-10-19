
Movimiento · JAVA
//Movimiento representa cualquier operación o transaccion economica que tenga que ver con el flujo de caja(dinero) del negocio

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
        }
    }

    public void setFecha(String fecha) {
        if (fecha != null && !fecha.equals("")) {
            this.fecha = fecha;
        }
    }

    public void setDescripcion(String descripcion) {
        if (descripcion != null && !descripcion.equals("")) {
            this.descripcion = descripcion;
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

Claude terminó la respuesta
