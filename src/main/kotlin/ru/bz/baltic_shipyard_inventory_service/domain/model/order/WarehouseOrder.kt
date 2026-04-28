package ru.bz.baltic_shipyard_inventory_service.domain.model.order

data class WarehouseOrder(
    val origin: Int,
    val code: String,
    val set: Int,
    val line: Int,
    val sequence: Int
)
