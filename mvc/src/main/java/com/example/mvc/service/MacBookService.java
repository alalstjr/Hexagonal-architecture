package com.example.mvc.service;

import com.example.mvc.dto.MacBookCreateDto;
import com.example.mvc.dto.MacBookDto;
import com.example.mvc.exception.CommonServiceException;
import com.example.mvc.mapper.MacBookMapper;
import com.example.mvc.model.BatteryJpaEntity;
import com.example.mvc.model.MacBookJpaEntity;
import com.example.mvc.repository.BatteryRepository;
import com.example.mvc.repository.MacBookRepository;
import com.example.mvc.repository.data.MacBookFragment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MacBookService {

    private final MacBookRepository macBookRepository;
    private final BatteryRepository batteryRepository;

    public MacBookDto createMacBook(MacBookCreateDto macBookCreateDto) {
        try {
            // 맥북을 생산하기 전 베터리를 장착해야합니다.
            BatteryJpaEntity battery = BatteryJpaEntity.builder().code("AAA111").chargeStatus(true).build();
            this.batteryRepository.save(battery);

            MacBookJpaEntity macBookJpaEntity = MacBookMapper.INSTANCE.dtoToDomainEntity(macBookCreateDto, battery.getId());

            return MacBookMapper.INSTANCE.entityToDomainEntity(
                    this.macBookRepository.save(macBookJpaEntity),
                    battery
            );
        } catch (Exception e) {
            throw new CommonServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "MacBook 을 생성할 수 없습니다.");
        }
    }

    public List<MacBookFragment> findAll() {
        return this.macBookRepository.findAllMacBook();
    }
}
