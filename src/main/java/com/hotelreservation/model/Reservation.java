package com.hotelreservation.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private int id;

    @Column(name = "guest_name", nullable = false)
    private String guestName;

    @Column(name = "room_number", nullable = false)
    private int roomNumber;

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @CreationTimestamp
    @Column(name = "reservation_date", insertable = false, updatable = false)
    private Timestamp reservationDate;

    public Reservation() {
    }

    public Reservation(String guestName, int roomNumber, String contactNumber) {
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.contactNumber = contactNumber;
    }

    public Reservation(int id, String guestName, int roomNumber, String contactNumber, Timestamp reservationDate) {
        this.id = id;
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.contactNumber = contactNumber;
        this.reservationDate = reservationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Timestamp getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Timestamp reservationDate) {
        this.reservationDate = reservationDate;
    }
}
