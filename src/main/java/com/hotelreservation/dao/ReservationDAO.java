package com.hotelreservation.dao;

import com.hotelreservation.model.Reservation;
import java.util.List;

public interface ReservationDAO {

    void save(Reservation reservation);

    Reservation findById(Long id);

    List<Reservation> findAll();

    void update(Reservation reservation);

    void delete(Long id);
}
