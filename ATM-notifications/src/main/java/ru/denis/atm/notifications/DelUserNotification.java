package ru.denis.atm.notifications;

import org.springframework.stereotype.Component;

@Component
public class DelUserNotification {
    public void SendNotification(String message) {
        System.out.println(message + ", goodbye");
    }
}
