package com.rental.service;

import com.rental.model.Location;
import com.rental.model.Vehicle;
import com.rental.model.VehicleType;

public interface LocationService {

    Location addLocation(double x, double y, String desc);

    void updateVehicleLocation(Vehicle vehicle, Location location);

    Location getLocation(String id);

    public Vehicle getVehicle(Location location, VehicleType vehicleType);

    public Vehicle bookVehicleIfAvailable(Vehicle vehicle);
}
