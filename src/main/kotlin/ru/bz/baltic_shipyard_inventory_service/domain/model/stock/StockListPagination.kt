package ru.bz.baltic_shipyard_inventory_service.domain.model.stock

data class StockListPagination(
    val stockList: List<Stock>,
    val page: Int,
    val pageSize: Int
)
