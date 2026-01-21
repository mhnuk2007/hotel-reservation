package model;

import java.sql.Timestamp;

public class Reservation {
    private int id; // auto-generated
    private String guestName;
    private int roomNumber;
    private String contactNumber;
    private Timestamp reservationDate; // auto-generated

    // Constructor for creating a new reservation (id and date auto-generated)
    public Reservation(String guestName, int roomNumber, String contactNumber) {
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.contactNumber = contactNumber;
    }

    // Constructor for retrieving existing reservation from DB (with auto-generated fields)
    public Reservation(int id, String guestName, int roomNumber, String contactNumber, Timestamp reservationDate) {
        this.id = id;
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.contactNumber = contactNumber;
        this.reservationDate = reservationDate;
    }

    // Getters and setters
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
