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
public class PersonaController {

}
