package com.rental.impl;

import com.rental.model.Vehicle;
import com.rental.model.VehicleType;
import com.rental.service.InventoryService;
import com.rental.service.QRCodeGEneratorService;
import com.rental.service.VehicleTypeMatching;

import java.util.HashMap;
import java.util.Map;

public class InventoryServiceImpl implements InventoryService {

    Map<String,Vehicle> vehicleMap = new HashMap<>();

    Map<String, Vehicle> vehicleMapWithQRCodes=new HashMap<>();

    VehicleTypeMatching vehicleTypeMatching;

    QRCodeGEneratorService generator;

    public InventoryServiceImpl(VehicleTypeMatching vehicleTypeMatching,
                                QRCodeGEneratorService generator){
        this.vehicleTypeMatching=vehicleTypeMatching;
        this.generator=generator;
    }

    @Override
    public void addVehicleToInventory(String vehicleDimension, String vehicleNo) {
        VehicleType vehicleType1 = vehicleTypeMatching.getVehicleType(vehicleDimension);
        Vehicle vehicle= new Vehicle(vehicleType1,vehicleNo);
        vehicle.setQrCode(generator.generateQRCode(vehicleType1,vehicleNo));
        vehicleMap.put(vehicle.getId(),vehicle);
        vehicleMapWithQRCodes.put(vehicle.getQrCode(), vehicle);
    }

    public Vehicle getVehicleById(String vehicleId){
        return vehicleMap.get(vehicleId);
    }

    @Override
    public Vehicle getVehicleByQRCode(String qrcode) {
        return vehicleMapWithQRCodes.get(qrcode);
    }
}
