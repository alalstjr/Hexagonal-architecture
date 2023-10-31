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
@Table(name = "BATTERY")
public class BatteryJpaEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String code;

    private Boolean chargeStatus;
}
