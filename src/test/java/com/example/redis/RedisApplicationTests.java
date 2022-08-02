package com.example.redis;

import com.example.redis.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisApplicationTests {

    @Autowired
    ItemService service;

    @Test
    void ArticleTest() {
        System.out.println(service.findOne(10001).getId());
    }


}
