package com.example.hexagonal.framework.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BatteryRepository extends JpaRepository<BatteryJpaEntity, Long> {
}