package ru.bz.baltic_shipyard_inventory_service.domain.usecases.warehouse

import org.springframework.stereotype.Service
import ru.bz.baltic_shipyard_inventory_service.domain.repository.WarehouseRepository


@Service
class UseCaseWarehouseExists(
    private val warehouseRepository: WarehouseRepository
) {
    operator fun invoke(warehouseCode: String): Boolean = warehouseRepository.getWarehouseDetail(warehouseCode) != null
}