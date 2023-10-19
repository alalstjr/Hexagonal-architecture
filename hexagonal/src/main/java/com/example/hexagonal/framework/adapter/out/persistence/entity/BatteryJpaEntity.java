package com.example.hexagonal.framework.adapter.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    private String code;

    private Boolean chargeStatus;
}
