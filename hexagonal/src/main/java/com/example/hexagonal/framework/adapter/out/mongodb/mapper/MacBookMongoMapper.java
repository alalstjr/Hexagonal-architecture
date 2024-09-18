package com.example.hexagonal.framework.adapter.out.mongodb.mapper;

import com.example.hexagonal.domain.MacBook;
import com.example.hexagonal.framework.adapter.out.mongodb.entity.BatteryMongoEntity;
import com.example.hexagonal.framework.adapter.out.mongodb.entity.MacBookMongoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper
@MapperConfig(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MacBookMongoMapper {

    MacBookMongoMapper INSTANCE = Mappers.getMapper(MacBookMongoMapper.class);

    MacBookMongoEntity domainEntityToEntity(MacBook domainEntity, String batteryId);

    @Mapping(target = "code", source = "macBookMongoEntity.code")
    @Mapping(target = "battery.chargeStatus", source = "batteryMongoEntity.chargeStatus")
    MacBook entityToDomainEntity(MacBookMongoEntity macBookMongoEntity, BatteryMongoEntity batteryMongoEntity);

    @Mapping(target = "battery.chargeStatus", source = "chargeStatus")
    MacBook fragmentToDomainEntity(String name, Boolean chargeStatus);
}
