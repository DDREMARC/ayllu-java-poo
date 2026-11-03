# Código que falta del Lab 11

Este archivo solo trae el código para copiar y pegar. No toqué ningún `.java`.

| # | Pieza | Va en |
|---|---|---|
| 1 | 👤 `PersonaModel` | `Personas/PersonaModel.java` (reemplaza todo) |
| 2 | 🗄️ `PersonaController` | `Personas/PersonaController.java` (reemplaza todo) |
| 3 | 🖥️ Menús de Personas y Gastos | `MainView.java` (se agregan piezas, ver abajo) |

Después de pegar: borrar `Personas/DatabaseConectorPersonas.java` (copia sobrante).

---

## 1. PersonaModel

```java
public class PersonaModel {
    private String codigoPersona;
    private String nombre;
    private String numeroTelefono;
    private int esEmpleado;
    private String zonaResidencia;
    private float deudaTotal;
    private String cargo;
    private float pagoPorDia;
    private int diasTrabajados;
    private float bono;

    public PersonaModel(String codigoPersona, String nombre, String numeroTelefono, int esEmpleado,
                        String zonaResidencia, float deudaTotal,
                        String cargo, float pagoPorDia, int diasTrabajados, float bono) {
        this.codigoPersona = codigoPersona;
        this.nombre = nombre;
        this.numeroTelefono = numeroTelefono;
        this.esEmpleado = esEmpleado;
        this.zonaResidencia = zonaResidencia;
        this.deudaTotal = deudaTotal;
        this.cargo = cargo;
        this.pagoPorDia = pagoPorDia;
        this.diasTrabajados = diasTrabajados;
        this.bono = bono;
    }

    public String getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(String codigoPersona) {
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

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public int getEsEmpleado() {
        return esEmpleado;
    }

    public void setEsEmpleado(int esEmpleado) {
        this.esEmpleado = esEmpleado;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public float getPagoPorDia() {
        return pagoPorDia;
    }

    public void setPagoPorDia(float pagoPorDia) {
        this.pagoPorDia = pagoPorDia;
    }

    public int getDiasTrabajados() {
        return diasTrabajados;
    }

    public void setDiasTrabajados(int diasTrabajados) {
        this.diasTrabajados = diasTrabajados;
    }

    public float getBono() {
        return bono;
    }

    public void setBono(float bono) {
        this.bono = bono;
    }

    public void mostrarPersona() {
        System.out.println("Codigo: " + codigoPersona);
        System.out.println("Nombre: " + nombre);
        System.out.println("Telefono: " + numeroTelefono);
        if (esEmpleado == 1) {
            System.out.println("Tipo: Empleado");
            System.out.println("Cargo: " + cargo);
            System.out.println("Pago por dia: " + pagoPorDia);
            System.out.println("Dias trabajados: " + diasTrabajados);
            System.out.println("Bono: " + bono);
        } else {
            System.out.println("Tipo: Cliente");
            System.out.println("Zona: " + zonaResidencia);
            System.out.println("Deuda total: " + deudaTotal);
        }
    }
}
```

---

## 2. PersonaController

