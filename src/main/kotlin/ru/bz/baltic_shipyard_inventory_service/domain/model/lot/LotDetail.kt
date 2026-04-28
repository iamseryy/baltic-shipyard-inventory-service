package ru.bz.baltic_shipyard_inventory_service.domain.model.lot

import ru.bz.baltic_shipyard_inventory_service.domain.model.item.Item


data class LotDetail(
    val item: Item,
    val lotCode: String,
    val effectivityUnit: Long,
    val lotCodeParent: String,
    val lotCodeSource: String,
)
