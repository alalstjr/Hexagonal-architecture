package com.example.hexagonal.framework.adapter.out.mongodb.mapper;

import com.example.hexagonal.domain.Battery;
import com.example.hexagonal.framework.adapter.out.mongodb.entity.BatteryMongoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper
@MapperConfig(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BatteryMongoMapper {

    BatteryMongoMapper INSTANCE = Mappers.getMapper(BatteryMongoMapper.class);

    BatteryMongoEntity domainEntityToEntityJpa(Battery domainEntity);
}
