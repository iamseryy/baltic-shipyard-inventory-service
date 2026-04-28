package ru.bz.baltic_shipyard_inventory_service.domain.usecases.container

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacedContainer
import ru.bz.baltic_shipyard_inventory_service.domain.repository.ContainerRepository


@Component
class UseCaseUpdatePlacedContainer(
    private val containerRepository: ContainerRepository
) {
    operator fun invoke(placedContainer: PlacedContainer) = containerRepository.updatePlacedContainer(placedContainer)
}