# Step 1 – Refactor from JDBC to JPA + Hibernate (Core Java)

## Purpose of This Step

The goal of **Step 1** is to refactor the existing **JDBC-based Hotel Reservation system** to use **JPA with Hibernate as the provider**, while still running as a **Core Java (non-Spring)** application.

This step focuses on:

* Understanding JPA concepts clearly
* Reducing boilerplate JDBC code
* Introducing ORM without adding Spring complexity

This branch acts as a **learning and transition layer** before moving to native Hibernate APIs and then Spring.

---

## Branch Information

* **Branch name:** `jpa-hibernate-core`
* **Base branch:** `main` (JDBC reference implementation)

---

## Technology Stack (Step 1)

| Layer                 | Technology                    |
| --------------------- | ----------------------------- |
| Language              | Java 17                       |
| ORM                   | Hibernate 6.x                 |
| Persistence API       | Jakarta Persistence (JPA 3.x) |
| Database              | MySQL                         |
| Configuration         | `persistence.xml`             |
| Dependency Management | Maven                         |

---

## Key Concepts Introduced

### 1. JPA (Jakarta Persistence API)

JPA is a **specification**, not an implementation. It defines:

* How entities are mapped
* How persistence context works
* How CRUD operations should behave

Hibernate acts as the **JPA implementation** in this project.

---

### 2. Entity

An **Entity** represents a database table as a Java class.

Characteristics:

* Annotated with `@Entity`
* Must have a primary key annotated with `@Id`
* Managed by JPA, not manually instantiated for persistence

Example responsibilities:

* Represent table structure
* Hold business data only
* No JDBC or SQL code

---

### 3. Persistence Unit

Defined inside `persistence.xml`.

Responsibilities:

* Database connection details
* Hibernate configuration
* Entity class registration

This replaces:

* JDBC connection URL handling
* Driver loading
* Manual connection lifecycle

---

### 4. Persistence

`jakarta.persistence.Persistence` is a **bootstrap class**.

Role:

* Reads `persistence.xml`
* Creates `EntityManagerFactory`

Used only **once** at application startup.

---

### 5. EntityManagerFactory

* Heavyweight object
* Thread-safe
* Created once per application

Responsibilities:

* Factory for `EntityManager`
* Holds database and ORM configuration

Lifecycle:

* Created at startup
* Closed at application shutdown

---

### 6. EntityManager

* Lightweight
* NOT thread-safe
* Represents a single persistence context

Responsibilities:

* Perform CRUD operations
* Manage entity state (managed, detached, removed)
* Handle transactions

Lifecycle:

* Create per operation / request
* Close immediately after use

---

### 7. Persistence Context

A **Persistence Context** is:

* A first-level cache
* Associated with one `EntityManager`

Key behaviors:

* Ensures entity identity
* Tracks changes automatically
* Synchronizes with DB at commit time

---

## Structural Changes from JDBC

### JDBC (Before)

* Manual SQL queries
* `PreparedStatement`
* Explicit result set mapping
* Manual transaction handling
* **Packages:** `model`, `dao`, `service`, `util`

### JPA + Hibernate (After)

* No SQL for basic CRUD
* Object-based persistence
* Automatic mapping
* Declarative transaction handling
* **Packages:** `com.hotelreservation.app.model`, `com.hotelreservation.app.dao`, `com.hotelreservation.app.service`

---

## Project Structure (Step 1)

```
src/main/java
 └── com.hotelreservation.app
     ├── model
     │   └── Reservation.java       <-- Converted to JPA Entity
     ├── dao
     │   ├── ReservationDao.java    <-- Interface
     │   └── ReservationDaoImpl.java <-- Refactored to use EntityManager
     └── service
         ├── ReservationService.java
         └── ReservationServiceImpl.java

src/main/resources
 └── META-INF
     └── persistence.xml        <-- New Configuration File
```

---

## Refactor Strategy Followed

1. **Update Project Structure**: Adopted a package structure under `com.hotelreservation.app`.
2. **Introduce JPA Dependencies**: Ensure Maven `pom.xml` has `hibernate-core` and `jakarta.persistence-api`.
3. **Convert Model to Entity**: Annotate `com.hotelreservation.app.model.Reservation` with `@Entity`, `@Table`, `@Id`, etc.
4. **Configure Persistence**: Create `src/main/resources/META-INF/persistence.xml` with database and Hibernate settings.
5. **Refactor DAO**: Update `com.hotelreservation.app.dao.ReservationDaoImpl` to initialize `EntityManagerFactory` and use `EntityManager` for CRUD operations.
6. **Verify Service Layer**: Ensure `com.hotelreservation.app.service.ReservationServiceImpl` works seamlessly with the new DAO implementation.

This ensures:

* Minimal risk
* Easier debugging
* Clear learning progression

---

## What This Step Does NOT Include

❌ Spring Framework
❌ Dependency Injection
❌ Spring Transactions
❌ Native Hibernate `Session` API

These are intentionally deferred to later steps.

---

## Benefits Achieved in Step 1

* Cleaner data access code
* Strong separation of concerns
* ORM fundamentals understood
* Foundation ready for Hibernate-native and Spring refactor

---

## Next Steps

➡ **Step 2 – Hibernate Native**

* Replace `EntityManager` with `Session`
* Use `SessionFactory`
* Explore Hibernate-specific features

➡ **Step 3 – Spring Core**

* Integrate Spring Context
* Externalize configuration
* Enable declarative transactions

---

## Notes

This step is intentionally verbose and explicit to build **strong ORM fundamentals**, which are critical before introducing Spring abstractions.
