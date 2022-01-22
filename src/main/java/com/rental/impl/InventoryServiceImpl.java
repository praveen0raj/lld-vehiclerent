package com.rental.impl;

import com.rental.model.Vehicle;
import com.rental.model.VehicleType;
import com.rental.service.InventoryService;
import com.rental.strategy.QRCodeGEneratorService;
import com.rental.strategy.VehicleTypeMatching;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
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
    public Vehicle addVehicleToInventory(String vehicleDimension, String vehicleNo) {
        VehicleType vehicleType1 = vehicleTypeMatching.getVehicleType(vehicleDimension);
        Vehicle vehicle= new Vehicle(vehicleType1,vehicleNo);
        vehicle.setQrCode(generator.generateQRCode(vehicleType1,vehicleNo));
        vehicleMap.put(vehicle.getId(),vehicle);
        vehicleMapWithQRCodes.put(vehicle.getQrCode(), vehicle);
        return vehicle;
    }

    public Vehicle getVehicleById(String vehicleId){
        return vehicleMap.get(vehicleId);
    }

    @Override
    public Vehicle getVehicleByQRCode(String qrcode) {
        return vehicleMapWithQRCodes.get(qrcode);
    }
}
