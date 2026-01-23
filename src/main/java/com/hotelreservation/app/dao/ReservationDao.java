package com.hotelreservation.app.dao;

import com.hotelreservation.app.model.Reservation;

import java.util.List;

public interface ReservationDao {
    void save(Reservation reservation);
    List<Reservation> findAll();
    Reservation findById(int id);
    Reservation findByIdAndName(int id, String guestName);
    void update(Reservation reservation);
    void delete(int id);
}
