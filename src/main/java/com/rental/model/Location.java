package com.rental.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Location {
    String id;
    double x;
    double y;

    public Location(double x, double y) {
        this.id= UUID.randomUUID().toString();
        this.x = x;
        this.y = y;
    }
}
