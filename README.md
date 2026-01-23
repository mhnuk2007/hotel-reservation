# 🏨 Hotel Reservation System (Hibernate Native Version)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-green.svg?style=for-the-badge)

A robust, console-based **Hotel Reservation System** built with **Core Java** and **Hibernate Native API**. This project demonstrates a professional implementation of the **Layered Architecture** pattern, ensuring clean separation of concerns, maintainability, and scalability.

---

## 📖 Table of Contents
- [Overview](#-overview)
- [Architecture](#-architecture)
- [Project Structure](#-project-structure)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Getting Started and Usage](#-getting-started-and-usage)
- [Database Setup](#-database-setup)
- [Future Roadmap](#-future-roadmap)
- [Author](#-author)

---

## 🚀 Overview

This application serves as a practical revision of Core Java and ORM concepts, refactored to mirror enterprise-level development standards. It uses **Hibernate Native API** for data persistence, providing a deep dive into ORM capabilities beyond standard JPA.

**Key Highlights:**
*   **Clean Code:** Adheres to SOLID principles.
*   **Hibernate Native:** Uses `Session` and `SessionFactory` for advanced ORM control.
*   **Scalable Design:** Decoupled layers allow for easy updates and testing.
*   **Secure Configuration:** Centralized database configuration via `hibernate.cfg.xml`.

---

## 🏗 Architecture

The project is structured into three distinct layers:

1.  **Presentation Layer (UI):** Handles user interaction via the console (`HotelReservation.java`).
2.  **Service Layer (Business Logic):** Processes data and applies business rules (`ReservationService`).
3.  **Data Access Layer (DAO):** Manages database interactions using Hibernate `Session` (`ReservationDAO`).

### 🔄 Refactoring Journey: JDBC → Hibernate Native
*   ✅ **DAO Pattern:** Refactored to use Hibernate `Session` instead of JDBC `PreparedStatement`.
*   ✅ **Service Layer:** Remains agnostic of the underlying data access technology.
*   ✅ **Model:** Annotated POJOs (`@Entity`) representing database tables.
*   ✅ **Configuration:** `hibernate.cfg.xml` replaces manual connection logic.
*   ✅ **Bootstrap:** Implemented `HibernateUtil` for `SessionFactory` management.

---

## 📂 Project Structure

```plaintext
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

## ✨ Features

*   **📝 Reserve a Room:** Create new bookings with guest details.
*   **👀 View Reservations:** List all active reservations.
*   **🔍 Find Room Number:** Retrieve room details by reservation ID and guest name.
*   **✏️ Update Reservation:** Modify existing booking details.
*   **❌ Delete Reservation:** Remove a booking from the system.

---

## 🛠 Tech Stack

*   **Language:** Java 17
*   **ORM:** Hibernate 6.x (Native API)
*   **Database:** MySQL
*   **Build Tool:** Maven

---

## 🏁 Getting Started and Usage

### Prerequisites
*   Java Development Kit (JDK) 17 or higher
*   MySQL Server installed and running
*   IDE (IntelliJ IDEA, Eclipse) or Terminal

### Installation

1.  **Clone the Repository and checkout the `hibernate-native` branch**
    ```bash
    git clone https://github.com/mhnuk2007/hotel-reservation.git
    cd hotel-reservation
    git checkout hibernate-native
    ```

2.  **Configure Database**
    Update `src/main/resources/hibernate.cfg.xml` with your database credentials:
    ```xml
    <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/hotel_db</property>
    <property name="hibernate.connection.username">root</property>
    <property name="hibernate.connection.password">your_password</property>
    ```

3.  **Run the Application**
    Compile and execute `HotelReservation.java`.

### Usage

The application is a console-based menu-driven system. When you run the application, you will see the following menu:

```
HOTEL MANAGEMENT SYSTEM
1. Reserve a room
2. View reservations
3. Get room number
4. Get reservation
5. Update reservation
6. Delete reservation
0. Exit
Choose an option:
```

Here is a description of each option:

| Option | Description                                                                 | Inputs                                                                                             | Outputs                                                                                                                                                                                            |
| :----- | :-------------------------------------------------------------------------- | :------------------------------------------------------------------------------------------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 1      | **Reserve a room**                                                          | `Customer name`, `Room number`, `Contact number`                                                   | A confirmation message: `Room reserved successfully!`                                                                                                                                            |
| 2      | **View reservations**                                                       | None                                                                                               | A table with all the reservations: `ID`, `Guest Name`, `Room`, `Contact`, `Reservation Date`. If there are no reservations, it will print `No reservations found.`                              |
| 3      | **Get room number**                                                         | `Reservation ID`, `Customer name`                                                                  | The room number for the given reservation: `Room number: <room_number>`. If the reservation is not found, it will print `Reservation not found.`                                                   |
| 4      | **Get reservation**                                                         | `Reservation ID`, `Customer name`                                                                  | The details of the reservation. If the reservation is not found, it will print `Reservation not found.`                                                                                            |
| 5      | **Update reservation**                                                      | `Reservation ID`, `New customer name`, `New room number`, `New contact number`                     | A confirmation message: `Reservation updated successfully!`. If the reservation is not found, it will print `Reservation not found.`                                                             |
| 6      | **Delete reservation**                                                      | `Reservation ID`                                                                                   | A confirmation message: `Reservation deleted successfully!`. If the reservation is not found, it will print `Reservation not found.`                                                               |
| 0      | **Exit**                                                                    | None                                                                                               | Exits the application with a thank you message: `Thank you for using Hotel Reservation System!`                                                                                                  |

---

## 🗄 Database Setup

Execute the following SQL commands to set up your environment:

```sql
-- Create Database
CREATE DATABASE hotel_db;

-- Use Database
USE hotel_db;

-- Create Table
CREATE TABLE reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    guest_name VARCHAR(100) NOT NULL,
    room_number INT NOT NULL,
    contact_number VARCHAR(20) NOT NULL,
    reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

## 🔮 Future Roadmap

This project is designed to evolve through a progressive migration path, moving from raw JDBC to modern enterprise frameworks. Each stage of the migration is available as a separate branch in the repository.

### 🛤️ Migration Path:
1.  **JDBC**: Direct database interaction using SQL.
    -   **Branch**: `main`
    -   **Status**: Completed
2.  **JPA + Hibernate**: Introducing ORM to eliminate boilerplate SQL.
    -   **Branch**: `jpa-hibernate-core`
    -   **Status**: Completed
3.  **Hibernate Native**: Leveraging Hibernate's native API for advanced features.
    -   **Branch**: `hibernate-native`
    -   **Status**: Current State
4.  **Spring (Core)**: Implementing Dependency Injection (DI) and Inversion of Control (IoC) without Spring Boot.
    -   **Status**: Upcoming
5.  **Spring Boot**: Final migration to a full-fledged microservice-ready application.
    -   **Status**: Upcoming

### ✅ Upcoming Tasks:
- [ ] **Transaction Management:** Ensure atomicity of database operations.
- [ ] **GUI Implementation:** Specific interface using JavaFX.
- [ ] **Unit Testing:** Implement JUnit tests for Service and DAO layers.

---

## 👨‍💻 Author

**Mohan Lal**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/mhnuk2007/)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/mhnuk2007)
[![Portfolio](https://img.shields.io/badge/Portfolio-FF5722?style=for-the-badge&logo=html5&logoColor=white)](https://mhnuk2007.github.io/)
