package ru.bz.baltic_shipyard_inventory_service.infrastructure.repository.impl

import org.springframework.stereotype.Service
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchInventoryBalanceFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.stock.StockListPagination
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.repository.RabbitRepository
import ru.bz.baltic_shipyard_inventory_service.domain.repository.InventoryRepository


@Service
class InventoryRepositoryImpl(
    private val rabbitRepository: RabbitRepository
): InventoryRepository {
    override fun findInventoryBalanceByFilter(filter: SearchInventoryBalanceFilter): StockListPagination? =
        rabbitRepository.findInventoryBalanceByFilter(filter)
}