import java.sql.*;
import java.util.Scanner;

public class HotelReservation {

    private static final String URL = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found");
            return;
        }

        try (Scanner scanner = new Scanner(System.in);
             Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            while (true) {
                System.out.println("\nHOTEL MANAGEMENT SYSTEM");
                System.out.println("1. Reserve a room");
                System.out.println("2. View reservations");
                System.out.println("3. Get room number");
                System.out.println("4. Update reservation");
                System.out.println("5. Delete reservation");
                System.out.println("0. Exit");
                System.out.print("Choose an option: ");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> reserveRoom(connection, scanner);
                    case 2 -> viewReservations(connection);
                    case 3 -> getRoomNumber(connection, scanner);
                    case 4 -> updateReservation(connection, scanner);
                    case 5 -> deleteReservation(connection, scanner);
                    case 0 -> {
                        exit();
                        return;
                    }
                    default -> System.out.println("Invalid option!");
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void reserveRoom(Connection connection, Scanner scanner) {
        try {
            System.out.print("Customer name: ");
            String name = scanner.nextLine();

            System.out.print("Room number: ");
            int room = Integer.parseInt(scanner.nextLine());

            System.out.print("Contact number: ");
            String contact = scanner.nextLine();

            String sql = "INSERT INTO reservations (guest_name, room_number, contact_number) VALUES (?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setInt(2, room);
                ps.setString(3, contact);

                ps.executeUpdate();
                System.out.println("Room reserved successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Error reserving room: " + e.getMessage());
        }
    }

    private static void viewReservations(Connection connection) {
        String sql = "SELECT * FROM reservations ORDER BY reservation_id";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println();
            System.out.println("+-----+----------------------+-------+-----------------+---------------------+");
            System.out.println("| ID  | Guest Name           | Room  | Contact         | Reservation Date    |");
            System.out.println("+-----+----------------------+-------+-----------------+---------------------+");

            while (rs.next()) {
                System.out.printf(
                        "| %-3d | %-20s | %-5d | %-15s | %-19s |%n",
                        rs.getInt("reservation_id"),
                        rs.getString("guest_name"),
                        rs.getInt("room_number"),
                        rs.getString("contact_number"),
                        rs.getTimestamp("reservation_date").toString().substring(0, 19)
                );
                System.out.println("+-----+----------------------+-------+-----------------+---------------------+");
            }

        } catch (SQLException e) {
            System.out.println("Error fetching reservations");
        }
    }

    private static void getRoomNumber(Connection connection, Scanner scanner) {
        try {
            System.out.print("Reservation ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Customer name: ");
            String name = scanner.nextLine();

            String sql = "SELECT room_number FROM reservations WHERE reservation_id = ? AND guest_name = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.setString(2, name);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("Room number: " + rs.getInt("room_number"));
                    } else {
                        System.out.println("Reservation not found.");
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving room number");
        }
    }

    private static void updateReservation(Connection connection, Scanner scanner) {
        try {
            System.out.print("Reservation ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            if (!reservationExists(connection, id)) {
                System.out.println("Reservation not found.");
                return;
            }

            System.out.print("New customer name: ");
            String name = scanner.nextLine();

            System.out.print("New room number: ");
            int room = Integer.parseInt(scanner.nextLine());

            System.out.print("New contact number: ");
            String contact = scanner.nextLine();

            String sql = "UPDATE reservations SET guest_name=?, room_number=?, contact_number=? WHERE reservation_id=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setInt(2, room);
                ps.setString(3, contact);
                ps.setInt(4, id);

                ps.executeUpdate();
                System.out.println("Reservation updated successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Error updating reservation");
        }
    }

    private static void deleteReservation(Connection connection, Scanner scanner) {
        try {
            System.out.print("Reservation ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            if (!reservationExists(connection, id)) {
                System.out.println("Reservation not found.");
                return;
            }

            String sql = "DELETE FROM reservations WHERE reservation_id=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
                System.out.println("Reservation deleted successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting reservation");
        }
    }

    private static boolean reservationExists(Connection connection, int id) throws SQLException {
        String sql = "SELECT 1 FROM reservations WHERE reservation_id=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    private static void exit() throws InterruptedException {
        System.out.print("Exiting");
        for (int i = 0; i < 5; i++) {
            Thread.sleep(400);
            System.out.print(".");
        }
        System.out.println("\nThank you for using Hotel Reservation System!");
    }
}
