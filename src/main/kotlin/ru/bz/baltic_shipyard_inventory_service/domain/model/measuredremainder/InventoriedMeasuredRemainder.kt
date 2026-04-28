package ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder

import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.AbortReason


data class InventoriedMeasuredRemainder(
    val id: Int,
    override val measuredRemainderId: String,
    override val remainder: String,
    override val project: String,
    override val material: String,
    override val warehouse: String,
    override val location: String,
    override val sequence: Int,
    override val status: MeasuredRemainderStatus,
    override val comment: String,
    override val length: Double,
    override val width: Double,
    override val depth: Double,
    val abortReason: AbortReason?
): MeasuredRemainderBase

