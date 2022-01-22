package com.rental.impl;

import com.rental.model.Admin;
import com.rental.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    Map<String, Admin> map = new HashMap<>();

    @Override
    public Admin createAdmin(String name) {
        Admin admin = new Admin(name);
        map.put(admin.getId(),admin);
        return admin;
    }
}
