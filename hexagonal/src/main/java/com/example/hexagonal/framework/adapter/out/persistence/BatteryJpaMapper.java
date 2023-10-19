package com.example.hexagonal.framework.adapter.out.persistence;

import com.example.hexagonal.domain.vo.Battery;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper
@MapperConfig(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BatteryJpaMapper {

    BatteryJpaMapper INSTANCE = Mappers.getMapper(BatteryJpaMapper.class);

    BatteryJpaEntity domainEntityToEntityJpa(Battery domainEntity);
}
