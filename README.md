# E-commerce Microservice 🛒

Sistema de e-commerce desarrollado con arquitectura de microservicios utilizando **Spring Boot**, **Spring Cloud** y contenedores **Docker**. Este proyecto implementa un enfoque modular y escalable para gestionar clientes, productos y categorías, con configuración centralizada, descubrimiento de servicios y manejo de excepciones compartido.

## 🏗️ Arquitectura

El sistema está compuesto por los siguientes microservicios:

- **Config Server**: Administración centralizada de configuraciones para todos los servicios.
- **Discovery Server (Eureka)**: Registro y descubrimiento de servicios para comunicación entre microservicios.
- **Customer Service**: Gestión de clientes, utilizando MongoDB como base de datos.
- **Product Microservice**: Gestión de productos y categorías, utilizando PostgreSQL como base de datos.
- **Common Exception**: Librería compartida para manejo uniforme de excepciones y respuestas de error.

### Diagrama de Arquitectura

```
[API Gateway] (futuro)
    |
    +-- [Discovery Server]
        |
        +-- [Config Server]
        |
        +-- [Customer Service] --> MongoDB
        |
        +-- [Product Microservice] --> PostgreSQL
            |
            +-- [Common Exception]
```

## 🚀 Tecnologías

- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Cloud 2025.0.0**
- **Spring Data JPA** (para PostgreSQL)
- **Spring Data MongoDB** (para MongoDB)
- **Netflix Eureka** (descubrimiento de servicios)
- **Spring Cloud Config** (configuración centralizada)
- **Docker & Docker Compose**
- **PostgreSQL** (base de datos relacional)
- **MongoDB** (base de datos NoSQL)
- **Lombok** (para reducir código boilerplate)
- **Maven** (gestión de dependencias)

## 📋 Requisitos Previos

- **Java 21** o superior
- **Maven 3.6+**
- **Docker** y **Docker Compose**
- **Git**

## ⚙️ Instalación y Configuración

### 1. Clonar el Repositorio

```bash
git clone https://github.com/Marco-Villanueva20/ecommerce-microservice.git
cd ecommerce-microservice
```

### 2. Configurar Bases de Datos con Docker

Ejecuta los contenedores de base de datos usando Docker Compose:

```bash
docker-compose up -d
```

Esto iniciará:
- **MongoDB** en `localhost:27017` (usuario: `root`, contraseña: `password`, base de datos: `customer_db`)
- **PostgreSQL** en `localhost:5432` (usuario: `root`, contraseña: `password`, base de datos: `product_db`)

### 3. Construir el Proyecto

Compila todos los módulos con Maven:

```bash
mvn clean install
```

### 4. Ejecutar los Servicios

#### Opción 1: Ejecutar Individualmente

1. **Config Server** (puerto 8888):
   ```bash
   cd config-server
   mvn spring-boot:run
   ```

2. **Discovery Server** (puerto 8761):
   ```bash
   cd discovery-server
   mvn spring-boot:run
   ```

3. **Customer Service** (puerto 8091):
   ```bash
   cd microservices/customer-service
   mvn spring-boot:run
   ```

4. **Product Microservice** (puerto 8092):
   ```bash
   cd microservices/product-microservice
   mvn spring-boot:run
   ```

#### Opción 2: Ejecutar con Maven desde la Raíz

```bash
mvn spring-boot:run -pl config-server &
mvn spring-boot:run -pl discovery-server &
mvn spring-boot:run -pl microservices/customer-service &
mvn spring-boot:run -pl microservices/product-microservice &
```

### 5. Verificar el Estado

- **Eureka Dashboard**: http://localhost:8761
- **Config Server**: http://localhost:8888

## 📚 APIs

### Customer Service (Puerto 8091)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/api/v1/customers` | Crear un nuevo cliente |
| `GET` | `/api/v1/customers` | Obtener todos los clientes |
| `GET` | `/api/v1/customers/{customerId}` | Obtener cliente por ID |
| `PUT` | `/api/v1/customers` | Actualizar cliente |
| `DELETE` | `/api/v1/customers/{customerId}` | Eliminar cliente por ID |

