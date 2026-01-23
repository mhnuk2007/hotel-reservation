package com.hotelreservation.service;

import com.hotelreservation.model.Reservation;

import java.util.List;

public interface ReservationService {

    void reserveRoom(String guestName, int roomNumber, String contactNumber);

    List<Reservation> viewReservations();

    String getRoomNumber(int id, String guestName);

    Reservation getReservation(int id, String guestName);

    void updateReservation(int id, String guestName, int roomNumber, String contactNumber);

    void deleteReservation(int id);

    boolean exists(int id);
}
