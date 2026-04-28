package ru.bz.baltic_shipyard_inventory_service.domain.usecases.warehouse

import org.springframework.stereotype.Service
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchLocationDetailShotFilter
import ru.bz.baltic_shipyard_inventory_service.domain.repository.WarehouseRepository


@Service
class UseCaseBinOfWarehouseExists (
    private val warehouseRepository: WarehouseRepository
){
    operator fun invoke(filter: SearchLocationDetailShotFilter): Boolean = warehouseRepository.getLocationDetailByFilter(filter) != null
}