```java
import java.sql.*;
import java.util.ArrayList;

public class PersonaController {
    private Connection connection;
    private ArrayList<PersonaModel> personasModelo;

    public PersonaController(Connection connection) {
        this.connection = connection;
        this.personasModelo = new ArrayList<>();
    }

    // Dos INSERT: el cliente y el empleado llenan columnas distintas
    public void registrar(PersonaModel persona) {
        try {
            PreparedStatement preparedStatement;

            if (persona.getEsEmpleado() == 1) {
                String query = "INSERT INTO personas (codigo_persona, nombre, numero_telefono, es_empleado, cargo, pago_por_dia, dias_trabajados, bono) VALUES (?, ?, ?, 1, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, persona.getCodigoPersona());
                preparedStatement.setString(2, persona.getNombre());
                preparedStatement.setString(3, persona.getNumeroTelefono());
                preparedStatement.setString(4, persona.getCargo());
                preparedStatement.setFloat(5, persona.getPagoPorDia());
                preparedStatement.setInt(6, persona.getDiasTrabajados());
                preparedStatement.setFloat(7, persona.getBono());
            } else {
                String query = "INSERT INTO personas (codigo_persona, nombre, numero_telefono, es_empleado, zona_residencia, deuda_total) VALUES (?, ?, ?, 0, ?, ?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, persona.getCodigoPersona());
                preparedStatement.setString(2, persona.getNombre());
                preparedStatement.setString(3, persona.getNumeroTelefono());
                preparedStatement.setString(4, persona.getZonaResidencia());
                preparedStatement.setFloat(5, persona.getDeudaTotal());
            }

            int filasCambiadas = preparedStatement.executeUpdate();
            if (filasCambiadas > 0) {
                System.out.println("Se ha registrado con exito");
                personasModelo.add(persona);
            } else {
                System.out.println("No se hizo los cambios");
            }
            preparedStatement.close();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
    }

    // Listar no recibe datos, por eso usa Statement
    public void listar() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM personas");
            personasModelo.clear();

            while (resultSet.next()) {
                personasModelo.add(leerFila(resultSet));
            }

            if (personasModelo.isEmpty()) {
                System.out.println("No hay personas registradas");
            }
            for (int i = 0; i < personasModelo.size(); i++) {
                personasModelo.get(i).mostrarPersona();
                System.out.println("-----");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
    }

    public void buscarPorId(String codigoPersona) {
        try {
            String query = "SELECT * FROM personas WHERE codigo_persona = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, codigoPersona);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                leerFila(resultSet).mostrarPersona();
            } else {
                System.out.println("No se encontro el registro");
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
    }

    // Devuelve 1 si es empleado, 0 si es cliente y -1 si no existe
    // Sirve para saber que datos pedir antes de actualizar
    public int obtenerTipo(String codigoPersona) {
        int tipo = -1;
        try {
            String query = "SELECT es_empleado FROM personas WHERE codigo_persona = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, codigoPersona);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                tipo = resultSet.getInt("es_empleado");
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return tipo;
    }

    public void actualizar(PersonaModel persona) {
        try {
            PreparedStatement preparedStatement;

            if (persona.getEsEmpleado() == 1) {
                String query = "UPDATE personas SET nombre = ?, numero_telefono = ?, cargo = ?, pago_por_dia = ?, dias_trabajados = ?, bono = ? WHERE codigo_persona = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, persona.getNombre());
                preparedStatement.setString(2, persona.getNumeroTelefono());
                preparedStatement.setString(3, persona.getCargo());
                preparedStatement.setFloat(4, persona.getPagoPorDia());
                preparedStatement.setInt(5, persona.getDiasTrabajados());
                preparedStatement.setFloat(6, persona.getBono());
                preparedStatement.setString(7, persona.getCodigoPersona());
            } else {
                String query = "UPDATE personas SET nombre = ?, numero_telefono = ?, zona_residencia = ?, deuda_total = ? WHERE codigo_persona = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, persona.getNombre());
                preparedStatement.setString(2, persona.getNumeroTelefono());
                preparedStatement.setString(3, persona.getZonaResidencia());
                preparedStatement.setFloat(4, persona.getDeudaTotal());
                preparedStatement.setString(5, persona.getCodigoPersona());
            }

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Tabla personas actualizada: " + filasAfectadas);
            } else {
                System.out.println("No se hizo los cambios");
            }
            preparedStatement.close();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
    }

    public void eliminar(String codigoPersona) {
        try {
            String query = "DELETE FROM personas WHERE codigo_persona = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, codigoPersona);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Se ha eliminado con exito");
            } else {
                System.out.println("No se encontro el registro");
            }
            preparedStatement.close();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
    }

    // Convierte la fila actual del ResultSet en un PersonaModel
    private PersonaModel leerFila(ResultSet resultSet) throws SQLException {
        return new PersonaModel(
                resultSet.getString("codigo_persona"),
                resultSet.getString("nombre"),
                resultSet.getString("numero_telefono"),
                resultSet.getInt("es_empleado"),
                resultSet.getString("zona_residencia"),
                resultSet.getFloat("deuda_total"),
                resultSet.getString("cargo"),
                resultSet.getFloat("pago_por_dia"),
                resultSet.getInt("dias_trabajados"),
                resultSet.getFloat("bono"));
    }
}
```

---

## 3. MainView: lo que se agrega

Son 4 cambios en `MainView.java`.

### 3.1 Crear los controladores

En `ejecutar()`, debajo de `VentaController ventaController = ...;`:

```java
PersonaController personaController = new PersonaController(dc.getConnection());
GastoFijoController gastoController = new GastoFijoController(dc.getConnection());
```

### 3.2 Cambiar los `case 2` y `case 3` del `switch` principal

```java
case 2:
    menuPersonas(sc, personaController);
    break;
case 3:
    menuGastos(sc, gastoController);
    break;
```

### 3.3 Método para leer texto con espacios

Pegar junto a `leerEntero` y `leerDouble`. Usa `nextLine()` para que `María López` no se corte en el espacio. Ignora las líneas vacías que quedan después de leer un número.

```java
private String leerTexto(Scanner sc, String mensaje) {
    System.out.print(mensaje);
    String texto = sc.nextLine();
    while (texto.trim().isEmpty()) {
        texto = sc.nextLine();
    }
    return texto.trim();
}
```

### 3.4 Los dos menús

Pegar junto a `menuVentas`.

