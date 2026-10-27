// CONTROLADOR: administra una coleccion temporal de VentaModel y ejecuta
// el CRUD real contra la base de datos (tabla "ventas") usando JDBC.
//
// Regla clave de esta semana: usar PreparedStatement en toda operacion que
// lleve un dato ingresado por el usuario (registrar, buscar por id,
// actualizar, eliminar). Statement solo se puede usar donde NO hay
// parametros (por ejemplo, listar todo con "SELECT * FROM ventas").
//
// Que falta hacer aqui:
// 1. Un atributo Connection (la conexion que le pasa la vista).
// 2. Un ArrayList<VentaModel> para guardar temporalmente lo que se lee de
//    la base de datos.
// 3. Constructor que recibe la Connection.
// 4. Metodo registrar(...): PreparedStatement con INSERT, un "?" por cada
//    valor que escribe el usuario, asignar con setInt/setDouble/setString
//    segun el tipo (ojo: los "?" se numeran desde 1, no desde 0).
// 5. Metodo listar(): Statement con "SELECT * FROM ventas", recorrer el
//    ResultSet con while (rs.next()), armar un VentaModel por fila y
//    guardarlo en la lista.
// 6. Metodo buscarPorId(...): PreparedStatement con WHERE Codigo_venta = ?
// 7. Metodo actualizar(...): PreparedStatement con UPDATE ... WHERE
//    Codigo_venta = ? (recordar: el ultimo "?" suele ser el de la
//    condicion WHERE).
// 8. Metodo eliminar(...): PreparedStatement con DELETE ... WHERE
//    Codigo_venta = ?
// 9. En cada metodo: try/catch de SQLException, y cerrar PreparedStatement
//    y ResultSet cuando corresponda (try-with-resources es una opcion).
public class VentaController {

}
