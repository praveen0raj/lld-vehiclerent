package com.rental.impl;

import com.rental.model.User;
import com.rental.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {

    Map<String,User> userMap = new HashMap<>();

    @Override
    public User createUser(String name) {
        User user = new User(name);
        userMap.put(user.getId(),user);
        return null;
    }

    @Override
    public User getUser(String id) {
        return userMap.get(id);
    }

}