```java
private void menuPersonas(Scanner sc, PersonaController controller) {
    int opcion = -1;
    while (opcion != 0) {
        System.out.println("--- Menu de personas ---");
        System.out.println("1. Registrar");
        System.out.println("2. Listar");
        System.out.println("3. Buscar");
        System.out.println("4. Actualizar");
        System.out.println("5. Eliminar");
        System.out.println("0. Volver");
        opcion = leerEntero(sc, "> ");

        switch (opcion) {
            case 1: {
                String codigo = leerTexto(sc, "Codigo de persona: ");
                String nombre = leerTexto(sc, "Nombre: ");
                String telefono = leerTexto(sc, "Telefono: ");
                int tipo = leerEntero(sc, "Es empleado? (1 = si, 0 = no): ");

                if (tipo == 1) {
                    String cargo = leerTexto(sc, "Cargo: ");
                    float pagoPorDia = (float) leerDouble(sc, "Pago por dia: ");
                    int dias = leerEntero(sc, "Dias trabajados: ");
                    float bono = (float) leerDouble(sc, "Bono: ");
                    controller.registrar(new PersonaModel(codigo, nombre, telefono, 1, null, 0, cargo, pagoPorDia, dias, bono));
                } else if (tipo == 0) {
                    String zona = leerTexto(sc, "Zona de residencia: ");
                    float deuda = (float) leerDouble(sc, "Deuda inicial: ");
                    controller.registrar(new PersonaModel(codigo, nombre, telefono, 0, zona, deuda, null, 0, 0, 0));
                } else {
                    System.out.println("Tipo invalido, escribe 1 o 0");
                }
                break;
            }
            case 2:
                controller.listar();
                break;
            case 3: {
                String codigo = leerTexto(sc, "Codigo de persona: ");
                controller.buscarPorId(codigo);
                break;
            }
            case 4: {
                String codigo = leerTexto(sc, "Codigo de persona a actualizar: ");
                int tipo = controller.obtenerTipo(codigo);

                if (tipo == -1) {
                    System.out.println("No se encontro el registro");
                } else {
                    String nombre = leerTexto(sc, "Nombre: ");
                    String telefono = leerTexto(sc, "Telefono: ");

                    if (tipo == 1) {
                        String cargo = leerTexto(sc, "Cargo: ");
                        float pagoPorDia = (float) leerDouble(sc, "Pago por dia: ");
                        int dias = leerEntero(sc, "Dias trabajados: ");
                        float bono = (float) leerDouble(sc, "Bono: ");
                        controller.actualizar(new PersonaModel(codigo, nombre, telefono, 1, null, 0, cargo, pagoPorDia, dias, bono));
                    } else {
                        String zona = leerTexto(sc, "Zona de residencia: ");
                        float deuda = (float) leerDouble(sc, "Deuda total: ");
                        controller.actualizar(new PersonaModel(codigo, nombre, telefono, 0, zona, deuda, null, 0, 0, 0));
                    }
                }
                break;
            }
            case 5: {
                String codigo = leerTexto(sc, "Codigo de persona a eliminar: ");
                controller.eliminar(codigo);
                break;
            }
            case 0:
                System.out.println("Volviendo...");
                break;
            default:
                System.out.println("Opcion invalida");
                break;
        }
    }
}

private void menuGastos(Scanner sc, GastoFijoController controller) {
    int opcion = -1;
    while (opcion != 0) {
        System.out.println("--- Menu de gastos fijos ---");
        System.out.println("1. Registrar");
        System.out.println("2. Listar");
        System.out.println("3. Buscar");
        System.out.println("4. Actualizar");
        System.out.println("5. Eliminar");
        System.out.println("0. Volver");
        opcion = leerEntero(sc, "> ");

        switch (opcion) {
            case 1: {
                int idTipo = leerTipoGasto(sc);
                double monto = leerDouble(sc, "Monto: ");
                controller.registrar(idTipo, monto);
                break;
            }
            case 2:
                controller.listar();
                break;
            case 3: {
                int id = leerEntero(sc, "Codigo de gasto: ");
                controller.buscarPorId(id);
                break;
            }
            case 4: {
                int id = leerEntero(sc, "Codigo de gasto a actualizar: ");
                int idTipo = leerTipoGasto(sc);
                double monto = leerDouble(sc, "Monto: ");
                controller.actualizar(id, idTipo, monto);
                break;
            }
            case 5: {
                int id = leerEntero(sc, "Codigo de gasto a eliminar: ");
                controller.eliminar(id);
                break;
            }
            case 0:
                System.out.println("Volviendo...");
                break;
            default:
                System.out.println("Opcion invalida");
                break;
        }
    }
}

// Pide el tipo de gasto hasta que sea 1, 2 o 3
private int leerTipoGasto(Scanner sc) {
    int idTipo = leerEntero(sc, "Tipo de gasto (1 = Implementos, 2 = Combustible y peajes, 3 = Agua y gas): ");
    while (idTipo < 1 || idTipo > 3) {
        System.out.println("Tipo invalido");
        idTipo = leerEntero(sc, "Tipo de gasto (1, 2 o 3): ");
    }
    return idTipo;
}
```

---

## Para probar con XAMPP prendido

1. Registrar un cliente y un empleado (códigos nuevos, como `C010` y `E010`).
2. Listar, buscar, actualizar y eliminar cada uno.
3. Repetir lo mismo en gastos.
4. Poner una letra donde se pide un número y un tipo de gasto `9` para ver que no se rompe.
