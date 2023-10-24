package com.example.hexagonal.application.port.in.mapper;

import com.example.hexagonal.application.port.in.dto.MacBookCreateDto;
import com.example.hexagonal.application.port.in.dto.MacBookDto;
import com.example.hexagonal.domain.entity.MacBook;
import com.example.hexagonal.domain.vo.Battery;
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
    MacBook dtoToDomainEntity(MacBookCreateDto macBookCreateDto, Battery battery);

    MacBookDto domainEntityToDto(MacBook domainEntity);
}
