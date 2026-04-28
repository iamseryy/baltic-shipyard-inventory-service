package ru.bz.baltic_shipyard_inventory_service.domain.usecases.warehouse

import org.springframework.stereotype.Service
import ru.bz.baltic_shipyard_inventory_service.domain.model.warehouse.Warehouse
import ru.bz.baltic_shipyard_inventory_service.domain.repository.WarehouseRepository


@Service
class UseCaseGetWarehouseDetailByWarehouseCode(
    private val warehouseRepository: WarehouseRepository
) {
    operator fun invoke(warehouseCode: String): Warehouse? = warehouseRepository.getWarehouseDetail(warehouseCode)
}