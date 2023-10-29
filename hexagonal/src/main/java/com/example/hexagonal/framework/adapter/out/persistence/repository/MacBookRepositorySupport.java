package com.example.hexagonal.framework.adapter.out.persistence.repository;

import com.example.hexagonal.framework.adapter.out.persistence.data.MacBookFragment;

import java.util.List;
import java.util.Optional;

public interface MacBookRepositorySupport {

    List<MacBookFragment> findAllMacBook();

    Optional<MacBookFragment> findByIdMacBook(Long id);
}
