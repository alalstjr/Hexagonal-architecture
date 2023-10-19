package com.example.hexagonal.application.port.in;

import com.example.hexagonal.application.exception.CommonServiceException;
import com.example.hexagonal.application.port.in.dto.MacBookCreateDto;
import com.example.hexagonal.application.port.in.dto.MacBookDto;
import com.example.hexagonal.application.port.out.MacBookManagementOutPort;
import com.example.hexagonal.application.usecase.MacBookUseCase;
import com.example.hexagonal.domain.vo.Battery;
import com.example.hexagonal.framework.adapter.out.persistence.mapper.MacBookJpaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MacBookManagementInPort implements MacBookUseCase {

    private final MacBookManagementOutPort macBookManagementOutPort;

    @Override
    public MacBookDto createMacBook(MacBookCreateDto macBookCreateDto) {
        // 맥북을 생산하기 전 베터리를 장착해야합니다.
        Battery battery = Battery.builder().code("AAA111").chargeStatus(true).build();

        return this.macBookManagementOutPort.save(
                        MacBookMapper.INSTANCE.dtoToDomainEntity(macBookCreateDto, battery)
                )
                .map(MacBookJpaMapper.INSTANCE::domainEntityToDto)
                .orElseThrow(() -> new CommonServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "MacBook 을 생성할 수 없습니다."));
    }

    @Override
    public List<MacBookDto> findAll() {
        return this.macBookManagementOutPort.findAll()
                .stream()
                .map(MacBookMapper.INSTANCE::domainEntityToDto)
                .toList();
    }
}
