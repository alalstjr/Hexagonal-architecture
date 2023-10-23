package com.example.hexagonal.framework.adapter.out.mongodb.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "BATTERY")
public class BatteryMongoEntity {

    @Id
    private String id;

    private String code;

    private Boolean chargeStatus;
}
