package com.example.hexagonal.framework.adapter.out.mongodb.repository;

import com.example.hexagonal.framework.adapter.out.mongodb.entity.BatteryMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BatteryMongoRepository extends MongoRepository<BatteryMongoEntity, String> {

}