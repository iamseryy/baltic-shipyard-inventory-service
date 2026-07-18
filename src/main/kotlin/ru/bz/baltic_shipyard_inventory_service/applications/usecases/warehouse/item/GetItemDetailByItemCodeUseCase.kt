package ru.bz.baltic_shipyard_inventory_service.applications.usecases.warehouse.item

import org.springframework.stereotype.Service
import ru.bz.baltic_shipyard_inventory_service.common.errors.ResourceNotFoundException
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.item.Item
import ru.bz.baltic_shipyard_inventory_service.domain.repository.WarehouseRepository

@Service
class GetItemDetailByItemCodeUseCase(
    private val warehouseRepository: WarehouseRepository
) {
    operator fun invoke(itemCode: String): Item = warehouseRepository.getItemDetail(itemCode) ?: throw ResourceNotFoundException()
}