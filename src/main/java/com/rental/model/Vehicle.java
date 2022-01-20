package com.rental.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class Vehicle {
    private String id;
    private VehicleType vehicleType;
    private String vehicleNo;
    private String qrCode;
    private Location currentLocation;
    private VehicleReservationStatus vehicleReservationStatus;
    private Integer km;

    public Vehicle(VehicleType vehicleType, String vehicleNo) {
        this.id = UUID.randomUUID().toString();
        this.vehicleNo=vehicleNo;
        this.vehicleType = vehicleType;
        this.vehicleReservationStatus=VehicleReservationStatus.CREATED;
        this.km=0;
    }
}
