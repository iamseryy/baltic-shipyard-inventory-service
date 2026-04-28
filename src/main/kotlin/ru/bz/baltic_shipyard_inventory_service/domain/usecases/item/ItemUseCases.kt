package ru.bz.baltic_shipyard_inventory_service.domain.usecases.item

import org.springframework.stereotype.Service


@Service
data class ItemUseCases(
    val getItemDetailByItemCode: UseCaseGetItemDetailByItemCode,
    val itemExists: UseCaseItemExists
)
