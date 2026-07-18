package ru.bz.baltic_shipyard_inventory_service.applications.usecases.warehouse.container

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacedContainer
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacementContainer
import ru.bz.baltic_shipyard_inventory_service.domain.repository.WarehouseRepository

@Component
class PlaceContainerUseCase(
    private val containerRepository: WarehouseRepository
) {
    operator fun invoke(placementContainer: PlacementContainer): PlacedContainer =
        containerRepository.placeContainer(placementContainer)
}