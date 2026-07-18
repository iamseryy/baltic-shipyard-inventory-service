package ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder

import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.Transaction
import ru.bz.baltic_shipyard_inventory_service.common.errors.abortreason.AbortReason
//TODO: rename id, code, remainder
data class UpdatableMeasuredRemainder(
    val id: Int,
    val code: String,
    val remainder: String,
    val project: String,
    val material: String,
    val warehouse: String,
    val location: String,
    val sequence: Int,
    val status: MeasuredRemainderStatus,
    val comment: String,
    val length: Double,
    val width: Double,
    val depth: Double,
    val transaction: Transaction,
    val abortReason: AbortReason?
)

