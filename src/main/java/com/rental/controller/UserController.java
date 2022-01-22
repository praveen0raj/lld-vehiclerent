package com.rental.controller;

import com.rental.model.*;
import com.rental.service.BookingService;
import com.rental.service.InventoryService;
import com.rental.service.LocationService;
import com.rental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    BookingService bookingService;

    @Autowired
    LocationService locationService;

    @Autowired
    InventoryService inventoryService;

    public UserController(UserService userService,
                          BookingService bookingService,
                          LocationService locationService,
                          InventoryService inventoryService){
        this.userService=userService;
        this.bookingService=bookingService;
        this.locationService=locationService;
        this.inventoryService=inventoryService;


    }

    @PostMapping("/user/create")
    public User createUser(@RequestParam String name){
        return userService.createUser(name);
    }

    @PostMapping("/user/vehicle/book")
    public Booking bookVehicle(@RequestParam String userId,
                                      @RequestParam String vehicleType,
                                      @RequestParam String locationId){
        User user = userService.getUser(userId);
        Location location = locationService.getLocation(locationId);
        Booking booking = bookingService.bookVehicle(user, VehicleType.valueOf(vehicleType),location);
        return booking;
    }

    @PostMapping("/user/vehicle/scan")
    public Booking scanQrCode(@RequestParam String userId, @RequestParam String qrcode){
        User user = userService.getUser(userId);
        Vehicle vehicle = inventoryService.getVehicleByQRCode(qrcode);
        Booking booking = bookingService.bookVehicle(user, vehicle);
        return booking;
    }

    @PostMapping("/user/vehicle/drop")
    public void returnVehicle(@RequestParam String userId, @RequestParam String bookingId,
                                            @RequestParam String locationId,
                                            @RequestParam Integer km){
        Booking booking = bookingService.getBooking(bookingId);
        Location location = locationService.getLocation(locationId);
        bookingService.updateBooking(booking,location,km);
    }

    @PostMapping("/user/book/payment")
    public void payBooking(@RequestParam String userId, @RequestParam String bookingId){
        return ;
    }

    @PostMapping("/user/book/cancel")
    public void cancelBooking(@RequestParam String userId, @RequestParam String bookingId){
        return ;
    }

    @PostMapping("/user/book/invoice")
    public void getInvoice(@RequestParam String userId, @RequestParam String bookingId){
        return ;
    }


}
