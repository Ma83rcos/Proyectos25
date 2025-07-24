

### Running Docker container



```bash
docker run -d --name parking-container -e POSTGRES_PASSWORD=123456 -e POSTGRES_USER=parking -e POSTGRES_DB=parking -p 5435:5432 postgres:17
```


## ✅ TAREA 1: QA, VALIDACIONES Y DEBUGGING EN VEHICLE SERVICE

# 🚗 Tarea – QA, Validaciones y Debugging en VehicleService

## 🎯 Objetivo
- Identificar errores comunes al registrar vehículos.
- Aplicar buenas prácticas de validación.
- Usar SLF4J para registrar eventos del sistema.
- Usar el debugger para analizar errores y flujos.

---

## 📌 Parte 1: Agregar SLF4J

1. Agrega la anotación `@Slf4j` en la clase `VehicleService`.
2. Usa `log.info(...)`, `log.warn(...)`, `log.error(...)` según el caso.

---

## 🧪 Parte 2: Validaciones y Casos de Error

### ✅ Caso 1 – Campos vacíos o nulos
Ya esta hecho
- Validar que `brand` y `plate` no estén vacíos.
- Si alguno está vacío o nulo, retorna un `MensageResponseDto` con código 400.
- Usa `log.error(...)` para dejar registro.

```java
if (vehicle.getBrand() == null || vehicle.getBrand().isEmpty() ||
    vehicle.getPlate() == null || vehicle.getPlate().isEmpty()) {
    log.error("Intento de registro con campos vacíos");
    return new MensageResponseDto("Campos vacíos", 400, "servicio/vehiculo", LocalDateTime.now(), null);
}
````

---

### ✅ Caso 2 – Matricula duplicada

* Antes de guardar, validar que `plate` no esté repetido en la base de datos.
* Usa el método `existsByPlate(String plate)` en el repositorio.
* Si ya existe, retorna error y usa `log.warn(...)`.

---

### ✅ Caso 3 – Límite de caracteres (máx. 50)

* Validar que `plate.length() <= 50`.
* Si se excede, retornar DTO con error y registrar con `log.warn(...)`.

---

## 🧠 Parte 3: Debugging

* Coloca breakpoints en `createVehicle(...)` y `updateVehicle(...)`.
* Observa los valores de entrada y salida.
* Verifica si las validaciones están funcionando como esperas.

---

## ✅ TAREA 2: QA, VALIDACIONES Y DEBUGGING EN PARKINGSPOT SERVICE


# 🅿️ Tarea – QA, Validaciones y Debugging en ParkingSpotService

## 🎯 Objetivo
- Detectar errores comunes en plazas de estacionamiento.
- Agregar validaciones necesarias.
- Usar logs SLF4J para monitoreo.
- Aplicar debugging para verificar la lógica.

## 📌 Parte 1: Agregar SLF4J

1. Agrega la anotación `@Slf4j` en la clase `ParkingSpotService`.
2. Usa `log.info(...)`, `log.warn(...)`, `log.error(...)` donde sea necesario.

## 🧪 Parte 2: Validaciones y Casos de Error

### ✅ Caso 1 – Código vacío o nulo

- Validar que `spotDto.getCode()` no sea nulo ni vacío antes de guardar.
- Si lo es, retornar mensaje de error y registrar con `log.error(...)`.


### ✅ Caso 2 – Código duplicado

- Validar que no exista otra plaza con el mismo `code`.
- Implementar `existsByCode(String code)` en `ParkingSpotRepository`.

```java
if (parkingSpotRepository.existsByCode(spotDto.getCode())) {
    log.warn("Código duplicado: " + spotDto.getCode());
    return new MensageResponseDto("Código ya registrado", 409, "servicio/plaza", LocalDateTime.now(), null);
}
````

### ✅ Caso 3 – Límite de caracteres

* Validar que `code.length() <= 30`.
* Si no se cumple, retornar DTO de error.
* Usa `log.warn(...)` para registrar.

---

### ✅ Caso 4 – `updateParkingSpot`: Retorno incorrecto

* Si la plaza no existe, actualmente se retorna código 200.
* Cambiar a 404 + log de advertencia.

---

### ✅ Caso 5 – `markSpotAsUnavailable`: Valor confuso

* Actualmente se usa `available = 3`.
* Definir claramente qué significa ese número.
* Usar constantes o valores booleanos/documentados.

---

## 🧠 Parte 3: Debugging

* Coloca breakpoints en:

    * `createParkingSpot(...)`
    * `updateParkingSpot(...)`
    * `markSpotAsUnavailable(...)`
* Inspecciona:

    * DTOs entrantes.
    * Estado de la entidad.
    * Flujo de la lógica y retorno.

---

## 📝 Entregable

* Código validado y corregido.
* Capturas del debugger con valores de prueba(whatsapp o solamente me dices que ya probaste el debugger y lo exploraste)
* Logs en consola que evidencien validaciones y errores controlados.

