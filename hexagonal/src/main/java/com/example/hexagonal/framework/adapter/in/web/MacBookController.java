package com.example.hexagonal.framework.adapter.in.web;

import com.example.hexagonal.application.port.in.dto.MacBookCreateDto;
import com.example.hexagonal.application.port.in.dto.MacBookDto;
import com.example.hexagonal.application.usecase.MacBookUseCase;
import com.example.hexagonal.common.WebAdapter;
import com.example.hexagonal.framework.adapter.in.web.validator.ObjectValidator;
import com.example.hexagonal.framework.adapter.in.web.validator.ValidDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/macbook")
@WebAdapter
public class MacBookController {

    private final MacBookUseCase macBookUseCase;

    @ValidDto
    @PostMapping
    public ResponseEntity<MacBookDto> create(@RequestBody MacBookCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.macBookUseCase.createMacBook(dto));
    }

    @GetMapping
    public ResponseEntity<List<MacBookDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.macBookUseCase.findAll());
    }

    @GetMapping("/{macbookId}")
    public ResponseEntity<MacBookDto> findById(@PathVariable("macbookId") String macbookId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.macBookUseCase.findById(macbookId));
    }
}
