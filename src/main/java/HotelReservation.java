
import com.hotelreservation.model.Reservation;
import com.hotelreservation.service.ReservationService;
import com.hotelreservation.service.ReservationServiceImpl;

import java.util.List;
import java.util.Scanner;

public class HotelReservation {

    private static final ReservationService service = new ReservationServiceImpl();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.println("\nHOTEL MANAGEMENT SYSTEM");
                System.out.println("1. Reserve a room");
                System.out.println("2. View reservations");
                System.out.println("3. Get room number");
                System.out.println("4. Get reservation");
                System.out.println("5. Update reservation");
                System.out.println("6. Delete reservation");
                System.out.println("0. Exit");
                System.out.print("Choose an option: ");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> reserveRoom(scanner);
                    case 2 -> viewReservations();
                    case 3 -> getRoomNumber(scanner);
                    case 4 -> getReservation(scanner);
                    case 5 -> updateReservation(scanner);
                    case 6 -> deleteReservation(scanner);
                    case 0 -> exit();
                    default -> System.out.println("Invalid option!");
                }
            }
        }
    }

    private static void reserveRoom(Scanner scanner) {
        System.out.print("Customer name: ");
        String name = scanner.nextLine();
        System.out.print("Room number: ");
        int room = Integer.parseInt(scanner.nextLine());
        System.out.print("Contact number: ");
        String contact = scanner.nextLine();

        service.reserveRoom(name, room, contact);
    }

    private static void viewReservations() {
        List<Reservation> reservations = service.viewReservations();
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }

        System.out.println("+-----+----------------------+-------+-----------------+---------------------+");
        System.out.println("| ID  | Guest Name           | Room  | Contact         | Reservation Date    |");
        System.out.println("+-----+----------------------+-------+-----------------+---------------------+");

        for (Reservation r : reservations) {
            System.out.printf("| %-3d | %-20s | %-5d | %-15s | %-19s |%n",
                    r.getId(), r.getGuestName(), r.getRoomNumber(),
                    r.getContactNumber(), r.getReservationDate());
        }
    }

    private static void getRoomNumber(Scanner scanner) {
        System.out.print("Reservation ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Customer name: ");
        String name = scanner.nextLine();

        System.out.println(service.getRoomNumber(id, name));
    }

    private static void getReservation(Scanner scanner) {
        System.out.print("Reservation ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Customer name: ");
        String name = scanner.nextLine();

        service.getReservation(id, name);
        System.out.println();
    }


    private static void updateReservation(Scanner scanner) {
        System.out.print("Reservation ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("New customer name: ");
        String name = scanner.nextLine();
        System.out.print("New room number: ");
        int room = Integer.parseInt(scanner.nextLine());
        System.out.print("New contact number: ");
        String contact = scanner.nextLine();

        service.updateReservation(id, name, room, contact);
    }

    private static void deleteReservation(Scanner scanner) {
        System.out.print("Reservation ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        service.deleteReservation(id);
    }

    private static void exit() {
        System.out.println("Thank you for using Hotel Reservation System!");
        System.exit(0);
    }
}