**Ejemplo de Request para Crear Cliente:**
```json
{
  "firstname": "Juan",
  "lastname": "Pérez",
  "email": "juan.perez@example.com",
  "address": {
    "street": "Calle Principal 123",
    "city": "Ciudad",
    "country": "País"
  }
}
```

### Product Microservice (Puerto 8092)

#### Productos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/v1/products` | Obtener todos los productos |
| `GET` | `/api/v1/products/{id}` | Obtener producto por ID |
| `POST` | `/api/v1/products` | Crear un nuevo producto |
| `PUT` | `/api/v1/products` | Actualizar producto |
| `DELETE` | `/api/v1/products/{id}` | Eliminar producto por ID |
| `POST` | `/api/v1/products/purchase` | Realizar compra de productos |
| `POST` | `/api/v1/products/restock` | Reponer stock de productos |

**Ejemplo de Request para Crear Producto:**
```json
{
  "name": "Producto Ejemplo",
  "description": "Descripción del producto",
  "price": 99.99,
  "quantity": 100,
  "categoryId": 1
}
```

#### Categorías

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/v1/categories` | Obtener todas las categorías |
| `GET` | `/api/v1/categories/{id}` | Obtener categoría por ID |
| `POST` | `/api/v1/categories` | Crear una nueva categoría |
| `PUT` | `/api/v1/categories` | Actualizar categoría |
| `DELETE` | `/api/v1/categories/{id}` | Eliminar categoría por ID |

**Ejemplo de Request para Crear Categoría:**
```json
{
  "name": "Electrónicos",
  "description": "Productos electrónicos y gadgets"
}
```

## 🗄️ Bases de Datos

### PostgreSQL (Product Microservice)
- **Host**: localhost:5432
- **Database**: product_db
- **Usuario**: root
- **Contraseña**: password
- **Tablas**: products, categories (generadas automáticamente con Hibernate)

### MongoDB (Customer Service)
- **Host**: localhost:27017
- **Database**: customer_db
- **Usuario**: root
- **Contraseña**: password
- **Colección**: customers

## 🔧 Configuración

La configuración se gestiona centralizadamente a través del Config Server. Los archivos de configuración se encuentran en `config-server/src/main/resources/config/`:

- `customer-service.properties`: Configuración para Customer Service
- `product-microservice.properties`: Configuración para Product Microservice
- `discovery-server.properties`: Configuración para Discovery Server

Cada servicio importa su configuración desde `http://localhost:8888`.

## 📝 Logging

Cada servicio genera logs en archivos separados:
- **Customer Service**: `logs/customer-microservice.log`
- **Product Microservice**: `logs/product-microservice.log`

Los logs incluyen timestamps, niveles y patrones configurables.

## 🧪 Pruebas

Ejecutar pruebas unitarias:

```bash
mvn test
```

## 🐳 Docker

Para desarrollo local, las bases de datos se ejecutan en contenedores Docker. El `docker-compose.yml` incluye:

- **MongoDB**: Puerto 27017
- **PostgreSQL**: Puerto 5432

```bash
docker-compose up -d  # Iniciar
docker-compose down   # Detener
```

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## 📞 Contacto

Marco Villanueva - marco_villanueva_@hotmail.com

Link del Proyecto: [https://github.com/Marco-Villanueva20/ecommerce-microservice.git](https://github.com/Marco-Villanueva20/ecommerce-microservice.git)

---

**Nota**: Este proyecto es una implementación de aprendizaje de arquitectura de microservicios con Spring Cloud. Para producción, considera agregar seguridad (Spring Security, OAuth2), API Gateway (Spring Cloud Gateway), circuit breakers (Resilience4j), y monitoreo (Spring Boot Actuator, Micrometer).
