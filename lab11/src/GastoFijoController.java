// CONTROLADOR: administra una coleccion temporal de GastoFijoModel y hace
// el CRUD real contra la tabla "gasto_fijo" usando JDBC + PreparedStatement.
//
// Que falta hacer aqui:
// 1. Atributo Connection + lista temporal de GastoFijoModel.
// 2. Constructor que recibe la Connection.
// 3. Metodo registrar(idTipo, monto): PreparedStatement con INSERT.
// 4. Metodo listar(): Statement (sin parametros) + recorrer ResultSet.
// 5. Metodo buscarPorId(idGasto): PreparedStatement con WHERE id_gasto = ?
// 6. Metodo actualizar(idGasto, idTipo, monto): PreparedStatement con
//    UPDATE ... WHERE id_gasto = ?
// 7. Metodo eliminar(idGasto): PreparedStatement con DELETE.
// 8. try/catch de SQLException en cada metodo, cerrando los recursos.
public class GastoFijoController {

}
