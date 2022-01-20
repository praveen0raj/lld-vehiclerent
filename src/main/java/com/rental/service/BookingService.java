package com.rental.service;

public interface BookingService {

     Booking bookVehicle(User user, VehicleType vehicleType, Location location);

     Booking bookVehicle(User user, Vehicle vehicle);

     Booking getBooking(String bookingId);

     void updateBooking(Booking booking, Location location, Integer km);
}
