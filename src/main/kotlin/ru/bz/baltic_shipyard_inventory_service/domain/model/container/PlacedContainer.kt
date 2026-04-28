package ru.bz.baltic_shipyard_inventory_service.domain.model.container

import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.Transaction
import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.AbortReason


data class PlacedContainer(
    val id: Int,
    val containerCode: String,
    val binCodeTarget: String,
    val transaction: Transaction,
    val abortReason: AbortReason?
)