package ru.bz.baltic_shipyard_inventory_service.domain.usecases.container

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacedContainer
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacementContainer
import ru.bz.baltic_shipyard_inventory_service.domain.repository.ContainerRepository


@Component
class UseCasePlaceContainer(
    private val containerRepository: ContainerRepository
) {
    operator fun invoke(placementContainer: PlacementContainer): PlacedContainer =
        containerRepository.placeContainer(placementContainer)
}