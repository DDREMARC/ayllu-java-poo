// VISTA: unica clase con la interfaz de consola (Scanner + while + switch).
// No ejecuta SQL directamente, solo llama a los metodos de los
// controladores (VentaController, PersonaController, GastoFijoController).
//
// Que falta hacer aqui:
// 1. Atributo Connection (se abre aqui con DriverManager.getConnection, al
//    inicio del programa) y un Scanner compartido.
// 2. Un VentaController, un PersonaController y un GastoFijoController,
//    creados pasandoles la misma Connection.
// 3. Metodo iniciar(): conecta a la base de datos, muestra el menu
//    principal (1. Ventas, 2. Personas, 3. Gastos fijos, 0. Salir) en un
//    do-while con switch, y cierra la conexion y el Scanner al salir.
// 4. Un submenu por cada entidad (menuVentas, menuPersonas,
//    menuGastosFijos) con las opciones: registrar, listar, buscar,
//    actualizar, eliminar, volver.
// 5. Metodos privados para leer datos de forma segura (leerEntero,
//    leerDouble, leerTexto), protegidos con hasNextInt()/hasNextDouble()
//    para que una letra no rompa el programa, igual que en el Lab 10.
public class MainView {

}
