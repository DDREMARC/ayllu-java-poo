# Laboratorio 11 — Cómo nos repartimos el trabajo

Equipo: **Angel, Dan y Leo**
Negocio: venta de pollos (los mismos datos que venimos usando: ventas, personas, gastos fijos)

## ¿Qué cambia respecto al Lab 10?

En el Lab 10 todo podía ir junto en una sola clase por tabla. En este laboratorio el profesor sí pide que separemos cada tabla en **3 partes**:

1. **Modelo** → es solo una "ficha" con los datos de una fila (por ejemplo, una venta: su precio, su peso, etc.). No se conecta a la base de datos, solo guarda información.
2. **Controlador** → es el que sí habla con la base de datos. Aquí va todo el registrar/listar/buscar/actualizar/eliminar.
3. **Vista** → es el menú que ve el usuario en la consola. Es una sola clase para todo el programa (no una por tabla).

También cambia la forma de mandar los datos a la base de datos: ya no escribimos el valor pegado dentro del texto SQL, sino que dejamos un `?` como "espacio en blanco" y después decimos qué valor va ahí. Eso se llama **PreparedStatement** y es justo lo nuevo de esta semana — sirve para que un dato raro que escriba el usuario no pueda "romper" la consulta.

## Cómo nos repartimos (igual que en el Lab 10, por tabla)

| Tabla | Archivos | Quién sigue con eso |
|---|---|---|
| Ventas | `VentaModel.java` + `VentaController.java` | **Angel** (ya la conoce del Lab 10) |
| Personas (clientes y empleados) | `PersonaModel.java` + `PersonaController.java` | **quien hizo `Personas.java` en el Lab 10** |
| Gastos fijos | `GastoFijoModel.java` + `GastoFijoController.java` | **quien hizo `GastosFijos.java` en el Lab 10** |

Los archivos ya están creados en la carpeta `lab11/` del repo, vacíos pero con comentarios adentro explicando qué tiene que llevar cada uno.

## Clase extra que sugirió el profesor: `DatabaseConnector.java`

Es la clase que abre la conexión a la base de datos, para que los tres controladores no repitan los datos de conexión. Cada controlador (`VentaController`, `PersonaController`, `GastoFijoController`) recibe la conexión por su constructor. Ya está creada vacía con comentarios; Angel la termina.

## Lo que se arma al final, entre los tres

- **`MainView.java` y `Main.java`** (el menú principal): hay que esperar a que las 3 tablas tengan su controlador listo antes de armar el menú que las une. Lo puede armar quien hizo `MenuPrincipal.java` en el Lab 10, con ayuda de los otros dos para probarlo.
- **`script_lab11.sql`**: cada uno pega la parte de su tabla (la misma de los scripts del Lab 10), y se juntan en un solo archivo.
- **Las capturas de pantalla**: cada uno saca las de su propia tabla, probando que registrar/listar/buscar/actualizar/eliminar funcionan de verdad contra la base de datos.
- **El archivo de texto único de entrega**: se junta todo el código de todas las clases al final, cuando ya esté probado y funcionando.

## Antes de avisar que está listo, revisar entre los tres

- [ ] Las 3 tablas tienen las 5 operaciones completas (no solo 2 o 3) — esto bajó nota en el Lab 9.
- [ ] Ningún `Scanner` se rompe si alguien escribe una letra en vez de un número.
- [ ] En cada controlador, los datos que escribe el usuario van siempre con `PreparedStatement` (con `?`), nunca pegados directamente en el texto del SQL.
- [ ] El menú no se cae si alguien elige una opción que no existe (como "9" o "-1").
- [ ] Las 3 clases compilan juntas, no solo cada una por separado.
