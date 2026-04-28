package ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transfer.TransferItemByLocationsEntity


interface TransferItemByLocationsJpaRepository:
    JpaRepository<TransferItemByLocationsEntity, Long>,
    JpaSpecificationExecutor<TransferItemByLocationsEntity> {

        fun findByTransactionId(id: Int): TransferItemByLocationsEntity?
}