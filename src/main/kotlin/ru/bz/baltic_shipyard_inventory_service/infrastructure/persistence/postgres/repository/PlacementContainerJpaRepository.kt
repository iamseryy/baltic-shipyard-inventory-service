package ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.container.PlacementContainerEntity

interface PlacementContainerJpaRepository:
    JpaRepository<PlacementContainerEntity, Long>,
    JpaSpecificationExecutor<PlacementContainerEntity> {

    fun findByTransactionId(id: Int): PlacementContainerEntity?
}