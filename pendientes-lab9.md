# Lab 9: Qué falta — resumen para el equipo

Hicimos push de:
- ✅ **ConsolaMain.java** corregido (case 1 arreglado, case 3 eliminar cliente)
- ✅ **GestorNegocio.java** (cliente registrar, mostrar, buscar, eliminar)

---

## 🟦 Angel — LISTO

Persona, Cliente, GestorNegocio de clientes. 
**Próximo paso:** Esperar que Dan y Leo terminen sus partes y juntos armar los reportes.

---

## 🟩 Dan — PENDIENTE

**Archivo:** Empleado.java + Movimiento.java (abstracta)

**En el gestor:** Agregar métodos
- `registrarEmpleado(...)`
- `mostrarEmpleados()`

**En el menú:** Llenar case 4 (registrar empleado)

**Entregables:** 
- Diagrama UML de clases (todo el proyecto)

---

## 🟧 Leo — PENDIENTE

**Archivos:** Venta.java y GastoFijo.java

**Simplificar:**
- Quitar `Runnable` y la flecha `->` en setters (solo `if` normal)
- Cambiar `? :` por `if/else` normal
- Cambiar `throw` por `if` con mensaje
- Simplificar Arrays.asList (comparar con `.equals()`)

**En el gestor:** Agregar métodos
- `registrarVenta(...)`
- `mostrarVentas()`
- `registrarGasto(...)`
- `mostrarGastos()`

**En el menú:** Llenar cases 5 y 6

---

## 🟡 LOS 3 JUNTOS — ÚLTIMO PASO

Una vez que terminen lo de arriba:

1. **Case 7 — Reportes**
   - Lista de tipo `IReportable` con empleados, ventas, gastos
   - Llamar `reporteTexto()` de cada uno
   - Sumar los montos

2. **Un archivo .txt** con TODO el código (copiar y pegar de IntelliJ)

3. **README** — explicar en corto qué es el sistema

4. **3 conclusiones** — una de cada uno (qué aprendimos)

5. **Subir todo** a GitHub antes de entregar

---

**Recuerden:** -10 puntos si el profe detecta IA. Cada uno escribe su parte y puede explicarla.

¡Adelante bro! 💪
