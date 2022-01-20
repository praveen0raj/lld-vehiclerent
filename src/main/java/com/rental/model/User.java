package com.rental.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class User {
    private String id;
    private String name;

    public User(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}
