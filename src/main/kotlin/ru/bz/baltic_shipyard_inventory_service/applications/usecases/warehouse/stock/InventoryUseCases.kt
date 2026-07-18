package ru.bz.baltic_shipyard_inventory_service.applications.usecases.warehouse.stock

import org.springframework.stereotype.Component

@Component
data class InventoryUseCases(
    val findInventoryBalanceByFilter: FindInventoryBalanceByFilterUseCase
)