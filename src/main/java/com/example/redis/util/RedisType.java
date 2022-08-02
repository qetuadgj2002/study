package com.example.redis.util;

public enum RedisType {
    STRING("string"),
    SET("set"),
    LIST("list"),
    HASH("hash"),
    SORTED_SET("zset");

    private String value;

    RedisType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
