# Match Application

A functional web service for creating, updating, and querying sports matches and their betting odds.

---

## Table of Contents


- [Requirements](#requirements)
- [Technology Stack](#technology-stack)
- [Architecture & Design](#architecture--design)
    - [Entities](#entities)
    - [DTOs](#dtos)
    - [Mapping (MapStruct)](#mapping-mapstruct)
    - [Repository Layer](#repository-layer)
    - [Service Layer](#service-layer)
    - [Controller Layer](#controller-layer)
    - [Exception Handling](#exception-handling)
    - [Validation](#validation)
- [How to Run](#how-to-run)
    - [Running with Docker Compose](#running-with-docker-compose)
    - [Run Locally (with local postgresql)](#run-locally-with-local-postgresql)
- [API Documentation](#api-documentation)
- [Key Design Decisions](#key-design-decisions)


---

## Requirements

- Expose a functional web service with CRUD endpoints for:
  - **Match** (`/matches`)
  - **MatchOdds** (`/match-odds`)
- Use **Spring Boot** (Java & Maven)
- Use **PostgreSQL** for data storage
- Use **JPA/Hibernate** for data access
- Sports type (`sport`) must be an Enum (`1` = Football, `2` = Basketball)


---

## Technology Stack

- **Java 17+**
- **Spring Boot 3.4.7**
- **Spring Data JPA**
- **PostgreSQL 15**
- **MapStruct** (DTO <-> Entity mapping)
- **Flyway** (Database migrations)
- **Docker & Docker Compose**
- **Springdoc OpenAPI** (`springdoc-openapi-starter-webmvc-ui`)

---

## Architecture & Design

### Entities

The application models two primary entities:

#### Match
Represents a sports event.

- `id`: Unique identifier
- `description`: Match description
- `match_date`: Date of the match
- `match_time`: Time of the match
- `team_a`: First team's name
- `team_b`: Second team's name
- `sport`: Enum value (1: Football, 2: Basketball)

#### MatchOdds
Represents a betting odd for a particular match.

- `id`: Unique identifier
- `match_id`: References a Match entity
- `specifier`: Betting specifier ("1", "X", "2")
- `odd`: The decimal odd value

These entities are persisted in a PostgreSQL database using JPA.

---

### DTOs

Data Transfer Objects (DTOs) are used to define clear and stable API contracts between the server and clients.  
DTOs help:

- Prevent overexposing internal data models
- Enforce validation rules for API inputs (e.g., required fields, value ranges)


All incoming requests and outgoing responses are mapped to and from DTOs, never exposing entities directly.

---

### Mapping (MapStruct)

- MapStruct is used to automate the mapping between entities and DTOs.
- Handles field renaming and differences between entity and DTO structures.
- Supports custom type conversions (e.g., between `BigDecimal` and `String`).
- Eliminates repetitive manual mapping code, keeping the codebase clean.
- All mappers are registered as Spring Beans for easy integration in the service layer.

---

### Repository Layer

- Uses Spring Data JPA repositories for database access.
- Repositories are defined for both `Match` and `MatchOdds`.
- Handles all data retrieval and persistence.

---

### Service Layer

- Implements business logic for `Match` and `MatchOdds`.
- Calls repository methods for data access and persistence.

---

### Controller Layer

- Provide RESTful endpoints for all CRUD operations on `Match` and `MatchOdds`.
- Work with DTOs for both request and response payloads.
- Delegate business logic and data access to the service layer.
- Rely on method-level validation for incoming requests.
- Rely on global exception handling for validation errors and other exceptions.
- Use `@RestController` and mapping annotations to define endpoints.

---

### Exception Handling

- Centralizes error handling using a `@RestControllerAdvice` class.
- Translates common exceptions (such as entity not found, validation errors, malformed requests, and date parsing errors) into consistent API error responses.
- Returns structured error messages with appropriate HTTP status codes.
- Logs all handled exceptions for troubleshooting and monitoring.
- Ensures clients receive meaningful, user-friendly error messages instead of stack traces.

---

### Validation

- Jakarta Bean Validation is used for validating incoming DTOs.
- Checks that required fields are present and correctly formatted.
- Validates string patterns and numeric ranges where applicable.
---

## How to Run

### Running with Docker Compose

1. **Build & Start Everything:**
    ```sh
    docker-compose up --build
    ```
  - Spins up both PostgreSQL and the app.
  - DB data is persisted via named Docker volume.

2. **App available at:**  
   [http://localhost:8080](http://localhost:8080)  
   **Swagger/OpenAPI:**  
   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

### Run Locally (with local PostgreSQL)

1. **Create a local PostgreSQL database:**
  - Database: `matchdb`
  - Username/password: match your `application.properties`

2. **Edit `src/main/resources/application.properties`:**
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/matchdb
    spring.datasource.username=youruser
    spring.datasource.password=yourpass
    ```

3. **Run the app:**
    ```sh
    ./mvnw spring-boot:run
    ```
   or
    ```sh
    mvn spring-boot:run
    ```

---

## API Documentation

- OpenAPI/Swagger UI:  
  [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- All endpoints, models, and error responses are documented.

---

## Key Design Decisions

- **Database migrations:** All schema changes are managed using Flyway with SQL migration scripts.
- **Global error handling:** Exceptions are handled centrally and returned as structured JSON responses.
- **MapStruct mapping:** Mapping between entities and DTOs is fully automated with MapStruct to keep layers decoupled and code clean.
---


