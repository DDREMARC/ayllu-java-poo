// CONTROLADOR: administra una coleccion temporal de PersonaModel y hace el
// CRUD real contra la tabla "personas" usando JDBC + PreparedStatement.
//
// Ojo con el registro e insert: como hay dos "tipos" de fila (cliente y
// empleado) con columnas distintas, puede convenir tener dos metodos de
// registro (por ejemplo registrarCliente(...) y registrarEmpleado(...))
// en vez de uno solo con muchos parametros vacios.
//
// Que falta hacer aqui:
// 1. Atributo Connection + lista temporal de PersonaModel.
// 2. Constructor que recibe la Connection.
// 3. Metodo(s) de registro: PreparedStatement con INSERT, usando "?" para
//    cada dato que escribe el usuario (el 1/0 de es_empleado no necesita
//    "?" porque no lo escribe el usuario, lo decide el programa).
// 4. Metodo listar(): Statement (sin parametros) + recorrer ResultSet.
// 5. Metodo buscarPorId(codigoPersona): PreparedStatement con WHERE
//    codigo_persona = ?
// 6. Metodo(s) de actualizacion: antes de actualizar hay que saber si es
//    cliente o empleado (buscarPorId primero), y luego hacer el UPDATE
//    correspondiente con PreparedStatement.
// 7. Metodo eliminar(codigoPersona): PreparedStatement con DELETE.
// 8. try/catch de SQLException en cada metodo, cerrando los recursos.

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
