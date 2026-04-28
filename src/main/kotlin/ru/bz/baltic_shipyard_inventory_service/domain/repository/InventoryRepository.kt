package ru.bz.baltic_shipyard_inventory_service.domain.repository

import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchInventoryBalanceFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.stock.StockListPagination

interface InventoryRepository {
    fun findInventoryBalanceByFilter(filter: SearchInventoryBalanceFilter): StockListPagination?
}