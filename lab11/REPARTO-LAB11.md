# Laboratorio 11 — Cómo nos repartimos el trabajo

Equipo: **Angel, Dan y Leo**
Negocio: venta de pollos (los mismos datos que venimos usando: ventas, personas, gastos fijos)
Base de datos: `ayllu_pollos` (XAMPP, usuario `root`, sin contraseña)

> Regla del curso: el código lo escribimos nosotros, cada uno entendiendo lo que hace. Este documento solo dice **qué** hay que hacer y **quién**, no trae el código hecho.

---

## 1. ¿Qué nos pide el laboratorio?

- Separar el programa en **3 partes** (se llama MVC):
  - **Modelo** → una "ficha" con los datos de una fila. No habla con la base de datos. Mínimo 3 (uno por tabla).
  - **Controlador** → el que sí habla con la base de datos. Tiene la lista temporal de fichas y los 5 métodos: registrar, listar, buscar, actualizar, eliminar. Mínimo 3 (uno por tabla).
  - **Vista** → el menú de la consola. **Una sola** para todo el programa.
- Usar **PreparedStatement** (con `?`) en todo lo que reciba datos del usuario: registrar, buscar, actualizar, eliminar. Solo `listar` (que no recibe nada) puede usar `Statement` normal.
- Un menú por consola con `Scanner`, `while` y `switch`.
- Entregar: el script SQL, el código de todas las clases en **un solo archivo de texto**, y las capturas de pantalla de que todo funciona.
- Se califica sobre 20 puntos, como los laboratorios anteriores.

---

## 2. Cómo se explica en palabras simples

| Pieza | Para qué sirve | Ejemplo con ventas |
|---|---|---|
| Modelo | Guarda los datos de una fila | `VentaModel`: precio, peso, pago, cliente, fecha |
| Controlador | Habla con la base de datos | `VentaController`: guarda, trae, busca, cambia y borra ventas |
| Vista | Lo que ve y escribe el usuario | `MainView`: el menú y las preguntas por consola |
| Conector | Abre la conexión a la base de datos | `DatabaseConnector` (clase que sugirió el profesor) |

El **PreparedStatement** es dejar un `?` como "espacio en blanco" en el texto SQL y después decir qué valor va en cada espacio. Así lo que escribe el usuario nunca se mezcla con la consulta.

---

## 3. Archivos del laboratorio (carpeta `lab11/`)

```
lab11/
├── Ventas/       VentaModel.java, VentaController.java
├── Personas/     PersonaModel.java, PersonaController.java
├── GastosFijos/  GastoFijoModel.java, GastoFijoController.java
├── DatabaseConnector.java   (UNA sola, compartida — ver aviso abajo)
├── MainView.java            (la vista)
├── Main.java                (solo arranca la vista)
├── script_lab11.sql
├── PENDIENTES-LAB11.md
└── REPARTO-LAB11.md         (este archivo)
```

> **Aviso importante:** ahora mismo hay **tres copias** del conector (`DatabaseConectorVentas.java`, `DatabaseConectorPersonas.java`, `DatabaseConectorGastosFijos.java`), y las tres tienen adentro una clase llamada `DatabaseConector`. Así **no va a compilar** (tres clases con el mismo nombre). Debe haber **una sola** clase `DatabaseConnector` que usen los tres controladores. Angel la termina y las otras dos copias se borran. Nadie más crea su propio conector.

---

## 4. Reparto por persona

> Propuesto según quién hizo cada parte en el Lab 10 (Angel: Ventas; Dan: Personas; Leo: el menú principal). Si prefieren cambiarlo, edítenlo aquí y avisen a los otros dos.

### Angel — Ventas + el conector compartido

Archivos: `Ventas/VentaModel.java`, `Ventas/VentaController.java`, `DatabaseConnector.java`

Datos de la tabla `ventas`: `Codigo_venta` (lo genera solo la base de datos), `Precio`, `Peso`, `Pago_pollo`, `Fecha` (AAAA-MM-DD), `Codigo_Cliente` (texto).

- [x] `VentaModel` terminado.
- [ ] `VentaController`: atributos (`Connection` y la lista de ventas) y constructor que recibe la conexión.
- [ ] `registrar`, `listar`, `buscarPorId`, `actualizar` y `eliminar` (los 4 con parámetros, con `PreparedStatement`).
- [ ] Cada método avisa si funcionó o si no se encontró la venta.
- [ ] `DatabaseConnector`: guarda url, usuario y contraseña, abre la conexión, la entrega con un método (getter) y la cierra al salir.
- [ ] Borrar las otras dos copias del conector y avisar a Dan y a Leo.

### Dan — Personas (clientes y empleados)

Archivos: `Personas/PersonaModel.java`, `Personas/PersonaController.java`

Datos de la tabla `personas` (una sola tabla para clientes y empleados): `codigo_persona`, `nombre`, `numero_telefono`, `es_empleado` (0 = cliente, 1 = empleado), `cargo`, `pago_por_dia`, `dias_trabajados`, `bono` (estos tres últimos solo para empleados), `zona_residencia`, `deuda_total` (estos dos últimos solo para clientes).

- [ ] `PersonaModel`: todos los campos, constructor, getters y setters, y un método para mostrar.
- [ ] `PersonaController`: atributos y constructor igual que el de Ventas (recibe la conexión, crea la lista vacía).
- [ ] Registrar: distinguir si es cliente o empleado y llenar solo los campos que corresponden.
- [ ] Listar, buscar, actualizar (según sea cliente o empleado) y eliminar.
- [ ] Todos con `PreparedStatement`, menos listar.
- [ ] Cuidar los campos que no aplican (por ejemplo `bono` en un cliente).

