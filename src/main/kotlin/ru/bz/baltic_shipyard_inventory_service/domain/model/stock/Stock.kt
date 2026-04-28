package ru.bz.baltic_shipyard_inventory_service.domain.model.stock

import ru.bz.baltic_shipyard_inventory_service.domain.model.item.Item

data class Stock(
    val warehouseCode: String,
    val binCode: String,
    val item: Item,
    val lotCode: String,
    val quantityAvailable: Double,
    val quantityBlocked: Double,
    val quantityAllocated: Double
)
