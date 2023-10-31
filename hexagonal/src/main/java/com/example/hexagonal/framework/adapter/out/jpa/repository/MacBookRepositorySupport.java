package com.example.hexagonal.framework.adapter.out.jpa.repository;

import com.example.hexagonal.framework.adapter.out.jpa.data.MacBookFragment;

import java.util.List;
import java.util.Optional;

public interface MacBookRepositorySupport {

    List<MacBookFragment> findAllMacBook();

    Optional<MacBookFragment> findByIdMacBook(Long id);

    Optional<MacBookFragment> findByCode(String code);
}
