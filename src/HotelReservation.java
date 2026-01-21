import dao.ReservationDAO;
import dao.ReservationDAOImpl;
import model.Reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class HotelReservation {

    private static final String URL = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static ReservationDAO reservationDAO;

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found");
            return;
        }

        try (Scanner scanner = new Scanner(System.in);
             Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            reservationDAO = new ReservationDAOImpl(connection);

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
                    case 1 -> reserveRoom(scanner);
                    case 2 -> viewReservations();
                    case 3 -> getRoomNumber(scanner);
                    case 4 -> updateReservation(scanner);
                    case 5 -> deleteReservation(scanner);
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

    private static void reserveRoom(Scanner scanner) {
        System.out.print("Customer name: ");
        String name = scanner.nextLine();
        System.out.print("Room number: ");
        int room = Integer.parseInt(scanner.nextLine());
        System.out.print("Contact number: ");
        String contact = scanner.nextLine();

        reservationDAO.addReservation(new Reservation(name, room, contact));
    }

    private static void viewReservations() {
        List<Reservation> reservations = reservationDAO.getAllReservations();
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }

        System.out.println("+-----+----------------------+-------+-----------------+---------------------+");
        System.out.println("| ID  | Guest Name           | Room  | Contact         | Reservation Date    |");
        System.out.println("+-----+----------------------+-------+-----------------+---------------------+");

        for (Reservation r : reservations) {
            System.out.printf(
                    "| %-3d | %-20s | %-5d | %-15s | %-19s |%n",
                    r.getId(),
                    r.getGuestName(),
                    r.getRoomNumber(),
                    r.getContactNumber(),
                    r.getReservationDate() != null ? r.getReservationDate() : "N/A"
            );
            System.out.println("+-----+----------------------+-------+-----------------+---------------------+");
        }
    }

    private static void getRoomNumber(Scanner scanner) {
        System.out.print("Reservation ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Customer name: ");
        String name = scanner.nextLine();

        String result = reservationDAO.getReservationByIdAndName(id, name);
        System.out.println(result);
    }

    private static void updateReservation(Scanner scanner) {
        System.out.print("Reservation ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (!reservationDAO.exists(id)) {
            System.out.println("Reservation not found.");
            return;
        }

        System.out.print("New customer name: ");
        String name = scanner.nextLine();
        System.out.print("New room number: ");
        int room = Integer.parseInt(scanner.nextLine());
        System.out.print("New contact number: ");
        String contact = scanner.nextLine();

        Reservation reservation = new Reservation(id, name, room, contact, null);
        reservationDAO.updateReservation(reservation);
    }

    private static void deleteReservation(Scanner scanner) {
        System.out.print("Reservation ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (!reservationDAO.exists(id)) {
            System.out.println("Reservation not found.");
            return;
        }

        reservationDAO.deleteReservation(id);
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
