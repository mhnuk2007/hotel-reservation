# Refactoring Journey: From JDBC to Hibernate Native

This document outlines the three major refactoring stages of the Hotel Reservation System project, from a basic JDBC implementation to a more robust solution using Hibernate's Native API.

---

# Step 1 – Initial JDBC-Based Implementation

## Purpose of This Step

The initial version of the **Hotel Reservation System** was built using a **raw JDBC** approach to handle database interactions. This provided a foundational understanding of database connectivity in Java but came with boilerplate code and a lack of type safety.

---

## Branch Information

* **Branch name:** `main`

---

## Technology Stack (Step 1)

| Layer                 | Technology                    |
| --------------------- | ----------------------------- |
| Language              | Java 17                       |
| Database Connectivity | JDBC (Java Database Connectivity) |
| Configuration         | `db.properties`               |
| Database              | MySQL                         |
| Dependency Management | Maven                         |

---

## Project Structure (Step 1)

```
src
├── main
│   ├── java
│   │   ├── com
│   │   │   └── hotelreservation
│   │   │       ├── dao
│   │   │       │   ├── ReservationDAO.java
│   │   │       │   └── ReservationDAOImpl.java
│   │   │       ├── model
│   │   │       │   └── Reservation.java
│   │   │       ├── service
│   │   │       │   ├── ReservationService.java
│   │   │       │   └── ReservationServiceImpl.java
│   │   │       └── util
│   │   │           └── DBConnection.java
│   │   └── HotelReservation.java
│   └── resources
│       └── db.properties
└── pom.xml
```

---

# Step 2 – Refactor from JDBC to JPA + Hibernate

## Purpose of This Step

The goal of **Step 2** is to refactor the existing **JDBC-based system** to use **JPA with Hibernate as the provider**. This step focuses on reducing boilerplate code and introducing ORM concepts without the complexity of the Spring Framework.

---

## Branch Information

* **Branch name:** `jpa-hibernate-core`
* **Base branch:** `main`

---

## Technology Stack (Step 2)

| Layer                 | Technology                    |
| --------------------- | ----------------------------- |
| Language              | Java 17                       |
| ORM                   | Hibernate 6.x                 |
| Persistence API       | Jakarta Persistence (JPA 3.x) |
| Database              | MySQL                         |
| Configuration         | `persistence.xml`             |
| Dependency Management | Maven                         |

---

## Structural Changes from JDBC

*   **Configuration:** `db.properties` was replaced by `META-INF/persistence.xml`.
*   **Utility:** `DBConnection.java` was removed in favor of JPA's `Persistence` class.
*   **DAO Layer:** `ReservationDAOImpl` was refactored to use `EntityManager` instead of `PreparedStatement`.
*   **Model:** The `Reservation` class was annotated with `@Entity` to become a JPA entity.

---

# Step 3 – Refactor to Hibernate Native API

## Purpose of This Step

The goal of **Step 3** is to refactor the project from JPA to use **Hibernate's Native API** (`Session`, `SessionFactory`, `Transaction`). This allows us to leverage Hibernate-specific features and have more fine-grained control over the ORM layer.

---

## Branch Information

* **Branch name:** `hibernate-native`
* **Base branch:** `jpa-hibernate-core`

---

## Technology Stack (Step 3)

| Layer                 | Technology                    |
| --------------------- | ----------------------------- |
| Language              | Java 17                       |
| ORM                   | Hibernate 6.x (Native API)    |
| Configuration         | `hibernate.cfg.xml`           |
| Database              | MySQL                         |
| Dependency Management | Maven                         |

---

## Structural Changes from JPA

*   **Configuration:** `persistence.xml` was replaced by `hibernate.cfg.xml`.
*   **Utility:** `HibernateUtil` was introduced to manage the `SessionFactory`.
*   **DAO Layer:** `ReservationDAOImpl` was refactored to use Hibernate's `Session` and `Transaction` instead of `EntityManager`.

## Project Structure (Step 3 - Current)

```
src/main/java/
├── com/hotelreservation/
│   ├── dao/
│   │   ├── ReservationDAO.java
│   │   └── ReservationDAOImpl.java
│   ├── model/
│   │   └── Reservation.java
│   ├── service/
│   │   ├── ReservationService.java
│   │   └── ReservationServiceImpl.java
│   └── util/
│       └── HibernateUtil.java
└── HotelReservation.java
src/main/resources/
└── hibernate.cfg.xml
```

---

## Next Steps

➡ **Step 4 – Spring Core**

*   Integrate the Spring Framework to manage dependencies and transactions declaratively.
*   Externalize configuration into Spring's context.
*   Simplify the DAO and service layers with Spring's support.