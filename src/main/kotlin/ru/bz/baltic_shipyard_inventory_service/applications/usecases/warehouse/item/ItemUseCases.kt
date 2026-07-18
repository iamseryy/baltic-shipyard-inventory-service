package ru.bz.baltic_shipyard_inventory_service.applications.usecases.warehouse.item

import org.springframework.stereotype.Service

@Service
data class ItemUseCases(
    val getItemDetailByItemCode: GetItemDetailByItemCodeUseCase,
    val itemExists: ItemExistsUseCase
)