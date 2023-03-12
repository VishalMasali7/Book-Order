package com.example.demo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entity.order;

public interface OrderRepository extends MongoRepository<order, Integer> {
}
