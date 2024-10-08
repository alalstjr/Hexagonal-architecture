package com.example.hexagonal.application.port.out;

import com.example.hexagonal.domain.MacBook;

import java.util.List;
import java.util.Optional;

public interface MacBookManagementOutPort {

    Optional<MacBook> save(MacBook macBook);

    List<MacBook> findAll();

    Optional<MacBook> findById(Object id);

    Optional<MacBook> findByCode(String code);
}
