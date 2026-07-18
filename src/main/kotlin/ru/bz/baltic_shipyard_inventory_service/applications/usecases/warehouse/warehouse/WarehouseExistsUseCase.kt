package ru.bz.baltic_shipyard_inventory_service.applications.usecases.warehouse.warehouse

import org.springframework.stereotype.Service
import ru.bz.baltic_shipyard_inventory_service.domain.repository.WarehouseRepository

@Service
class WarehouseExistsUseCase(
    private val warehouseRepository: WarehouseRepository
) {
    operator fun invoke(warehouseCode: String): Boolean = warehouseRepository.getWarehouseDetail(warehouseCode) != null
}