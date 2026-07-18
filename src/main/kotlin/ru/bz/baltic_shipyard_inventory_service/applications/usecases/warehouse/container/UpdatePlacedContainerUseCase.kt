package ru.bz.baltic_shipyard_inventory_service.applications.usecases.warehouse.container

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacedContainer
import ru.bz.baltic_shipyard_inventory_service.domain.repository.WarehouseRepository

@Component
class UpdatePlacedContainerUseCase(
    private val containerRepository: WarehouseRepository
) {
    operator fun invoke(placedContainer: PlacedContainer) = containerRepository.updatePlacedContainer(placedContainer)
}