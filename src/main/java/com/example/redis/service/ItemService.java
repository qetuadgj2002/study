package com.example.redis.service;

import com.example.redis.domain.Item;
import com.example.redis.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    public Item findItemById(Integer id) {
        return itemRepository.findById(id).orElse(null);
    }

    public List<Item> findALlWithAscend() {
        Sort sort = Sort.sort(Item.class).by(Item::getId).ascending();
        return itemRepository.findAll(sort);
    }

    public List<Item> findALlWithDescend() {
        Sort sort = Sort.sort(Item.class).by(Item::getId).descending();
        return itemRepository.findAll(sort);
    }

    public List<Item> findConcreteItems(int page,int size) {
        Sort sort = Sort.sort(Item.class).ascending();
        Pageable pageable = PageRequest.of(page, size,sort).toOptional().orElseThrow();
        return itemRepository.findAll(pageable).getContent();
    }

    public Item findOne(int id) {
        return itemRepository.findOneBySQL(id);
    }
}
