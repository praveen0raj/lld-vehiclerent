package com.rental.strategy;

import com.rental.model.Location;
import com.rental.model.VehicleType;

public interface PriceStrategy {

    double calculatePrice(Location origin, Location dest, VehicleType vehicleType,
                          Integer okm, Integer dkm);
}
