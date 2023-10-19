package com.example.hexagonal.framework.adapter.out.persistence;

import com.example.hexagonal.application.port.in.dto.MacBookDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MacBookRepository extends JpaRepository<MacBookJpaEntity, Long> {

    @Query(value = "SELECT b.name AS batteryName, m.name AS macbookName " +
            "FROM BATTERY b " +
            "JOIN MACBOOK m ON b.id = m.batteryId", nativeQuery = true)
    List<MacBookDto> findAllMac();
}