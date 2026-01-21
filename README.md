# Hotel Reservation System

This is a **console-based Hotel Reservation System** implemented in Java using **JDBC** for MySQL database connectivity.

## Features

* Reserve a room
* View all reservations
* Get room number for a reservation
* Update a reservation
* Delete a reservation

## Prerequisites

* Java 8 or higher
* MySQL server
* JDBC Driver for MySQL (`mysql-connector-java`)
* IDE (IntelliJ IDEA, Eclipse, etc.) or terminal

## Database Setup

1. Create a database named `hotel_db`:

```sql
CREATE DATABASE hotel_db;
```

2. Create `reservations` table:

```sql
CREATE TABLE reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    guest_name VARCHAR(100) NOT NULL,
    room_number INT NOT NULL,
    contact_number VARCHAR(20) NOT NULL,
    reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## Getting Started

1. Clone the repository:

```bash
git clone https://github.com/mhnuk2007/hotel-reservation.git
```

2. Open the project in your IDE or terminal.
3. Update database credentials in `HotelReservation.java` if necessary:

```java
private static final String URL = "jdbc:mysql://localhost:3306/hotel_db";
private static final String USERNAME = "root";
private static final String PASSWORD = "root";
```

4. Compile and run the program:

```bash
javac src/HotelReservation.java
java -cp .;path/to/mysql-connector-java.jar HotelReservation
```

> Replace `path/to/mysql-connector-java.jar` with your MySQL JDBC driver path.

## Usage

* The program will display a menu with options.
* Enter the number corresponding to the desired action.
* Follow prompts to enter reservation details.
* All operations are reflected in the `reservations` table in MySQL.

## Future Improvements

* Implement **DAO pattern** for cleaner separation.
* Add **transaction management**.
* Implement a **GUI version** using JavaFX.
* Convert to **Spring Boot + JPA** application for web interface.

## License

This project is open source and free to use.

---

**Author:** Mohan Lal
* LinkedIn: https://www.linkedin.com/in/mhnuk2007/
* GitHub: https://github.com/mhnuk2007
* Portfolio: https://mhnuk2007.github.io/
* Netlify: https://mhnuk2007.netlify.app/
