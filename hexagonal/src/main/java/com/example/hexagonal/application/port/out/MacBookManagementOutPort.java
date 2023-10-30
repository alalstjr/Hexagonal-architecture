package com.example.hexagonal.application.port.out;

import com.example.hexagonal.domain.entity.MacBook;

import java.util.List;
import java.util.Optional;

public interface MacBookManagementOutPort {

    Optional<MacBook> save(MacBook macBook);

    List<MacBook> findAll();

    Optional<MacBook> findById(String id);

    Optional<MacBook> findByCode(String code);
}
