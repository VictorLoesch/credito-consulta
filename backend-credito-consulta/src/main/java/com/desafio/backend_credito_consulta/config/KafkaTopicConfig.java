package com.desafio.backend_credito_consulta.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Value("${app.kafka.topic}")
    private String topicName;

    @Bean
    public NewTopic auditoriaCreditoTopic() {
        return new NewTopic(topicName, 1, (short) 1);
    }

}
