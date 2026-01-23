package com.hotelreservation.service;

import java.util.List;

import com.hotelreservation.dao.ReservationDAO;
import com.hotelreservation.dao.ReservationDAOImpl;
import com.hotelreservation.model.Reservation;

public class ReservationServiceImpl implements ReservationService {

    private final ReservationDAO dao = new ReservationDAOImpl();

    @Override
    public void reserveRoom(String guestName, int roomNumber, String contactNumber) {

        Reservation reservation = new Reservation();
        reservation.setGuestName(guestName);
        reservation.setRoomNumber(roomNumber);
        reservation.setContactNumber(contactNumber);

        dao.save(reservation);
    }

    @Override
    public List<Reservation> viewReservations() {
        return dao.findAll();
    }

    @Override
    public String getRoomNumber(int id, String guestName) {

        Reservation reservation = dao.findById((long) id);

        if (reservation != null && reservation.getGuestName().equalsIgnoreCase(guestName)) {
            return String.valueOf(reservation.getRoomNumber());
        }

        return null;
    }

    @Override
    public Reservation getReservation(int id, String guestName) {

        Reservation reservation = dao.findById((long) id);

        if (reservation != null && reservation.getGuestName().equalsIgnoreCase(guestName)) {
            return reservation;
        }

        return null;
    }

    @Override
    public void updateReservation(int id, String guestName, int roomNumber, String contactNumber) {

        Reservation reservation = dao.findById((long) id);

        if (reservation == null) {
            throw new RuntimeException("Reservation not found with id: " + id);
        }

        if (!reservation.getGuestName().equalsIgnoreCase(guestName)) {
            throw new RuntimeException("Guest name mismatch");
        }

        reservation.setRoomNumber(roomNumber);
        reservation.setContactNumber(contactNumber);

        dao.update(reservation);
    }

    @Override
    public void deleteReservation(int id) {
        dao.delete((long) id);
    }

    @Override
    public boolean exists(int id) {
        return dao.findById((long) id) != null;
    }
}
