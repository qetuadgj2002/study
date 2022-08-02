package com.example.redis.domain;

import org.springframework.data.util.Streamable;

import java.util.Iterator;


public class Items implements Streamable<Item> {

    private final Streamable<Item> itemStreamable;

    public Items(Streamable<Item> itemStreamable) {
        this.itemStreamable = itemStreamable;
    }

    public long getTotalNum(){
        long count = 0;
        return itemStreamable.stream().count();
    }



    @Override
    public Iterator<Item> iterator() {
        return itemStreamable.iterator();
    }


}
