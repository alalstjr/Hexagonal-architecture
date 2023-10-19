package com.example.hexagonal.framework.adapter.in.web;

import com.example.hexagonal.application.usecase.MacBookUseCase;
import com.example.hexagonal.common.WebAdapter;
import com.example.hexagonal.application.port.in.dto.MacBookCreateDto;
import com.example.hexagonal.application.port.in.dto.MacBookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/macbook")
@WebAdapter
public class MacBookController {

    private final MacBookUseCase macBookUseCase;

    @PostMapping
    public MacBookDto create(@RequestBody MacBookCreateDto dto) {
        return this.macBookUseCase.createMacBook(dto);
    }

    @GetMapping
    public List<MacBookDto> findAll() {
        return this.macBookUseCase.findAll();
    }
}
