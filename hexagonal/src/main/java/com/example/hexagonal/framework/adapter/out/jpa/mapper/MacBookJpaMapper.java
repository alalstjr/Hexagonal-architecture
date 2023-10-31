package com.example.hexagonal.framework.adapter.out.jpa.mapper;

import com.example.hexagonal.application.port.in.dto.MacBookDto;
import com.example.hexagonal.domain.entity.MacBook;
import com.example.hexagonal.framework.adapter.out.jpa.entity.BatteryJpaEntity;
import com.example.hexagonal.framework.adapter.out.jpa.entity.MacBookJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper
@MapperConfig(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MacBookJpaMapper {

    MacBookJpaMapper INSTANCE = Mappers.getMapper(MacBookJpaMapper.class);

    MacBookDto domainEntityToDto(MacBook domainEntity);

    MacBookJpaEntity domainEntityToEntityJpa(MacBook domainEntity, Long batteryId);

    @Mapping(target = "code", source = "macBookJpaEntity.code")
    @Mapping(target = "battery.chargeStatus", source = "batteryJpaEntity.chargeStatus")
    MacBook jpaEntityToDomainEntity(MacBookJpaEntity macBookJpaEntity, BatteryJpaEntity batteryJpaEntity);

    @Mapping(target = "battery.chargeStatus", source = "chargeStatus")
    MacBook fragmentToDomainEntity(String name, Boolean chargeStatus);
}
