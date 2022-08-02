package com.example.redis.repository;

import com.example.redis.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


// 可不添加注解
@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
    Item findItemByName(String name);

    @Query("select i from #{#entityName} i where i.id=?1 ")
    Item findOneBySQL(Integer id);
}
