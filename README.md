# 🏨 Hotel Reservation System

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-4479A1?style=for-the-badge&logo=java&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-green.svg?style=for-the-badge)

A robust, console-based **Hotel Reservation System** built with **Core Java** and **JDBC**. This project demonstrates a professional implementation of the **Layered Architecture** pattern, ensuring clean separation of concerns, maintainability, and scalability.

---

## 📖 Table of Contents
- [Overview](#-overview)
- [Architecture](#-architecture)
- [Project Structure](#-project-structure)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Getting Started](#-getting-started)
- [Database Setup](#-database-setup)
- [Future Roadmap](#-future-roadmap)
- [Author](#-author)

---

## 🚀 Overview

This application serves as a practical revision of Core Java and JDBC concepts, refactored to mirror enterprise-level development standards. It transitions from a monolithic design to a modular, layered approach, preparing the groundwork for advanced frameworks like Spring Boot.

**Key Highlights:**
*   **Clean Code:** Adheres to SOLID principles.
*   **Scalable Design:** Decoupled layers allow for easy updates and testing.
*   **Secure Configuration:** Centralized database credentials.

---

## 🏗 Architecture

The project is structured into three distinct layers:

1.  **Presentation Layer (UI):** Handles user interaction via the console (`HotelReservation.java`).
2.  **Service Layer (Business Logic):** Processes data and applies business rules (`ReservationService`).
3.  **Data Access Layer (DAO):** Manages direct database interactions (`ReservationDAO`).

### 🔄 Refactoring Journey: Monolith → Layered
*   ✅ **DAO Pattern:** Isolated SQL operations.
*   ✅ **Service Layer:** Centralized business logic.
*   ✅ **Model:** POJOs representing database entities.
*   ✅ **Utilities:** Reusable DB connection logic.

---

## 📂 Project Structure

```plaintext
src/
├── dao/                  # Data Access Objects
│   ├── ReservationDAO.java
│   └── ReservationDAOImpl.java
├── model/                # Data Models (POJOs)
│   └── Reservation.java
├── service/              # Business Logic Layer
│   ├── ReservationService.java
│   └── ReservationServiceImpl.java
├── util/                 # Utility Classes
│   └── DBConnection.java
├── db.properties         # Database Configuration
└── HotelReservation.java # Main Application Entry Point
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

*   **Language:** Java 8+
*   **Database:** MySQL
*   **Connectivity:** JDBC (Java Database Connectivity)
*   **Driver:** MySQL Connector/J

---

## 🏁 Getting Started

### Prerequisites
*   Java Development Kit (JDK) 8 or higher
*   MySQL Server installed and running
*   IDE (IntelliJ IDEA, Eclipse) or Terminal

### Installation

1.  **Clone the Repository**
    ```bash
    git clone https://github.com/mhnuk2007/hotel-reservation.git
    cd hotel-reservation
    ```

2.  **Configure Database**
    Create a `db.properties` file in the `src` directory:
    ```properties
    db.driver=com.mysql.cj.jdbc.Driver
    db.url=jdbc:mysql://localhost:3306/hotel_db
    db.username=root
    db.password=your_password
    ```

3.  **Run the Application**
    Compile and execute `HotelReservation.java`.

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

This project is designed to evolve through a progressive migration path, moving from raw JDBC to modern enterprise frameworks.

### 🛤️ Migration Path:
1.  **JDBC (Current State)**: Direct database interaction using SQL.
2.  **JPA + Hibernate**: Introducing ORM (Object-Relational Mapping) to eliminate boilerplate SQL.
3.  **Hibernate Native**: Leveraging Hibernate's native API for advanced features.
4.  **Spring (Core)**: Implementing Dependency Injection (DI) and Inversion of Control (IoC) without Spring Boot.
5.  **Spring Boot**: Final migration to a full-fledged microservice-ready application.

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
