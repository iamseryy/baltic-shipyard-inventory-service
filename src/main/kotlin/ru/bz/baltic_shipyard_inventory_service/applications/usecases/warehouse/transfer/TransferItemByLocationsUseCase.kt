package ru.bz.baltic_shipyard_inventory_service.applications.usecases.warehouse.transfer

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferItemByLocations
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferredItemByLocations
import ru.bz.baltic_shipyard_inventory_service.domain.repository.WarehouseRepository

@Component
class TransferItemByLocationsUseCase(
    private val warehouseRepository: WarehouseRepository
) {
    operator fun invoke(transferItemByLocations: TransferItemByLocations): TransferredItemByLocations =
        warehouseRepository.transferItemByLocations(transferItemByLocations)
}