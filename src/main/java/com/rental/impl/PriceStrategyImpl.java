package com.rental.impl;

import com.rental.model.Location;
import com.rental.model.VehicleType;
import com.rental.model.constants.PriceConstants;
import com.rental.service.PriceStrategy;

public class PriceStrategyImpl implements PriceStrategy {

    @Override
    public double calculatePrice(Location origin, Location dest, VehicleType vehicleType,
                                 Integer okm, Integer dkm) {
        return (dkm-okm) * PriceConstants.PRICE_PER_KM;
    }
}
