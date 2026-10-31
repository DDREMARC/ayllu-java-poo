# Laboratorio 10 — JDBC: Estado y pendientes

Equipo: Angel · Dan · Leo
Proyecto: sistema de venta de pollos — base de datos `ayllu_pollos`

## 1. Lo que ya está listo (tabla `ventas` — Angel)

- [x] Conexión JDBC funcionando (`DriverManager.getConnection`)
- [x] Tabla `ventas` creada, con clave primaria `Codigo_venta` (AUTO_INCREMENT, sin tilde en el nombre)
- [x] CRUD completo: registrar, listar, buscar por código, actualizar, eliminar
- [x] Validación de números (no se rompe si escriben letras)
- [x] Validación de texto vacío (fecha, código de cliente)
- [x] Cierre de `ResultSet`, `Statement`, `Connection` y `Scanner` al salir
- [x] Mensaje cuando la tabla está vacía o no se encuentra un registro
- [x] Estructura separada: `MenuPrincipal.java` (menú general) + `Ventas.java` (submenú de ventas, sin `main` propio)
- [x] Script SQL de la tabla `ventas` exportado y verificado (`lab10/script_ventas.sql`)
- [x] Repositorio organizado: `lab9/` (Laboratorio 9) y `lab10/` (este laboratorio), separados

## 2. Lo que falta

### Código (Dan y Leo)

- [ ] Decidir las **2 tablas restantes** (sugerencia: `clientes` y `empleados`, ya existen como conceptos en el Lab 9)
- [ ] Crear cada tabla en MySQL con su propia clave primaria
- [ ] Por cada tabla, una clase siguiendo el mismo patrón que `Ventas.java`:
  - Variables sueltas (nada de clases tipo `ClienteDB` — la guía dice que no hace falta, todo puede ir en una sola clase)
  - Un método `menuX()` (no `main`) con las 5 operaciones: registrar, listar, buscar por ID, actualizar, eliminar, volver
  - Usar `conexion` y `sc` compartidos desde `Ventas.java` (o moverlos a una clase común si prefieren)
  - Validar números con `hasNextInt()`/`hasNextFloat()` antes de leer (ver checklist abajo, punto crítico)
  - Cerrar `Statement` y `ResultSet` en cada operación
- [ ] Conectar cada `menuX()` nuevo a `MenuPrincipal.java` (reemplazar el "(próximamente)" por la llamada real)

### Entregables (documento, no código)

- [ ] Descripción del negocio (se puede reusar la del Lab 9, ajustada si hace falta)
- [ ] Descripción de las 3 tablas: campos, tipos de dato, clave primaria
- [ ] Script SQL completo: `CREATE DATABASE` + las 3 tablas + datos de prueba de cada una
- [ ] Capturas de pantalla de **cada** operación, de **cada** tabla, mostrando el resultado en phpMyAdmin:
  - Conexión exitosa
  - Menú principal
  - Submenú de cada tabla
  - Registrar, listar, buscar por ID, actualizar, eliminar (con el cambio reflejado en la base de datos)
- [ ] Juntar todas las clases (`MenuPrincipal.java` + `Ventas.java` + las 2 nuevas) en **un único archivo de texto**, como pide la guía

## 3. Checklist final antes de entregar

Esto es para no repetir lo que pasó en el Lab 9: el profesor marcó cosas que se quedaron a medias cuando ya se creía lista la entrega. Revisar esto **todos**, no solo quien escribió el código, antes de avisar que está listo:

- [ ] **CRUD completo en las 3 tablas, no solo en algunas.** En el Lab 9 el profesor bajó nota porque solo se podían modificar/eliminar ventas y gastos, pero no clientes ni empleados. Que no pase lo mismo acá: las 3 tablas necesitan las 5 operaciones, no solo 2 o 3.
- [ ] **Todo número leído por `Scanner` está protegido.** En el Lab 9 el profesor marcó que `scanner.nextInt()`/`nextDouble()` sin protección cerraban el programa con cualquier letra. Revisar que las 3 tablas usen `hasNextInt()`/`hasNextFloat()` (o try/catch), no solo la de ventas.
- [ ] **El menú no se rompe con una opción fuera de rango** (como "9" o "-1"), en el menú principal y en cada submenú.
- [ ] **Cerrar `Statement`, `ResultSet` y `Connection`** en cada operación de las 3 tablas (no solo en ventas).
- [ ] **Nombres de columnas sin tildes ni caracteres especiales** en las 3 tablas — fue justo lo que nos rompió `ventas` al inicio (`Código_Venta` vs `Codigo_venta`).
- [ ] **Mensajes claros** cuando una búsqueda, actualización o eliminación no encuentra nada.
- [ ] **Compilar las 3 clases juntas** antes de avisar que está listo (no alcanza con que cada uno compile la suya por separado — a veces un nombre de método o de clase no coincide entre archivos).
- [ ] **Las capturas se sacan después de probar el programa ya terminado**, no a medio camino — si se agrega algo después, hay que volver a sacarlas.
- [ ] **Revisar la rúbrica real del Lab 10 una última vez** antes de entregar (la guía dice que no es obligatorio usar clases por tabla, interfaces ni herencia — si alguien agregó algo de eso "porque se veía mejor", quitarlo para no salirse de lo pedido).
