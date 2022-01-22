package com.rental.service;

import com.rental.model.Vehicle;

public interface InventoryService {

    Vehicle addVehicleToInventory(String vehicleType, String vehicleNo);

    Vehicle getVehicleById(String id);

    Vehicle getVehicleByQRCode(String qrcode);

}
