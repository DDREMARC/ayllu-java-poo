package modelo;

public class TipoGasto {
    private int idTipo;
    private String nombre;

    public TipoGasto(int idTipo, String nombre) {
        this.idTipo = idTipo;
        this.nombre = nombre;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public String getNombre() {
        return nombre;
    }
}
