package com.rental.strategy;

import com.rental.constants.PriceConstants;
import com.rental.model.Location;
import com.rental.model.VehicleType;
import org.springframework.stereotype.Service;

@Service
public class PriceStrategyImpl implements PriceStrategy {

    @Override
    public double calculatePrice(Location origin, Location dest, VehicleType vehicleType,
                                 Integer okm, Integer dkm) {
        return (dkm-okm) * PriceConstants.PRICE_PER_KM;
    }
}
