package com.hotelreservation.app.service;

import com.hotelreservation.app.dao.ReservationDao;
import com.hotelreservation.app.dao.ReservationDaoImpl;
import com.hotelreservation.app.model.Reservation;


import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    private final ReservationDao dao = new ReservationDaoImpl();

    @Override
    public void reserveRoom(String guestName, int roomNumber, String contactNumber) {
        Reservation r = new Reservation(guestName, roomNumber, contactNumber);
        dao.save(r);
        System.out.println("Reservation added successfully!");
    }

    @Override
    public List<Reservation> viewReservations() {
        return dao.findAll();
    }

    @Override
    public Reservation getReservationById(int id) {
        return dao.findById(id);
    }

    @Override
    public Reservation getReservationByIdAndName(int id, String guestName) {
        return dao.findByIdAndName(id, guestName);
    }

    @Override
    public void updateReservation(int id, String newGuestName, int newRoomNumber, String newContactNumber) {
        Reservation r = dao.findById(id);
        if (r != null) {
            r.setGuestName(newGuestName);
            r.setRoomNumber(newRoomNumber);
            r.setContactNumber(newContactNumber);
            dao.update(r);
            System.out.println("Reservation updated successfully!");
        } else {
            System.out.println("Reservation ID not found!");
        }
    }

    @Override
    public void deleteReservation(int id) {
        Reservation r = dao.findById(id);
        if (r != null) {
            dao.delete(id);
            System.out.println("Reservation deleted successfully!");
        } else {
            System.out.println("Reservation ID not found!");
        }
    }
}
