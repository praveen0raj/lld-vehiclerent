package com.rental.impl;

import com.rental.service.BookingService;
import com.rental.service.LocationService;
import com.rental.service.PriceStrategy;

import java.util.HashMap;
import java.util.Map;

public class BookingServiceImpl implements BookingService {

    Map<String, Booking> bookings = new HashMap<>();

    LocationService locationService;

    PriceStrategy priceStrategy;

    public BookingServiceImpl(LocationService locationService, PriceStrategy priceStrategy){
        this.locationService=locationService;
        this.priceStrategy=priceStrategy;
    }

    @Override
    public Booking bookVehicle(User user, VehicleType vehicleType, Location location) {
        Vehicle vehicle  = locationService.getVehicle(location, vehicleType);
        if(vehicle==null){
            throw new RuntimeException("vehicle is not available");
        }else{
            Booking booking = new Booking(user,vehicle,location);
            bookings.put(booking.getId(),booking);
            return booking;
        }
    }

    @Override
    public Booking bookVehicle(User user, Vehicle vehicle) {

        return null;
    }

    @Override
    public Booking getBooking(String bookingId) {
        return bookings.get(bookingId);
    }

    @Override
    public void updateBooking(Booking booking, Location location, Integer km) {
        booking.setBookingStatus(BookingStatus.DROP);
        booking.setDropLocation(location);
        double price = priceStrategy.calculatePrice(booking.getPickedLocation(), booking.getDropLocation(),
                booking.getVehicle().getVehicleType(), booking.getStartKm(), booking.getEndKm());
        booking.setPrice(price);
    }

}
