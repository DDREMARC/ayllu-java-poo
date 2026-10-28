# Laboratorio 11 — PreparedStatement + MVC — Pendientes

Equipo: Angel · Dan · Leo
Proyecto: sistema de venta de pollos — base de datos `ayllu_pollos` (mismas 3 tablas del Lab 10: `ventas`, `personas`, `gasto_fijo`)

## Lo nuevo de esta semana respecto al Lab 10

- Antes (Lab 10): solo `Statement`, variables sueltas, sin clases por tabla.
- Ahora (Lab 11): **obligatorio** organizar en MVC — 3 clases modelo, 3 clases controladoras, 1 clase vista — y usar **PreparedStatement** en toda operación con parámetros (registrar, buscar por id, actualizar, eliminar). `Statement` solo en consultas sin parámetros (listar todo).

## Checklist de archivos (ya creados vacíos, falta completar la lógica)

- [ ] `src/VentaModel.java`
- [ ] `src/VentaController.java`
- [ ] `src/PersonaModel.java`
- [ ] `src/PersonaController.java`
- [ ] `src/GastoFijoModel.java`
- [ ] `src/GastoFijoController.java`
- [ ] `MainView.java`
- [ ] `Main.java`
- [ ] `script_lab11.sql`

## Checklist antes de entregar (errores que ya pasaron en labs anteriores — no repetir)

- [ ] CRUD **completo** en las 3 tablas (Lab 9: bajaron nota por CRUD incompleto).
- [ ] Todo número leído por Scanner protegido con `hasNextInt()`/`hasNextDouble()`.
- [ ] El menú no se rompe con una opción fuera de rango.
- [ ] Cerrar `PreparedStatement`/`Statement`, `ResultSet` y `Connection` en cada operación.
- [ ] Revisar que cada `?` tenga su `set` correspondiente, en el orden correcto, antes de ejecutar.
- [ ] No concatenar datos del usuario en el SQL donde corresponde usar PreparedStatement.
- [ ] Un único archivo de texto con el código de todas las clases para la entrega final.
- [ ] Evidencias (capturas) de cada operación en las 3 tablas, incluyendo específicamente el uso de PreparedStatement.
