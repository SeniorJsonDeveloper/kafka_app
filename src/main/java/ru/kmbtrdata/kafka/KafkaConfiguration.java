package ru.kmbtrdata.kafka;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.Mergeable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaConfiguration {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapService;
    @Value("${app.kafka.kafkaMessageGroupId}")
    private String kafkaMessageGroupId;
    @Bean
    public ProducerFactory<String,KafkaMessage> producerFactory(ObjectMapper objectMapper){
        Map<String,Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapService);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config,new StringSerializer(),new org.springframework.kafka.support.serializer.JsonSerializer<>(objectMapper));
    }
    @Bean
    public KafkaTemplate<String,KafkaMessage> kafkaTemplate(ProducerFactory<String,KafkaMessage> kafkaMessageProducerFactory){
        return new KafkaTemplate<>(kafkaMessageProducerFactory);
    }
    @Bean
    public ConsumerFactory<String,KafkaMessage> kafkaMessageConsumerFactory(ObjectMapper objectMapper){
        Map<String,Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.100.58:9092");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(ConsumerConfig.CLIENT_ID_CONFIG,kafkaMessageGroupId);
        config.put(org.springframework.kafka.support.serializer.JsonDeserializer.TRUSTED_PACKAGES,"*");
        return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer(),new org.springframework.kafka.support.serializer.JsonDeserializer<>(objectMapper));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,KafkaMessage> kafkaListenerContainerFactory(ConsumerFactory<String,KafkaMessage> kafkaMessageConsumerFactory) {
         ConcurrentKafkaListenerContainerFactory<String, KafkaMessage> factoryConfigurer = new ConcurrentKafkaListenerContainerFactory<>();
         factoryConfigurer.setConsumerFactory(kafkaMessageConsumerFactory);
         return factoryConfigurer;
    }






}
