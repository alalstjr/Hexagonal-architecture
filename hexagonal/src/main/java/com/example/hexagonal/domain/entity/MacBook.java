package com.example.hexagonal.domain.entity;

import com.example.hexagonal.domain.vo.Battery;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MacBook {

    private String name;
    private String code;
    private Battery battery;
}
