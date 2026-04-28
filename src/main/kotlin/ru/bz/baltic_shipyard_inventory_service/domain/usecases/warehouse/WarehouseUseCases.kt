package ru.bz.baltic_shipyard_inventory_service.domain.usecases.warehouse

import org.springframework.stereotype.Service


@Service
data class WarehouseUseCases(
    val getWarehouseDetailByWarehouseCode: UseCaseGetWarehouseDetailByWarehouseCode,
    val warehouseExists: UseCaseWarehouseExists,
    val getLocationDetailByFilter: UseCaseGetLocationDetailByFilter,
    val binOfWarehouseExists: UseCaseBinOfWarehouseExists,
)
