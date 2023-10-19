package com.example.hexagonal.framework.adapter.out.persistence;

import com.example.hexagonal.application.port.out.MacBookManagementOutPort;
import com.example.hexagonal.common.PersistenceAdapter;
import com.example.hexagonal.domain.entity.MacBook;
import com.example.hexagonal.framework.adapter.out.persistence.entity.BatteryJpaEntity;
import com.example.hexagonal.framework.adapter.out.persistence.mapper.BatteryJpaMapper;
import com.example.hexagonal.framework.adapter.out.persistence.mapper.MacBookJpaMapper;
import com.example.hexagonal.framework.adapter.out.persistence.repository.BatteryRepository;
import com.example.hexagonal.framework.adapter.out.persistence.repository.MacBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@PersistenceAdapter
public class MacBookAdapter implements MacBookManagementOutPort {

    private final MacBookRepository macBookRepository;
    private final BatteryRepository batteryRepository;

    @Override
    public Optional<MacBook> save(MacBook macBook) {
        // 맥북 저장 전 배터리 생성 후 맥북에 장착
        BatteryJpaEntity batteryJpaEntity = this.batteryRepository.save(
                BatteryJpaMapper.INSTANCE.domainEntityToEntityJpa(macBook.getBattery())
        );
        return Optional.ofNullable(
                MacBookJpaMapper.INSTANCE.jpaEntityToDomainEntity(
                        this.macBookRepository.save(MacBookJpaMapper.INSTANCE.domainEntityToEntityJpa(macBook, batteryJpaEntity.getId())),
                        batteryJpaEntity
                )
        );
    }

    @Override
    public List<MacBook> findAll() {
        return this.macBookRepository.findAllMacBook()
                .stream()
                .map(v -> MacBookJpaMapper.INSTANCE.fragmentToDomainEntity(v.macbookName(), v.chargeStatus()))
                .toList();
    }
}
