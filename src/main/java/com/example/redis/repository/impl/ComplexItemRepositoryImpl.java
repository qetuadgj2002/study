package com.example.redis.repository.impl;

import com.example.redis.repository.ComplexItemRepository;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.springframework.beans.factory.annotation.Autowired;


public class ComplexItemRepositoryImpl implements ComplexItemRepository {

    @Autowired
    CriteriaBuilderImpl criteriaBuilder;

}
