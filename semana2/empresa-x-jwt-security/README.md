# empresa-x-jwt-security

Proyecto demostrativo para la **semana 2** de Backend 2.

## Qué demuestra en clase

- diferencia entre autenticación y autorización
- modelo stateless con JWT
- login que emite token Bearer
- rutas públicas y protegidas
- autorización por rol `ADMIN`
- validación de token en cada solicitud

## Endpoints principales

- `POST /api/auth/login`
- `GET /api/public/info`
- `GET /api/usuario/perfil`
- `GET /api/admin/reporte`

## Credenciales de prueba

- `usuario_demo / user123`
- `admin_demo / admin123`

## Flujo sugerido para la clase

1. Probar la ruta pública.
2. Intentar entrar a una ruta protegida sin token.
3. Hacer login con usuario y obtener JWT.
4. Repetir la solicitud protegida usando `Authorization: Bearer <token>`.
5. Mostrar que `usuario_demo` no entra al endpoint admin.
6. Mostrar que `admin_demo` sí entra al endpoint admin.

## Ejemplo de login

```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "usuario_demo",
  "password": "user123"
}
```

## Objetivo pedagógico

Mostrar cómo Spring Security cambia desde un enfoque stateful con formulario a un enfoque stateless con JWT, manteniendo el proyecto pequeño y explicable.
