package com.hotelreservation.app.service;

import com.hotelreservation.app.model.Reservation;

import java.util.List;

public interface ReservationService {
    void reserveRoom(String guestName, int roomNumber, String contactNumber);
    List<Reservation> viewReservations();
    Reservation getReservationById(int id);
    Reservation getReservationByIdAndName(int id, String guestName);
    void updateReservation(int id, String newGuestName, int newRoomNumber, String newContactNumber);
    void deleteReservation(int id);
}
