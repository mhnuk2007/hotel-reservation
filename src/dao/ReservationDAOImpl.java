package dao;

import model.Reservation;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    @Override
    public void addReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (guest_name, room_number, contact_number) VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, reservation.getGuestName());
            ps.setInt(2, reservation.getRoomNumber());
            ps.setString(3, reservation.getContactNumber());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error reserving room", e);
        }
    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations ORDER BY reservation_id";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                reservations.add(new Reservation(
                        rs.getInt("reservation_id"),
                        rs.getString("guest_name"),
                        rs.getInt("room_number"),
                        rs.getString("contact_number"),
                        rs.getTimestamp("reservation_date")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching reservations", e);
        }
        return reservations;
    }

    @Override
    public String getReservationByIdAndName(int id, String name) {
        String sql = "SELECT room_number FROM reservations WHERE reservation_id=? AND guest_name=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setString(2, name);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return "Room number: " + rs.getInt("room_number");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving room number", e);
        }
        return "Reservation not found.";
    }

    @Override
    public void updateReservation(Reservation reservation) {
        String sql = "UPDATE reservations SET guest_name=?, room_number=?, contact_number=? WHERE reservation_id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, reservation.getGuestName());
            ps.setInt(2, reservation.getRoomNumber());
            ps.setString(3, reservation.getContactNumber());
            ps.setInt(4, reservation.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating reservation", e);
        }
    }

    @Override
    public void deleteReservation(int id) {
        String sql = "DELETE FROM reservations WHERE reservation_id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting reservation", e);
        }
    }

    @Override
    public boolean exists(int id) {
        String sql = "SELECT 1 FROM reservations WHERE reservation_id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeQuery().next();

        } catch (SQLException e) {
            throw new RuntimeException("Error checking reservation existence", e);
        }
    }
}
