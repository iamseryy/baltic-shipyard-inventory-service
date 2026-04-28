package ru.bz.baltic_shipyard_inventory_service.domain.usecases.inventory

import org.springframework.stereotype.Component


@Component
data class InventoryUseCases(
    val findInventoryBalanceByFilter: UseCaseFindInventoryBalanceByFilter
)