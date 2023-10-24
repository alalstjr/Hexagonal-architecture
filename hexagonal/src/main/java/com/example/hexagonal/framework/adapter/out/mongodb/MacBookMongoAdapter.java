package com.example.hexagonal.framework.adapter.out.mongodb;

import com.example.hexagonal.application.port.out.MacBookManagementOutPort;
import com.example.hexagonal.common.PersistenceAdapter;
import com.example.hexagonal.domain.entity.MacBook;
import com.example.hexagonal.framework.adapter.out.mongodb.entity.BatteryMongoEntity;
import com.example.hexagonal.framework.adapter.out.mongodb.mapper.BatteryMongoMapper;
import com.example.hexagonal.framework.adapter.out.mongodb.mapper.MacBookMongoMapper;
import com.example.hexagonal.framework.adapter.out.mongodb.repository.BatteryMongoRepository;
import com.example.hexagonal.framework.adapter.out.mongodb.repository.MacBookMongoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
//@Primary
@RequiredArgsConstructor
@PersistenceAdapter
public class MacBookMongoAdapter implements MacBookManagementOutPort {

    private final MacBookMongoRepository macBookMongoRepository;
    private final BatteryMongoRepository batteryMongoRepository;

    @Override
    public Optional<MacBook> save(MacBook macBook) {
        // 맥북 저장 전 배터리 생성 후 맥북에 장착
        BatteryMongoEntity batteryMongoEntity = this.batteryMongoRepository.save(
                BatteryMongoMapper.INSTANCE.domainEntityToEntityJpa(macBook.getBattery())
        );
        return Optional.ofNullable(
                MacBookMongoMapper.INSTANCE.entityToDomainEntity(
                        this.macBookMongoRepository.save(MacBookMongoMapper.INSTANCE.domainEntityToEntity(macBook, batteryMongoEntity.getId())),
                        batteryMongoEntity
                )
        );
    }

    @Override
    public List<MacBook> findAll() {
        return this.macBookMongoRepository.findAll()
                .stream()
                .map(v -> this.batteryMongoRepository.findById(v.getBatteryId())
                        .flatMap(battery -> Optional.of(MacBookMongoMapper.INSTANCE.fragmentToDomainEntity(v.getName(), battery.getChargeStatus())))
                )
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
