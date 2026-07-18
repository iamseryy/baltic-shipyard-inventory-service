package ru.bz.baltic_shipyard_inventory_service.applications.usecases.warehouse.warehouse

import org.springframework.stereotype.Service

@Service
data class WarehouseUseCases(
    val getWarehouseDetailByWarehouseCode: GetWarehouseDetailByWarehouseCodeUseCase,
    val warehouseExists: WarehouseExistsUseCase,
    val getLocationDetailByFilter: GetLocationDetailByFilterUseCase,
    val binOfWarehouseExists: BinOfWarehouseExistsUseCase,
)