# Hotel Reservation System

This is a **console-based Hotel Reservation System** implemented in **Core Java** using **JDBC** for database connectivity. The project is designed with a **layered architecture** to ensure a clean separation of concerns, making it maintainable, scalable, and easy to understand.

## Project Architecture

The application is structured into three main layers:

1.  **Presentation Layer (UI)**: The `HotelReservation.java` class, which is responsible for handling user input and displaying output. It is the entry point of the application.
2.  **Service Layer**: Handles the business logic. It acts as an intermediary between the presentation layer and the data access layer.
3.  **Data Access Layer (DAO)**: Responsible for all database operations (CRUD). It abstracts the underlying data source from the rest of the application.

This layered approach is a common practice in enterprise application development and is a foundational concept for building robust software.

### Visualized Structure

```
+---------------------------+
|   Presentation Layer      |
|  (HotelReservation.java)  |
+-------------+-------------+
              |
              v
+-------------+-------------+
|       Service Layer       |
| (ReservationServiceImpl)  |
+-------------+-------------+
              |
              v
+-------------+-------------+
|   Data Access Layer (DAO) |
| (ReservationDAOImpl)      |
+-------------+-------------+
              |
              v
+-------------+-------------+
|          Database         |
|         (MySQL)           |
+---------------------------+
```

## Features

*   Reserve a room
*   View all reservations
*   Get room number for a reservation
*   Update an existing reservation
*   Delete a reservation
*   **Clean, Layered Architecture**: Ensures separation of concerns and high maintainability.

## Prerequisites

*   Java 8 or higher
*   MySQL Server
*   MySQL JDBC Driver

## Database Setup

1.  **Create the database**:
    ```sql
    CREATE DATABASE hotel_db;
    ```
2.  **Create the table**:
    ```sql
    USE hotel_db;

    CREATE TABLE reservations (
        reservation_id INT AUTO_INCREMENT PRIMARY KEY,
        guest_name VARCHAR(100) NOT NULL,
        room_number INT NOT NULL,
        contact_number VARCHAR(20) NOT NULL,
        reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
    ```

## Getting Started

1.  **Clone the repository**.
2.  **Open the project** in your favorite IDE (e.g., IntelliJ IDEA, Eclipse).
3.  **Configure the database connection**:
    *   Navigate to the `src` directory.
    *   Create a file named `db.properties`.
    *   Add the following properties to the file, replacing the placeholder values with your MySQL credentials:
        ```properties
        db.driver=com.mysql.cj.jdbc.Driver
        db.url=jdbc:mysql://localhost:3306/hotel_db
        db.username=your_username
        db.password=your_password
        ```
4.  **Add the JDBC Driver**: Make sure the MySQL JDBC driver JAR file is included in your project's classpath.
5.  **Compile and Run**: Execute the `main` method in `HotelReservation.java`.

## Future Improvements

*   **Transaction Management**: Implement transaction handling in the service layer to ensure data integrity.
*   **GUI**: Build a graphical user interface using a framework like JavaFX or Swing.
*   **Spring Boot Migration**: As a next step, this project can be migrated to a full-fledged web application using Spring Boot and Spring Data JPA.

## License

This project is open source and free to use.

---

**Author:** Mohan Lal
* LinkedIn: https://www.linkedin.com/in/mhnuk2007/
* GitHub: https://github.com/mhnuk2007
* Portfolio: https://mhnuk2007.github.io/
* Netlify: https://mhnuk2007.netlify.app/
