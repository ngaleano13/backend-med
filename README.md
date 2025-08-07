# MedAPI - Backend (Spring Boot)

## 🌐 Descripción General

Este proyecto es una **API REST para un centro médico**, desarrollada con **Spring Boot**. Tiene como objetivo gestionar:

* **Sucursales** del centro médico (nombre, dirección, teléfono, latitud y longitud)
* **Usuarios** con roles diferenciados (**USER** y **ADMIN**)
* Futuro soporte para **gestión de turnos médicos**

El sistema está diseñado para integrarse con un **frontend en React**, que probablemente utilizará Google Maps para geolocalizar sucursales.

---

## 📂 Estructura Actual

### Sucursales (`/api/sucursales`)

* **GET /** - Listar todas las sucursales *(disponible para cualquier usuario)*
* **GET /{id}** - Buscar sucursal por ID *(público)*
* **GET /buscar?nombre=???** - Buscar sucursales por nombre *(público)*
* **POST /** - Crear sucursal *(solo ADMIN)*
* **PUT /{id}** - Actualizar sucursal *(solo ADMIN)*
* **DELETE /{id}** - Eliminar sucursal *(solo ADMIN)*

### Usuarios (`/api/usuario`)

* **POST /registro** - Registrar un nuevo usuario *(rol USER por defecto)*
* **GET /perfil** - Ver perfil propio *(requiere login)*
* **PUT /{id}** - Actualizar perfil propio *(requiere login)*

### Admin Usuarios (`/api/admin/usuarios`)

* **GET /** - Listar todos los usuarios *(solo ADMIN)*
* **GET /{id}** - Ver usuario por ID *(solo ADMIN)*
* **PUT /{id}** - Actualizar el rol del Usuario *(solo ADMIN)*
* **DELETE /{id}** - Eliminar usuario *(solo ADMIN)*

---

## 🌐 Seguridad y Roles

* La API implementa **autenticación HTTP Basic** (en desarrollo, se puede migrar a JWT en el futuro si es necesario).
* Los roles definidos:

  * `USER`: puede registrarse, ver y editar su perfil.
  * `ADMIN`: además puede gestionar usuarios y sucursales.
* Endpoints protegidos con `@PreAuthorize`.

---

## ⚡ Tecnologías Usadas

* **Java 17**
* **Spring Boot** (Web, Security, Data JPA)
* **Lombok**
* **PostgreSQL (Docker Image)**
* **TablePlus** (para exploración de base de datos)
* **Manejo de excepciones global:** `@RestControllerAdvice`

---

## 🗓️ Pendiente / Futuro

* Implementar **gestión de turnos**:

  * CRUD de turnos por usuario
  * Asociación con sucursal y especialidad
* Evaluar si se integrará **JWT** para autenticación
* Evaluar documentación automática (Swagger/OpenAPI)
* Pruebas unitarias y de integración

---

## 🚀 Ejecución Local

1. Clonar el repositorio
2. Configurar la base de datos en `application.properties`
3. Ejecutar la aplicación desde tu IDE o con:

```bash
./mvnw spring-boot:run
```

4. Acceder a:

   * `http://localhost:8080/api/sucursales`
   * `http://localhost:8080/api/usuario`

---

## 📅 Estado: EN DESARROLLO
