package ru.bz.baltic_shipyard_inventory_service.domain.usecases.warehouse

import org.springframework.stereotype.Service
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchLocationDetailShotFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.warehouse.Bin
import ru.bz.baltic_shipyard_inventory_service.domain.repository.WarehouseRepository


@Service
class UseCaseGetLocationDetailByFilter(
    private val warehouseRepository: WarehouseRepository
) {
    operator fun invoke(filter: SearchLocationDetailShotFilter): Bin? = warehouseRepository.getLocationDetailByFilter(filter)
}