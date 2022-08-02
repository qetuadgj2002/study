package com.example.redis.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class ItemDto implements Serializable {
    private final Integer id;
    private final String name;
}
