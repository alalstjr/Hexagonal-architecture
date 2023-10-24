package com.example.mvc.mapper;

import com.example.mvc.dto.MacBookCreateDto;
import com.example.mvc.dto.MacBookDto;
import com.example.mvc.model.BatteryJpaEntity;
import com.example.mvc.model.MacBookJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper
@MapperConfig(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MacBookMapper {

    // 매퍼 팩토리(종속성 주입 없이 사용하도록 설정)
    MacBookMapper INSTANCE = Mappers.getMapper(MacBookMapper.class);

    @Mapping(source = "macBookCreateDto.code", target = "code")
    MacBookJpaEntity dtoToDomainEntity(MacBookCreateDto macBookCreateDto, Long batteryId);

    @Mapping(target = "battery.chargeStatus", source = "batteryJpaEntity.chargeStatus")
    MacBookDto entityToDomainEntity(MacBookJpaEntity macBookJpaEntity, BatteryJpaEntity batteryJpaEntity);
}
