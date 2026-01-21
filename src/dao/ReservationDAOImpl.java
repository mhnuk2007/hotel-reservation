package dao;

import model.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements  ReservationDAO {

    private final Connection connection;
    public ReservationDAOImpl(Connection connection) {
        this.connection = connection;
    }



    @Override
    public void addReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (guest_name, room_number, contact_number) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, reservation.getGuestName());
            ps.setInt(2, reservation.getRoomNumber());
            ps.setString(3, reservation.getContactNumber());

            ps.executeUpdate();
            System.out.println("Room reserved successfully!");
        } catch (SQLException e){
            System.out.println("Error reserving room" + e.getMessage());
        }

    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations ORDER BY reservation_id";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Reservation reservation = new Reservation(
                        rs.getInt("reservation_id"),
                        rs.getString("guest_name"),
                        rs.getInt("room_number"),
                        rs.getString("contact_number"),
                        rs.getTimestamp("reservation_date")
                );
                reservations.add(reservation);
            }

        } catch (SQLException e){
            System.out.println("Error fetching reservations" + e.getMessage());
        }

            return reservations;
    }

    @Override
    public String getReservationByIdAndName(int id, String name) {
        try {
            String sql = "SELECT room_number FROM reservations WHERE reservation_id = ? AND guest_name = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.setString(2, name);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return "Room number: " + rs.getInt("room_number");
                    } else {
                        return "Reservation not found.";
                    }
                }
            }

        } catch (SQLException e) {

            System.out.println("Error retrieving room number" + e.getMessage());
            return "Error retrieving room number";
        }
    }



    @Override
    public void updateReservation(Reservation reservation) {

        String sql = "UPDATE reservations SET guest_name=?, room_number=?, contact_number=? WHERE reservation_id=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, reservation.getGuestName() );
            ps.setInt(2, reservation.getRoomNumber());
            ps.setString(3, reservation.getContactNumber());
            ps.setInt(4, reservation.getId());

            ps.executeUpdate();
            System.out.println("Reservation updated successfully!");
        } catch (SQLException e) {
        System.out.println("Error updating reservation");
    }

    }

    @Override
    public void deleteReservation(int id) {
        String sql = "DELETE FROM reservations WHERE reservation_id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Reservation deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting reservation");
        }
    }

    @Override
    public boolean exists(int id) {
        String sql = "SELECT 1 FROM reservations WHERE reservation_id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("Error checking reservation existence: " + e.getMessage());
            return false;
        }
    }

}




