# DemoSupermercado

Una API REST en **Spring Boot** que simula un sistema de supermercado con sucursales, productos y ventas, más estadísticas sobre los más vendidos. Ideal para práctica con JPA, MySQL, DTOs, excepciones personalizadas y Java Streams.


## 📚 Resumen del proyecto

DemoSupermercado es un backend ligero para gestionar:

- **Sucursales** (Branch)
- **Productos**
- **Ventas** (Sale) con sus detalles (SaleDetail)
- Estadísticas para saber:
    - cuál es el producto más vendido (por cantidad)
    - cuál es la sucursal con más ventas (por monto total)

Está construido con Spring Boot + JPA + MySQL, usando DTOs, mapper, validaciones y manejo de errores centralizado.


## 🛠 Tecnologías usadas

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Lombok
- Maven o Gradle
- Java Streams y lambdas


## 🚀 Funcionalidades / Endpoints

Aquí los principales endpoints de la API.

| Módulo | Endpoint | Método | Descripción |
|---|---|---|---|
| **Sucursal** | `/api/branches` | GET | Obtener todas las sucursales |
| | `/api/branches` | POST | Crear una nueva sucursal |
| | `/api/branches/{id}` | PUT | Actualizar sucursal existente |
| | `/api/branches/{id}` | DELETE | Eliminar sucursal |
| **Producto** | `/api/products` | GET | Obtener todos los productos |
| | `/api/products` | POST | Crear un producto nuevo |
| | `/api/products/{id}` | PUT | Actualizar un producto |
| | `/api/products/{id}` | DELETE | Eliminar un producto |
| **Venta** | `/api/sales` | GET | Obtener todas las ventas |
| | `/api/sales` | POST | Crear una venta |
| | `/api/sales/{id}` | PUT | Actualizar una venta existente |
| | `/api/sales/{id}` | DELETE | Eliminar una venta |
| **Estadística** | `/api/stats/product/top` | GET | Obtener el producto más vendido |
| | `/api/stats/branch/top` | GET | Obtener la sucursal con más ventas |


## 📦 Tipos de archivos / Estructura del proyecto

Algunos de los paquetes y tipos de archivos que estás usando:

- **`Model`** — Entidades JPA: `Branch`, `Product`, `Sale`, `SaleDetail`
- **`DTO`** — Clases DTO para request/responses (`ProductDTO`, `BranchDTO`, `SaleDTO`, `SaleDetailDTO`, `ProductStatsDTO`, `BranchStatsDTO`)
- **`Mapper`** — Clase `Mapper` que convierte entre entidad y DTO
- **`Repository`** — Repositorios Spring Data JPA (`ProductRepository`, `SaleRepository`, `BranchRepository`)
- **`Service`** — Lógica de negocio, validaciones, cálculo de estadísticas, manejo de excepciones
- **`Controller`** — Controladores REST que exponen los endpoints
- **`Exception`** — Excepciones personalizadas (`NotFoundException`, `ParamsException`, `NoDataException`) + un `@ControllerAdvice` para manejarlas globalmente

Además, puedes incluir un archivo de **Postman** (exportado como JSON) para pruebas.


## 🔧 Cómo levantar el proyecto (instrucciones)

1. Clona el repositorio:
   ```bash
   git clone https://github.com/YosephGX/DemoSupermercado.git
   cd DemoSupermercado
3. Asegúrate de tener instalado:
    - Java JDK (por ejemplo, versión 17+)
    - MySQL corriendo (o usa tu base de datos preferida. Actualmente H2)
    - IDE (IntelliJ)
3. Configura tu entorno:
    - Crea una base de datos (por ejemplo: `demo_supermercado`)
    - Abrir proyecto en el IDE
    - Ajusta el `application.properties` con tu URL, usuario y contraseña de MySQL
    - Descarga las dependencias del `pom.xml`
      ```data
      Click Derecho (pom.xml) -> Maven -> Download Sources
4. Importa el archivo Postman para probar los endpoints.
   ```data
   DemoSuperMercado.postman_collection.json
   ```


## ✅ Manejo de errores

- Se usan excepciones personalizadas: `NotFoundException`, `ParamsException` y `NoDataException`.
- Se usa `GlobalExceptionHandler` con `@ControllerAdvice` para capturar las excepciones y devolver JSON.
- El DTO Devuelto es:
  ```data
  Int status
  String error
  String message
  String path
  ```
- Si no hay datos para las peticiones lanza `NoDataException` con codigo `200(OK)` y un mensaje para que el cliente entienda que no hay registros que mostrar.