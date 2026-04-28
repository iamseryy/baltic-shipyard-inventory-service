package ru.bz.baltic_shipyard_inventory_service.domain.model.filter

data class SearchInventoryBalanceFilter(
    val warehouseCode:String? = null,
    val binCode: String? = null,
    val itemCode: String? = null,
    val lotCode: String? = null,
    val findLotChildren: Boolean = false,
    val page: Int = 0,
    val pageSize: Int = 10
)
