package com.rental.impl;

import com.rental.model.Location;
import com.rental.model.Vehicle;
import com.rental.model.VehicleReservationStatus;
import com.rental.model.VehicleType;
import com.rental.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LocationServiceImpl implements LocationService{

    Map<String, Location> locations=new HashMap<>();

    Map<Location, Map<VehicleType, List<Vehicle>> > available  = new HashMap<>();

    Map<Location, Map<VehicleType, List<Vehicle>> > unAvailable  = new HashMap<>();

    @Override
    public Location addLocation(double x, double y, String desc) {
        Location location = new Location(x,y);
        locations.put(location.getId(),location);
        return location;
    }

    @Override
    public void updateVehicleLocation(Vehicle vehicle, Location location) {
        vehicle.setCurrentLocation(location);
        available.get(location).get(vehicle.getVehicleType()).add(vehicle);
    }

    @Override
    public Location getLocation(String id) {
        return locations.get(id);
    }


    public Vehicle getVehicle(Location location, VehicleType vehicleType){
        Vehicle vehicle =null;
        synchronized(vehicleType){
            Map<VehicleType, List<Vehicle>> vehicleTypes = available.get(location);
            List<Vehicle> vehicles = vehicleTypes.get(vehicleType);
            if(vehicles.size()>0){
                vehicle = vehicles.get(0);
                vehicles.remove(0);
            }
            vehicle.setVehicleReservationStatus(VehicleReservationStatus.UNAVAILABLE);
            unAvailable.get(location).get(vehicleType).add(vehicle);
        }

        return vehicle;
    }

    @Override
    public Vehicle bookVehicleIfAvailable(Vehicle vehicle) {
        if(vehicle.getVehicleReservationStatus().equals(VehicleReservationStatus.AVAILABLE)){
            synchronized(vehicle){
                Map<VehicleType, List<Vehicle>> vehicleTypes = available.get(vehicle.getCurrentLocation());
                List<Vehicle> vehicles = vehicleTypes.get(vehicle.getVehicleType());
                vehicles.remove(vehicle);
                vehicle.setVehicleReservationStatus(VehicleReservationStatus.UNAVAILABLE);
                unAvailable.get(vehicle.getCurrentLocation()).get(vehicle.getVehicleType()).add(vehicle);
            }

        }

        return vehicle;
    }
}
