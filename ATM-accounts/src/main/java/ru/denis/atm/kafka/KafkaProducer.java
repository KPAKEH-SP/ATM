package ru.denis.atm.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.denis.atm.configs.UserEventsConfig;
import ru.denis.atm.kafkaevents.EditUserEvent;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, EditUserEvent> kafkaTemplate;
    private final UserEventsConfig userEventsConfig;

    public void sendEditUserEvent(String key, EditUserEvent event) {
        kafkaTemplate.send(userEventsConfig.getTopicName(), key, event);
    }
}
