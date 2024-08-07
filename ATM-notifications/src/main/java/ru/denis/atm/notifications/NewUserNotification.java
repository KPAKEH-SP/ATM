package ru.denis.atm.notifications;

import org.springframework.stereotype.Component;

@Component
public class NewUserNotification {
    public void SendNotification(String message) {
        System.out.println(message + ", welcome to atm!");
    }
}
