package ru.kmbtrdata.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaMessageListener {
    private final KafkaService kafkaService;
    @KafkaListener(topics = "${app.kafka.kafkaMessageTopic}",
    groupId = "${app.kafka.kafkaMessageGroupId}",
    containerFactory = "kafkaListenerContainerFactory")
    public void listen(@Payload KafkaMessage message,
                       @Header(value = KafkaHeaders.RECEIVED_KEY,required = false)UUID key,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.RECEIVED_PARTITION)Integer partition,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timeStamp){
        log.info("Received message: {}",message);
        log.info("Key: {}; Partition: {}; Topic: {}; Timestamp {}",key,partition,topic,timeStamp);
        kafkaService.addMessage(message);
    }
}
