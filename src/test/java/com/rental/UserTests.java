package com.rental;

import com.rental.controller.AdminController;
import com.rental.controller.UserController;
import com.rental.impl.BookingServiceImpl;
import com.rental.impl.InventoryServiceImpl;
import com.rental.impl.LocationServiceImpl;
import com.rental.impl.UserServiceImpl;
import com.rental.model.*;
import com.rental.service.*;
import com.rental.strategy.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserTests {

    UserController userController;

    AdminController adminController;

    NotificationService  notificationService;

    @BeforeEach
    void setUP(){
        VehicleTypeMatching vehicleTypeMatching=new VehicleTypeMatchingImpl();
        QRCodeGEneratorService generator = new QRCodeGEneratorServiceImpl();
        PriceStrategy priceStrategy = new PriceStrategyImpl();
        InventoryService inventoryService = new InventoryServiceImpl(vehicleTypeMatching, generator);
        LocationService locationService = new LocationServiceImpl();
        UserService userService=new UserServiceImpl();
        BookingService bookingService=new BookingServiceImpl(locationService, priceStrategy);
        userController= new UserController(userService,bookingService,locationService,
                inventoryService);
        adminController= new AdminController(locationService,inventoryService);

    }

    @Test
    void rentCar() {

        //Add vehicles to inventory
        Admin admin = adminController.createAdmin("Praveen");

        //add vehicles
        Vehicle vehicle = adminController.addVehicle(admin.getId(),VehicleType.MOTORCYCLE.toString(),"12345678");
        Vehicle vehicle1 = adminController.addVehicle(admin.getId(),VehicleType.MOTORCYCLE.toString(),"22345678");
        Vehicle vehicle2 = adminController.addVehicle(admin.getId(),VehicleType.HATCHBACK.toString(),"32345678");
        Vehicle vehicle3 = adminController.addVehicle(admin.getId(),VehicleType.HATCHBACK.toString(),"42345678");
        Vehicle vehicle4 = adminController.addVehicle(admin.getId(),VehicleType.SUV.toString(),"52345678");
        Vehicle vehicle5 = adminController.addVehicle(admin.getId(),VehicleType.SUV.toString(),"62345678");

        //add location
        Location location1 = adminController.addLocation(1,2,"Aya nagar");
        Location location2 = adminController.addLocation(3,2,"Aya nagar");

        //add to location1
        adminController.updateVehicleLocation(vehicle.getId(),location1.getId());
        adminController.updateVehicleLocation(vehicle1.getId(),location1.getId());
        adminController.updateVehicleLocation(vehicle2.getId(),location1.getId());
        adminController.updateVehicleLocation(vehicle3.getId(),location1.getId());

        //add to location2
        adminController.updateVehicleLocation(vehicle4.getId(),location2.getId());
        adminController.updateVehicleLocation(vehicle5.getId(),location2.getId());


        //book vehicle
        User usr = userController.createUser("raj");
        Booking booking = userController.bookVehicle(usr.getId(),VehicleType.MOTORCYCLE.toString(),location1.getId());
        assert booking!=null;


        //pick vehicle or book if available
        userController.scanQrCode(usr.getId(),"");

        //returning vehicle
        userController.returnVehicle(usr.getId(), booking.getId(),location2.getId(),10000);

        //cancel booking
        userController.cancelBooking(usr.getId(), booking.getId());

        //customer can make payment
        userController.payBooking(usr.getId(), booking.getId());

        //get invoice
        userController.getInvoice(usr.getId(), booking.getId());

        //Users Should get remainder notification a day before their booking.
        notificationService.sendNotification(usr, booking);

        //Users should be able to choose and add devices and services to the vehicles while they book.
        userController.addDevicesAndServices(usr,booking, new GPSDevice());

        //System should be provide APIs to search for vechicles booked by users by user id and for a particular interval.


    }

}
