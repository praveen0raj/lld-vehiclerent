package com.rental.service;

import com.rental.model.Vehicle;

public interface InventoryService {

    void addVehicleToInventory(String vehicleType, String vehicleNo);

    Vehicle getVehicleById(String id);

    Vehicle getVehicleByQRCode(String qrcode);

}
