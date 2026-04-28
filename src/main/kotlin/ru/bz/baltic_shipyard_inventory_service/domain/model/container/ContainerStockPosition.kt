package ru.bz.baltic_shipyard_inventory_service.domain.model.container

import ru.bz.baltic_shipyard_inventory_service.domain.model.item.Item
import ru.bz.baltic_shipyard_inventory_service.domain.model.order.WarehouseOrder
import ru.bz.baltic_shipyard_inventory_service.domain.model.warehouse.Location


data class ContainerStockPosition(
    val line: Int,
    val warehouseOrder: WarehouseOrder,
    val item: Item,
    val lotCode: String,
    val quantity: Double,
    val stockLocation: Location
)
