package com.example.hexagonal.framework.adapter.out.jpa.repository;

import com.example.hexagonal.framework.adapter.out.jpa.entity.BatteryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatteryRepository extends JpaRepository<BatteryJpaEntity, Long> {
}