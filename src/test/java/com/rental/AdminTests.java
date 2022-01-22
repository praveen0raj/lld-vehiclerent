package com.rental;

import com.rental.controller.AdminController;
import com.rental.impl.*;
import com.rental.service.*;
import com.rental.strategy.QRCodeGEneratorService;
import com.rental.strategy.QRCodeGEneratorServiceImpl;
import com.rental.strategy.VehicleTypeMatching;
import com.rental.strategy.VehicleTypeMatchingImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminTests {

    AdminController adminController;

    @BeforeEach
    void setUP(){
        VehicleTypeMatching vehicleTypeMatching=new VehicleTypeMatchingImpl();
        QRCodeGEneratorService generator = new QRCodeGEneratorServiceImpl();
        AdminService adminService = new AdminServiceImpl();
        InventoryService inventoryService = new InventoryServiceImpl(vehicleTypeMatching, generator);
        LocationService locationService = new LocationServiceImpl();
        adminController= new AdminController(locationService,inventoryService);
    }

    @Test
    void createAdmin() {

    }

}
