package com.rental.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
public class Booking {
    private String id;
    private User user;
    private Vehicle vehicle;
    private Date bookedDate;
    private Location pickedLocation;
    @Setter
    private Location dropLocation;

    @Setter
    private double price;
    @Setter
    private BookingStatus bookingStatus;
    private Payment payment;

    Integer startKm;

    Integer endKm;

    public Booking(User user, Vehicle vehicle, Location pickedLocation) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.vehicle = vehicle;
        this.pickedLocation = pickedLocation;
        this.bookingStatus = BookingStatus.CREATED;
        this.bookedDate=new Date();

    }
}
