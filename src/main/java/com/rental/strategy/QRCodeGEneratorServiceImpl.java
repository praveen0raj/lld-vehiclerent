package com.rental.strategy;

import com.rental.model.VehicleType;
import org.springframework.stereotype.Service;

@Service
public class QRCodeGEneratorServiceImpl implements QRCodeGEneratorService {

    @Override
    public String generateQRCode(VehicleType vehicleType, String vehicleNo) {
        return null;
    }
}
