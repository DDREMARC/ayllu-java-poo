// Clase auxiliar (la sugirio el profesor): se encarga SOLO de abrir la conexion
// a la base de datos ayllu_pollos. Los controladores no se conectan por su cuenta,
// reciben la Connection por su constructor.
//
// Que falta hacer aqui:
// 1. Atributos: url (jdbc:mysql://localhost:3306/ayllu_pollos), usuario, contraseña
//    y un atributo Connection.
// 2. Un metodo que abre la conexion con DriverManager.getConnection dentro de un
//    try/catch (es el conectarBaseDatos que hoy esta en VentaController).
// 3. Un metodo (getter) que entrega la Connection a quien la pida.
// 4. Opcional: un metodo para cerrar la conexion al salir del programa.
//
// MainView crea un DatabaseConnector, lo conecta, toma la Connection y se la pasa
// a los tres controladores.
public class DatabaseConnector {

}
