package com.example.mvc.controller;

import com.example.mvc.dto.MacBookCreateDto;
import com.example.mvc.dto.MacBookDto;
import com.example.mvc.repository.data.MacBookFragment;
import com.example.mvc.service.MacBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/macbook")
public class MacBookController {

    private final MacBookService macBookService;

    @PostMapping
    public MacBookDto create(@RequestBody MacBookCreateDto dto) {
        return this.macBookService.createMacBook(dto);
    }

    @GetMapping
    public List<MacBookFragment> findAll() {
        return this.macBookService.findAll();
    }
}
