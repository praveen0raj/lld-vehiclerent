package com.rental.controller;

import com.rental.model.Admin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    AdminService adminService;

    InventoryService inventoryService;

    LocationService locationService;

    public AdminController(LocationService locationService,
                          InventoryService inventoryService){
        this.locationService=locationService;
        this.inventoryService=inventoryService;
    }

    @PostMapping("/admin/create")
    public ResponseEntity createAdmin(@RequestParam String name){
        Admin admin = adminService.createAdmin(name);
        return ResponseEntity.ok(admin.getId());
    }

    @PostMapping("/admin/inventory/add")
    public ResponseEntity addVehicle(String vehicleType, String vehicleNo){
        inventoryService.addVehicleToInventory(vehicleType,vehicleNo);
        return ResponseEntity.ok("");
    }

    @PostMapping("/admin/location/update")
    public ResponseEntity updateVehicleLocation(String vehicleId, String locationId){
        locationService.updateVehicleLocation(inventoryService.getVehicleById(vehicleId),
                locationService.getLocation(locationId));
        return ResponseEntity.ok("");
    }

}
