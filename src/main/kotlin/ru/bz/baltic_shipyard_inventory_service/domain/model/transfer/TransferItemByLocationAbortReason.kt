package ru.bz.baltic_shipyard_inventory_service.domain.model.transfer

import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.AbortReason

data class TransferItemByLocationAbortReason(
    override val warehouseCode: String,
    override val binCodeSource: String,
    override val binCodeTarget: String,
    override val itemCode: String,
    override val lotCode: String,
    override val quantity: Double,
    val abortReason: AbortReason?
): TransferItemByLocationsBase

fun TransferItemByLocations.toTransferItemByLocationAbortReason(abortReason: AbortReason?) = TransferItemByLocationAbortReason (
    warehouseCode = warehouseCode,
    binCodeSource = binCodeSource,
    binCodeTarget = binCodeTarget,
    itemCode = itemCode,
    lotCode = lotCode,
    quantity = quantity,
    abortReason = abortReason
)
