# App autorizacion y autenticacion mediante 0AUTH

Esta es una aplicación REST API, creada para el manejo de sesiones
mediante JWT utilizando la libreria 0Auth de Spring.

## Descripción

Mediante el endpoint /api/auth, tendrá acceso a las rutas
- `/register`: Registro de nuevo usuario con rol client.
- `/login`: Login del usuario(genera y envia token)
- `/index`: Mensaje usuario con rol admin.

El motor de base de datos utilizado es MySQL, con un
nombre de esquema "auth" y con credenciales:

- Usuario: root
- Password: admin

## Uso

A traves del enlace: [auth-spring-boot-production.up.railway.app]()
podra realizar pruebas.