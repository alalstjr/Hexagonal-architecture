package com.example.hexagonal.framework.adapter.out.persistence;

import com.example.hexagonal.application.port.in.dto.MacBookDto;
import com.example.hexagonal.domain.entity.MacBook;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper
@MapperConfig(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MacBookJpaMapper {

    // 매퍼 팩토리(종속성 주입 없이 사용하도록 설정)
    MacBookJpaMapper INSTANCE = Mappers.getMapper(MacBookJpaMapper.class);

    MacBookDto domainEntityToDto(MacBook domainEntity);

    MacBookJpaEntity domainEntityToEntityJpa(MacBook domainEntity, Long batteryId);

    @Mapping(source = "macBookJpaEntity.code", target = "code")
    MacBook jpaEntityToDomainEntity(MacBookJpaEntity macBookJpaEntity, BatteryJpaEntity batteryJpaEntity);

    MacBook jpaEntityToDomainEntitys(MacBookDto macBookDto);
}
