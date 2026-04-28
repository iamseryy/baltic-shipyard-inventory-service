package ru.bz.baltic_shipyard_inventory_service.domain.repository

import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchLocationDetailShotFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.warehouse.Bin
import ru.bz.baltic_shipyard_inventory_service.domain.model.warehouse.Warehouse

interface WarehouseRepository {
    fun getWarehouseDetail(warehouseCode: String): Warehouse?
    fun getLocationDetailByFilter(filter: SearchLocationDetailShotFilter): Bin?
}