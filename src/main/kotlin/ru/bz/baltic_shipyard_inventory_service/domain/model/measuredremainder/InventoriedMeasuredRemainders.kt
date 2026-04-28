package ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder

import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.Transaction


data class InventoriedMeasuredRemainders(
    val transaction: Transaction,
    val measuredRemainders: List<InventoriedMeasuredRemainder>
)
