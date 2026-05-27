# empresa-x-security

Proyecto correspondiente a la **semana 1** de la asignatura Backend 2. La actividad presenta una implementación académica de **Spring Security** sobre una aplicación construida con **Spring Boot**, incorporando rutas públicas, rutas protegidas y control de acceso por roles.

## Descripción de la actividad

El proyecto simula un backend de Empresa X con distintos niveles de acceso. Su objetivo es demostrar cómo Spring Security permite proteger endpoints HTTP mediante una configuración centralizada, autenticación básica y autorización diferenciada para usuarios comunes y administradores.

## Qué mejora respecto a un demo básico

Esta versión evita quedarse en el típico ejemplo mínimo donde todo lo privado se protege igual. En cambio, incorpora:

- una ruta pública accesible sin autenticación
- una ruta protegida para usuarios autenticados
- una ruta restringida exclusivamente para administradores
- usuarios en memoria con roles definidos
- pruebas automatizadas para validar las reglas de seguridad

## Contenido teórico trabajado

### 1. Spring Boot como base del backend
Spring Boot simplifica la configuración inicial del proyecto, el arranque del servidor embebido y la integración de dependencias necesarias para exponer endpoints web.

### 2. Seguridad con Spring Security
La dependencia `spring-boot-starter-security` permite interceptar solicitudes HTTP y aplicar políticas de autenticación y autorización.

### 3. SecurityFilterChain
La clase `SecurityConfig` define un bean `SecurityFilterChain` para expresar reglas de acceso de forma declarativa:

- `/app/publico` → acceso libre
- `/app/usuario/**` → requiere rol `USER` o `ADMIN`
- `/app/admin/**` → requiere rol `ADMIN`

Esto permite modelar seguridad con mayor intención y no solo con la regla genérica de “todo autenticado”.

### 4. Usuarios en memoria y roles
La configuración registra dos usuarios en memoria:

- `usuario_demo / user123` con rol `USER`
- `admin_demo / admin123` con rol `ADMIN`

Con esto se puede probar autorización por roles sin integrar base de datos.

### 5. Autenticación por formulario y HTTP Basic
La aplicación mantiene dos mecanismos simples para pruebas:

- `formLogin()` para login por formulario
- `httpBasic()` para pruebas rápidas desde navegador, Postman o tests automáticos

### 6. Endpoints REST con acceso diferenciado
El controlador define tres rutas representativas:

- endpoint público
- endpoint para usuario autenticado
- endpoint exclusivo para administrador

Esto permite observar de forma práctica la diferencia entre autenticación y autorización.

### 7. Testing de seguridad
El proyecto incluye pruebas de integración HTTP y validaciones automáticas para comprobar que:

- la ruta pública responde sin login
- una ruta protegida rechaza usuarios no autenticados
- un usuario con rol insuficiente recibe `403 Forbidden`
- un administrador sí puede acceder a su panel

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
    │   │   ├── config/
    │   │   └── controller/
    │   └── resources/
    └── test/
```

## Credenciales de prueba

- Usuario estándar:
  - usuario: `usuario_demo`
  - contraseña: `user123`

- Administrador:
  - usuario: `admin_demo`
  - contraseña: `admin123`

## Conceptos clave reforzados

- configuración de seguridad en Spring
- autorización por roles
- autenticación básica
- uso de controladores REST
- separación de responsabilidades entre configuración y endpoints
- validación automática de reglas de acceso

## Observación técnica

El repositorio excluye archivos de compilación y configuraciones locales del IDE para mantener una base limpia, portable y fácil de versionar.
