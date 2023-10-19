package com.example.hexagonal.framework.adapter.out.persistence.repository;

import com.example.hexagonal.framework.adapter.out.persistence.data.MacBookFragment;
import com.example.hexagonal.framework.adapter.out.persistence.entity.QBatteryJpaEntity;
import com.example.hexagonal.framework.adapter.out.persistence.entity.QMacBookJpaEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MacBookRepositorySupportImpl implements MacBookRepositorySupport {

    private final JPAQueryFactory queryFactory;
    private final QMacBookJpaEntity qMacBookJpa;
    private final QBatteryJpaEntity qBatteryJpa;

    public MacBookRepositorySupportImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
        this.qMacBookJpa = QMacBookJpaEntity.macBookJpaEntity;
        this.qBatteryJpa = QBatteryJpaEntity.batteryJpaEntity;
    }

    @Override
    public List<MacBookFragment> findAllMacBook() {
        return queryFactory
                .from(qMacBookJpa)
                .join(qBatteryJpa)
                .on(qMacBookJpa.batteryId.eq(qBatteryJpa.id))
                .select(Projections.constructor(
                        MacBookFragment.class,
                        qMacBookJpa.name,
                        qBatteryJpa.chargeStatus
                ))
                .fetch()
                ;
    }
}
