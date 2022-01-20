package com.rental.service;

import com.rental.model.User;

public interface UserService {

    User createUser(String name);

    User getUser(String id);

}
