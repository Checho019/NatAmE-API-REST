# NatAmE API REST
Api REST presentada para la gestión de una tienda en linea

### Datos tecnicos
- Lenguaje: JAVA
- Versión: 21
- Gestor de dependencias: Gradle
- Se usa el JDK y el JRE proporcionados por Adoptium
- Desplegado en el puerto 8080
- Base de datos Oracle

### Autenticación
La autenticación de la api se hace mediante dos headers:
- user: Es el nombre de usuario nombrado en la base de datos
- password: Contraseña del usuario nombrado en la base de datos

### Endpoints
Los endpoints que provee la API son:
| Endpoint | Petición HTTP | Descripción |
|----------|---------------|-------------|
|cliente/obtener/{id} | GET | Consulta un cliente según su correo.|
|cliente/agregar | POST | Agrega un cliente nuevo.|
|producto/obtener/{codigo} | GET | Consulta un producto según su código.|
|producto/ | GET | Consulta todos los productos.|
|representante/obtener/{id} | GET | Obtiene un representante de ventas según su id.|
|representante/agregar | POST | Agrega un representante de ventas nuevo.|
|representante/obtenerVistas/{correo} | GET | Obtener vista simplificada de representante.|

Las peticiones son enviadas en formato JSON y sus atributos son acordes a la entidad que representan en el modelo relacional.
<br>
Nota: Mejorar el sisstema de conexiones a la base de datos, puesto que está pensado para ser un sistema recurrente.


