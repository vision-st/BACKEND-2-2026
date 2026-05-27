# empresa-x-security

Proyecto correspondiente a la **semana 1** de la asignatura Backend 2. La actividad introduce una configuración básica de **Spring Security** aplicada sobre una aplicación web construida con **Spring Boot**.

## Descripción de la actividad

El proyecto simula una aplicación de Empresa X con endpoints públicos y protegidos. La idea central es comprender cómo Spring Security intercepta solicitudes HTTP, restringe acceso a ciertas rutas y habilita mecanismos simples de autenticación.

## Contenido teórico trabajado

### 1. Spring Boot como base del proyecto
La aplicación utiliza Spring Boot para simplificar la configuración inicial del backend, el arranque del servidor embebido y la integración entre dependencias.

### 2. Introducción a Spring Security
Se incorpora la dependencia `spring-boot-starter-security`, lo que permite proteger rutas HTTP mediante una configuración centralizada.

### 3. SecurityFilterChain
La clase `SecurityConfig` define un bean `SecurityFilterChain`, donde se establece la política de acceso:
- `/app/index_normal` queda público
- cualquier otra ruta requiere autenticación

Este enfoque permite controlar seguridad de manera declarativa y explícita.

### 4. Autenticación por formulario y HTTP Basic
La configuración habilita dos mecanismos de autenticación:
- `formLogin()` para acceder mediante formulario web por defecto
- `httpBasic()` para pruebas rápidas desde navegador, herramientas HTTP o Postman

### 5. Endpoints públicos y protegidos
En el controlador se distinguen rutas con distintos niveles de acceso:
- endpoint público
- endpoint protegido
- endpoint administrativo protegido

Esto ayuda a comprender la diferencia entre autorización abierta y acceso restringido.

### 6. Usuario en memoria mediante propiedades
El archivo `application.properties` define un usuario simple para pruebas:
- usuario: `admin`
- contraseña: `admin123`

Con esto se puede validar el flujo de autenticación sin integrar base de datos.

## Estructura del proyecto

```text
empresa-x-security/
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .mvn/
└── src/
    ├── main/
    │   ├── java/com/duoc/empresa_x_security/
    │   └── resources/
    └── test/
```

## Conceptos clave reforzados

- configuración de seguridad en Spring
- autorización de rutas
- autenticación básica
- uso de controladores REST
- separación entre configuración y lógica de endpoints

## Observación técnica

El repositorio excluye archivos de compilación y configuraciones locales del IDE para mantener una base limpia, portable y fácil de versionar.
