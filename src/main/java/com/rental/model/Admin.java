package com.rental.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Admin {
    private String id;
    private String name;

    public Admin(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}
