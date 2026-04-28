package ru.bz.baltic_shipyard_inventory_service.domain.usecases.inventory

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchInventoryBalanceFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.stock.StockListPagination
import ru.bz.baltic_shipyard_inventory_service.domain.exception.ResourceNotFoundException
import ru.bz.baltic_shipyard_inventory_service.domain.repository.InventoryRepository


@Component
class UseCaseFindInventoryBalanceByFilter(
    private val inventoryRepository: InventoryRepository
) {
    operator fun invoke(filter: SearchInventoryBalanceFilter): StockListPagination =
        inventoryRepository.findInventoryBalanceByFilter(filter)  ?: throw ResourceNotFoundException()
}