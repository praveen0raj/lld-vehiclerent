package com.rental.controller;

import com.rental.model.Admin;
import com.rental.model.Location;
import com.rental.model.Vehicle;
import com.rental.service.AdminService;
import com.rental.service.InventoryService;
import com.rental.service.LocationService;
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
    public Admin createAdmin(@RequestParam String name){
        return adminService.createAdmin(name);
    }

    @PostMapping("/admin/inventory/add")
    public Vehicle addVehicle(@RequestParam String adminId, @RequestParam String vehicleType, @RequestParam String vehicleNo){
        return inventoryService.addVehicleToInventory(vehicleType,vehicleNo);
    }

    @PostMapping("/admin/location/add")
    public Location addLocation(@RequestParam double x, @RequestParam double y, @RequestParam String name){
        return locationService.addLocation(x,y,"123123123");
    }

    @PostMapping("/admin/location/update")
    public void updateVehicleLocation(@RequestParam String vehicleId, @RequestParam String locationId){
        locationService.updateVehicleLocation(inventoryService.getVehicleById(vehicleId),
                locationService.getLocation(locationId));
    }

}
