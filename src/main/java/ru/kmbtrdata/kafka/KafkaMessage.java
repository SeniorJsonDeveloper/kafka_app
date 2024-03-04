package ru.kmbtrdata.kafka;

import lombok.Data;

@Data
public class KafkaMessage {
    private Integer id;
    private String message;
}
