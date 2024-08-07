package ru.denis.atm.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.denis.atm.notifications.DelUserNotification;
import ru.denis.atm.notifications.NewUserNotification;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final NewUserNotification newUserNotification;
    private final DelUserNotification delUserNotification;
    @KafkaListener(topics = "users-events", groupId = "notifications-consumer")
    public void listen(ConsumerRecord<String, String> record) {
        switch (record.key()){
            case "new-user-event":
                newUserNotification.SendNotification(record.value());
                break;
            case "del-user-event":
                delUserNotification.SendNotification(record.value());
                break;
        }
    }
}
