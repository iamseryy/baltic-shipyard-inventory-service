package ru.bz.baltic_shipyard_inventory_service.applications.usecases.warehouse.item

import org.springframework.stereotype.Service
import ru.bz.baltic_shipyard_inventory_service.domain.repository.WarehouseRepository

@Service
class ItemExistsUseCase(
    private val warehouseRepository: WarehouseRepository
) {
    operator fun invoke(itemCode:String) = warehouseRepository.getItemDetail(itemCode) != null
}