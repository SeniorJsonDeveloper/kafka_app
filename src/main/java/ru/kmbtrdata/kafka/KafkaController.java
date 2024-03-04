package ru.kmbtrdata.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/kafka")
public class KafkaController {
    private final KafkaService kafkaService;
    @Value("${app.kafka.kafkaMessageTopic}")
    private String topicName;
    private final KafkaTemplate<String,KafkaMessage> kafkaMessageTemplate;
    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody KafkaMessage message){
        kafkaMessageTemplate.send(topicName,message);
        return ResponseEntity.ok("Message sent to kafka!");
    }
    @GetMapping("{id}")
    public ResponseEntity<KafkaMessage> getMessageById(@PathVariable Integer id){
        return ResponseEntity.ok(
                kafkaService.findById(id)
                        .orElseThrow(()->new RuntimeException("Message not found"))
        );
    }
}
