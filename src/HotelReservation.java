import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class HotelReservation {
    private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String username = "root";
    private static final String password = "root";


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL drivers not found");
            e.printStackTrace();

        }
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println();
                System.out.println("HOTEL MANAGEMENT SYSTEM");

                System.out.println("1. Reserve a room");
                System.out.println("2. View Reservations");
                System.out.println("3. Get Room Number");
                System.out.println("4. Update Reservations");
                System.out.println("5. Delete Reservations");
                System.out.println("0. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> reserveRoom(connection, scanner);
                    case 2 -> viewReservations(connection, scanner);
                    case 3 -> getRoomNumber(connection, scanner);
                    case 4 -> updateReservations(connection, scanner);
                    case 5 -> deleteReservations(connection, scanner);
                    case 0 ->{
                        exit();
                        scanner.close();
                        connection.close();
                        return;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }

            }


        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void reserveRoom(Connection connection, Scanner scanner) throws  SQLException{
        try {
            System.out.println("RESERVE A ROOM");
            scanner.nextLine();
            System.out.print("Enter customer name: ");
            String customerName = scanner.nextLine();
            System.out.print("Enter room number: ");
            int roomNumber = scanner.nextInt();
            System.out.print("Enter customer's contact number: ");
            String contactNumber = scanner.next();

            String query = "INSERT INTO reservations (guest_name, room_number, contact_number) VALUES (?, ?, ?)";
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, customerName);
                preparedStatement.setInt(2, roomNumber);
                preparedStatement.setString(3, contactNumber);

                int rows = preparedStatement.executeUpdate();
                if (rows > 0) {

                    System.out.println("Room reserved successfully!");
                } else {
                    System.out.println("Failed to reserve room.");
                }

            }


        } catch (SQLException e) {
            System.out.println("Error reserving room: " + e.getMessage());
        }


    }

    private static void viewReservations(Connection connection, Scanner scanner) {
        try {
            String sql = "SELECT * FROM reservations ORDER BY reservation_id";
            var stmt = connection.createStatement();
            var rs = stmt.executeQuery(sql);

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
            e.printStackTrace();
        }
    }




    private static void getRoomNumber(Connection connection, Scanner scanner) {
    }

    private static void updateReservations(Connection connection, Scanner scanner) {
    }

    private static void deleteReservations(Connection connection, Scanner scanner) {
    }

    private static  void exit() throws InterruptedException {
        System.out.print("Exiting");
        int i = 5;
        while(i!=0){
            System.out.print(".");
            Thread.sleep(450);
            i--;
        }
        System.out.println();
        System.out.println("Thank you for using Hotel Reservation System!!!");
    }

}
