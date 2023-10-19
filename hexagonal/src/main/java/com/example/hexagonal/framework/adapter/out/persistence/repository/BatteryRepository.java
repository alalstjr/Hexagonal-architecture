package com.example.hexagonal.framework.adapter.out.persistence.repository;

import com.example.hexagonal.framework.adapter.out.persistence.entity.BatteryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatteryRepository extends JpaRepository<BatteryJpaEntity, Long> {
}