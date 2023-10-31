package com.example.hexagonal.framework.adapter.out.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MACBOOK")
public class MacBookJpaEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long batteryId;

    private String name;

    @Column(unique = true)
    private String code;
}
