package com.example.hexagonal.framework.adapter.out.persistence.repository;

import com.example.hexagonal.framework.adapter.out.persistence.entity.MacBookJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MacBookRepository extends JpaRepository<MacBookJpaEntity, Long>, MacBookRepositorySupport {

}