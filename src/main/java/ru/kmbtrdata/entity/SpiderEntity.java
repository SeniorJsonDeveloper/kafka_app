package ru.kmbtrdata.entity;

import jakarta.persistence.Id;
import org.springframework.data.tarantool.core.mapping.Tuple;

@Tuple
public class SpiderEntity {
    @Id
    public Integer id;
}
