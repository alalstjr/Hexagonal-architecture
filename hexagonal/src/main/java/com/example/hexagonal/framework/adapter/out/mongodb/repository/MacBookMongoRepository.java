package com.example.hexagonal.framework.adapter.out.mongodb.repository;

import com.example.hexagonal.framework.adapter.out.mongodb.entity.MacBookMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MacBookMongoRepository extends MongoRepository<MacBookMongoEntity, String> {

    Optional<MacBookMongoEntity> findByCode(String code);
}