package ru.bz.baltic_shipyard_inventory_service.applications.usecases.warehouse.transfer

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferredItemByLocations
import ru.bz.baltic_shipyard_inventory_service.domain.repository.WarehouseRepository

@Component
class UpdateTransferredItemByLocationsUseCase(
    private val warehouseRepository: WarehouseRepository
) {
    operator fun invoke(transferredItemByLocations: TransferredItemByLocations) =
        warehouseRepository.updateTransferredItemByLocations(transferredItemByLocations)
}