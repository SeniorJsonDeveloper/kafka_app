package ru.kmbtrdata.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KafkaService {
    private final List<KafkaMessage> messages = new ArrayList<>();
    public void addMessage(KafkaMessage kafkaMessage){
        messages.add(kafkaMessage);
    }
    public Optional<KafkaMessage> findById(Integer id){
        return messages.stream().filter(it->it.getId().equals(id)).findFirst();
    }
}
