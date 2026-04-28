package ru.bz.baltic_shipyard_inventory_service.domain.repository

import ru.bz.baltic_shipyard_inventory_service.domain.model.item.Item


interface ItemRepository {
    fun getItemDetail(itemCode: String): Item?
}