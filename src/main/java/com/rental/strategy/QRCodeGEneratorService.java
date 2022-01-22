package com.rental.strategy;

import com.rental.model.VehicleType;

public interface QRCodeGEneratorService {
    String generateQRCode(VehicleType vehicleType, String vehicleNo);
}
