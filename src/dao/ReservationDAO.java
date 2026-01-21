package dao;

import model.Reservation;

import java.sql.Connection;
import java.util.List;

public interface ReservationDAO {
    void addReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    String getReservationByIdAndName(int id, String name );
    void updateReservation(Reservation reservation);
    void deleteReservation(int id);
    boolean exists(int id);
}
