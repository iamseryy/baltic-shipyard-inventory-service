package ru.bz.baltic_shipyard_inventory_service.domain.model.transfer

import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.Transaction
import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.AbortReason

data class TransferredItemByLocations(
    val id: Int,
    override val warehouseCode: String,
    override val binCodeSource: String,
    override val binCodeTarget: String,
    override val itemCode: String,
    override val lotCode: String,
    override val quantity: Double,
    val transaction: Transaction,
    val abortReason: AbortReason?
): TransferItemByLocationsBase
