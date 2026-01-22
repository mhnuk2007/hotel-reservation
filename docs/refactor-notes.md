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

### JPA + Hibernate (After)

* No SQL for basic CRUD
* Object-based persistence
* Automatic mapping
* Declarative transaction handling

---

## Project Structure (Step 1)

```
src/main/java
 └── com.hotelreservation
     ├── entity
     │   └── Reservation.java
     ├── dao
     │   └── ReservationJpaDao.java
     └── util
         └── JpaUtil.java

src/main/resources
 └── META-INF
     └── persistence.xml
```

---

## Refactor Strategy Followed

1. **Preserve JDBC code initially** (baseline reference)
2. Introduce JPA dependencies via Maven
3. Create entity classes mapped to existing tables
4. Add `persistence.xml`
5. Replace one DAO at a time
6. Verify functionality after each replacement

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