### Leo — Gastos fijos + la vista

Archivos: `GastosFijos/GastoFijoModel.java`, `GastosFijos/GastoFijoController.java`, `MainView.java`, `Main.java`

Datos de la tabla `gasto_fijo`: `id_gasto` (automático), `id_tipo`, `fecha`, `monto`. Los tipos están en la tabla `tipo_gasto`: 1 = Implementos, 2 = Combustible y peajes, 3 = Agua y gas.

- [ ] `GastoFijoModel` y `GastoFijoController`: lo mismo que en Ventas, con los campos de gasto.
- [ ] Registrar con `PreparedStatement`, validando que el tipo sea 1, 2 o 3.
- [ ] Listar, buscar, actualizar y eliminar.
- [ ] `MainView`: **solo después** de que los tres controladores existan. Menú principal (1. Ventas, 2. Personas, 3. Gastos fijos, 0. Salir) y un submenú por tabla (registrar, listar, buscar, actualizar, eliminar, volver).
- [ ] En `MainView` vive el `Scanner`: lee los datos, arma el modelo y se lo pasa al controlador. Los controladores no leen nada del teclado.
- [ ] Lectura segura de números y decimales para que una letra no rompa el programa.
- [ ] `Main.java`: solo crea la vista y la arranca.

### Entre los tres

- [ ] `script_lab11.sql`: cada uno pega el `CREATE TABLE` de su tabla (los mismos del Lab 10) y unos `INSERT` de prueba. Se juntan en un solo archivo.
- [ ] Capturas de pantalla: cada uno saca las de su tabla (registrar, listar, buscar, actualizar, eliminar), y se incluye una donde se vea el `PreparedStatement` en el código.
- [ ] Archivo de entrega: se junta el código de todas las clases en **un solo archivo de texto**, al final y cuando todo ya funcione.

---

## 5. Reglas que usamos los tres (para que todo encaje)

1. **Mismo molde en los tres controladores:** un atributo `Connection`, un atributo con la lista de fichas, un constructor que recibe la conexión y crea la lista vacía.
2. **Nombres de métodos iguales:** `registrar`, `listar`, `buscarPorId`, `actualizar`, `eliminar`. Así `MainView` se arma fácil.
3. **Los controladores no usan `Scanner` ni abren la conexión.** El `Scanner` está en `MainView` y la conexión llega por el constructor.
4. **Nombres de columnas:** en el SQL y en los `get("...")` del `ResultSet` van los nombres de la **base de datos** (`Pago_pollo`, `Codigo_Cliente`), no los de las variables de Java.
5. **`PreparedStatement`:** cada `?` lleva su `set`, **en el mismo orden**, y la cuenta de `?` empieza en 1.
6. **Tipos:** dinero y pesos como `double` (no `float`); código de cliente como texto (`String`).
7. **Cerrar** `PreparedStatement`, `Statement` y `ResultSet` al terminar cada método.
8. **Convención de nombres:** variables en minúscula con mayúsculas intermedias (`pagoPollo`), clases con mayúscula inicial.

---

## 6. Orden de trabajo sugerido

1. Cada uno termina su **modelo** (es lo más fácil y todos lo necesitan).
2. Angel termina el **conector compartido** y avisa. Mientras tanto, Dan y Leo siguen con sus controladores.
3. Cada uno termina su **controlador**, un método a la vez (registrar primero).
4. Leo arma **MainView** cuando los tres controladores existan.
5. Se junta el **script SQL** y se prueba todo con XAMPP prendido.
6. Capturas de pantalla.
7. Se arma el archivo único y se entrega.

---

## 7. Cómo trabajar en GitHub sin pisarnos

- **Antes de empezar a trabajar:** `Git → Pull` en IntelliJ.
- Cada uno edita **solo sus archivos** (su carpeta). Si hay que tocar un archivo de otro, avisarle antes.
- Subir cambios seguido y chicos: `Commit` con un mensaje claro (por ejemplo, "Lab 11: registrar de personas") y luego `Push`.
- Si IntelliJ avisa de un conflicto, **no elegir a ciegas**: llamar a la persona dueña del archivo.
- No subir archivos de IntelliJ (`.idea`, `out`).

---

## 8. Antes de avisar que está listo, revisar entre los tres

- [ ] Las 3 tablas tienen las 5 operaciones completas (no solo 2 o 3). Esto bajó nota en el Lab 9.
- [ ] Hay **una sola** clase `DatabaseConnector` y todo compila junto, no solo cada archivo por separado.
- [ ] Ningún `Scanner` se rompe si alguien escribe una letra en vez de un número.
- [ ] Los datos del usuario siempre van con `?` y `PreparedStatement`, nunca pegados en el texto del SQL.
- [ ] El menú no se cae si alguien elige una opción que no existe (como "9" o "-1").
- [ ] Cada método avisa claramente si funcionó o si no encontró nada.
- [ ] Se probó todo con la base de datos real (XAMPP prendido), no solo que compile.
- [ ] El script SQL crea las tablas desde cero y funciona en una computadora limpia.
- [ ] Las capturas muestran cada operación de las 3 tablas.
- [ ] El archivo de texto único tiene el código de **todas** las clases.
