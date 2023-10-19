package com.example.hexagonal.framework.adapter.out.persistence;

import com.example.hexagonal.application.port.out.MacBookManagementOutPort;
import com.example.hexagonal.common.PersistenceAdapter;
import com.example.hexagonal.domain.entity.MacBook;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Repository
@PersistenceAdapter
public class MacBookAdapter implements MacBookManagementOutPort {

    private final MacBookRepository macBookRepository;
    private final BatteryRepository batteryRepository;

    @Override
    public Optional<MacBook> save(MacBook macBook) {
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
        return this.macBookRepository.findAllMac()
                .stream()
                .map(MacBookJpaMapper.INSTANCE::jpaEntityToDomainEntitys)
                .toList();
    }
}
