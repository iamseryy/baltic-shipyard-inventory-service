package ru.bz.baltic_shipyard_inventory_service.applications.usecases.warehouse.stock

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.common.errors.ResourceNotFoundException
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchInventoryBalanceFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.stock.StockListPagination
import ru.bz.baltic_shipyard_inventory_service.domain.repository.WarehouseRepository

@Component
class FindInventoryBalanceByFilterUseCase(
    private val warehouseRepository: WarehouseRepository
) {
    operator fun invoke(filter: SearchInventoryBalanceFilter): StockListPagination =
        warehouseRepository.findInventoryBalanceByFilter(filter)  ?: throw ResourceNotFoundException()
}