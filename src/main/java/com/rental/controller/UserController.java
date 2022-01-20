package com.rental.controller;

import com.rental.service.BookingService;
import com.rental.service.InventoryService;
import com.rental.service.LocationService;
import com.rental.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    UserService userService;

    BookingService bookingService;

    LocationService locationService;

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
    public ResponseEntity createUser(@RequestParam String name){
        userService.createUser(name);
        return ResponseEntity.ok("");
    }

    @PostMapping("/user/vehicle/book")
    public ResponseEntity bookVehicle(@RequestParam String userId,
                                      @RequestParam String vehicleType,
                                      @RequestParam String locationId){
        User user = userService.getUser(userId);
        Location location = locationService.getLocation(locationId);
        Booking booking = bookingService.bookVehicle(user, VehicleType.valueOf(vehicleType),location);
        return ResponseEntity.ok(booking.getId());
    }

    @PostMapping("/user/vehicle/scan")
    public ResponseEntity scanQrCode(@RequestParam String userId, @RequestParam String qrcode){
        User user = userService.getUser(userId);
        Vehicle vehicle = inventoryService.getVehicleByQRCode(qrcode);
        Booking booking = bookingService.bookVehicle(user, vehicle);
        return ResponseEntity.ok(booking.getId());
    }

    @PostMapping("/user/vehicle/drop")
    public ResponseEntity dropBookedVehicle(@RequestParam String bookingId,
                                            @RequestParam String locationId,
                                            @RequestParam Integer km){
        Booking booking = bookingService.getBooking(bookingId);
        Location location = locationService.getLocation(locationId);
        bookingService.updateBooking(booking,location,km);
        return ResponseEntity.ok("");
    }

    @PostMapping("/user/book/cancel")
    public ResponseEntity cancelBooking(@RequestParam String bookingId){
        return ResponseEntity.ok("");
    }

    @PostMapping("/user/book/")
    public ResponseEntity getInvoice(@RequestParam String vehicleId){
        return ResponseEntity.ok("");
    }


}
