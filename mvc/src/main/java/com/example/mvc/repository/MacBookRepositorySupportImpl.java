package com.example.mvc.repository;

import com.example.mvc.model.QBatteryJpaEntity;
import com.example.mvc.model.QMacBookJpaEntity;
import com.example.mvc.repository.data.MacBookFragment;
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
