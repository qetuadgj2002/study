package com.example.redis.controller;


import com.example.redis.domain.Item;
import com.example.redis.domain.ItemDto;
import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    Cache<Integer, Item> cache;

    @GetMapping("/hateoas/{id}")
    public EntityModel<Item> findOneById(@PathVariable Integer id) {
        Item item = cache.getIfPresent(id);
        EntityModel<Item> model = EntityModel.of(item);
        model.add(WebMvcLinkBuilder.linkTo(ItemDto.class).slash(item.getId()).withSelfRel());
        // model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ItemController.class).getItem(id)).withSelfRel());
        return model;
    }
}