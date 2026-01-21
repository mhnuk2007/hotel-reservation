package service;

import dao.ReservationDAO;
import dao.ReservationDAOImpl;
import model.Reservation;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    private final ReservationDAO reservationDAO = new ReservationDAOImpl();

    @Override
    public void reserveRoom(String guestName, int roomNumber, String contactNumber) {
        reservationDAO.addReservation(new Reservation(guestName, roomNumber, contactNumber));
        System.out.println("Room reserved successfully!");
    }

    @Override
    public List<Reservation> viewReservations() {
        return reservationDAO.getAllReservations();
    }

    @Override
    public String getRoomNumber(int id, String name) {
        return reservationDAO.getReservationByIdAndName(id, name);
    }

    @Override
    public void updateReservation(int id, String guestName, int roomNumber, String contactNumber) {
        if (!reservationDAO.exists(id)) {
            System.out.println("Reservation not found.");
            return;
        }
        reservationDAO.updateReservation(new Reservation(id, guestName, roomNumber, contactNumber, null));
        System.out.println("Reservation updated successfully!");
    }

    @Override
    public void deleteReservation(int id) {
        if (!reservationDAO.exists(id)) {
            System.out.println("Reservation not found.");
            return;
        }
        reservationDAO.deleteReservation(id);
        System.out.println("Reservation deleted successfully!");
    }

    @Override
    public boolean exists(int id) {
        return reservationDAO.exists(id);
    }
}
