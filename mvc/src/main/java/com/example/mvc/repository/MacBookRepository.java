package com.example.mvc.repository;

import com.example.mvc.model.MacBookJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MacBookRepository extends JpaRepository<MacBookJpaEntity, Long>, MacBookRepositorySupport {

}