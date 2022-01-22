package com.rental.service;

import com.rental.model.Booking;
import com.rental.model.User;

public interface NotificationService {

    void sendNotification(User user, Booking booking);

}
