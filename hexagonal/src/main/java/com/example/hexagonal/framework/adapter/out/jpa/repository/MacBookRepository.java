package com.example.hexagonal.framework.adapter.out.jpa.repository;

import com.example.hexagonal.framework.adapter.out.jpa.entity.MacBookJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MacBookRepository extends JpaRepository<MacBookJpaEntity, Long>, MacBookRepositorySupport {

}