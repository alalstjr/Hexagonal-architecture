package com.example.hexagonal.application.usecase;

import com.example.hexagonal.application.port.in.dto.MacBookCreateDto;
import com.example.hexagonal.application.port.in.dto.MacBookDto;

import java.util.List;

public interface MacBookUseCase {

    MacBookDto createMacBook(MacBookCreateDto macBookCreateDto);

    List<MacBookDto> findAll();

    MacBookDto findById(Object id);

    MacBookDto delete(Object id);
}
