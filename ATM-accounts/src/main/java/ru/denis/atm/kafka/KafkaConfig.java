package ru.denis.atm.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.denis.atm.kafkaevents.EditUserEvent;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @Autowired
    Environment environment;

    @Bean
    public NewTopic newTopic() {
        return new NewTopic("user-events", 2, (short) 2);
    }

    Map<String, Object> producerConfig(){
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty("spring.kafka.bootstrap-servers"));
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return config;
    }

    @Bean
    ProducerFactory<String, EditUserEvent> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    KafkaTemplate<String, EditUserEvent> kafkaTemplate(){
        return new KafkaTemplate<String, EditUserEvent>(producerFactory());
    }
}
