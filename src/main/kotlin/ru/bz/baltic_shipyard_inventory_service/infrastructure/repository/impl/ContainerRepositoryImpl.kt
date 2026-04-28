package ru.bz.baltic_shipyard_inventory_service.infrastructure.repository.impl

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.Container
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.ContainersPagination
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacedContainer
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacementContainer
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchContainerFilter
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.repository.RabbitRepository
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.container.PlacementContainerEntity
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.container.toPlacedContainer
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.container.toPlacementContainerEmbeddable
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.container.toPlacementContainerEntity
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction.TransactionEntity
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.repository.PlacementContainerJpaRepository
import ru.bz.baltic_shipyard_inventory_service.domain.repository.ContainerRepository


@Component
class ContainerRepositoryImpl(
    private val rabbitRepository: RabbitRepository,
    private val containerJpaRepository: PlacementContainerJpaRepository
): ContainerRepository {
    override fun findContainerByCode(code: String): Container? = rabbitRepository.getContainerByCode(code)

    override fun findContainersByFilter(filter: SearchContainerFilter): ContainersPagination? = rabbitRepository.findContainersByFilter(filter)


    override fun placeContainer(placementContainer: PlacementContainer): PlacedContainer =
        containerJpaRepository.save(
            PlacementContainerEntity(
                placementContainer = placementContainer.toPlacementContainerEmbeddable(),
                transaction = TransactionEntity(userLogin = placementContainer.userLogin)
            )
        ).toPlacedContainer().also {placedContainer ->
            rabbitRepository.placeContainer(placedContainer)
        }

    override fun findPlacedContainerByTransactionId(id: Int): PlacedContainer? =
        containerJpaRepository.findByTransactionId(id)?.toPlacedContainer()

    override fun updatePlacedContainer(placedContainer: PlacedContainer): PlacedContainer  =
        placedContainer.toPlacementContainerEntity().let {entity ->
            containerJpaRepository.save(entity)
        }.toPlacedContainer()
}