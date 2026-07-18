package ru.bz.baltic_shipyard_inventory_service.applications.usecases.warehouse.lot

import org.springframework.stereotype.Service
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchLotDetailShotFilter
import ru.bz.baltic_shipyard_inventory_service.domain.repository.WarehouseRepository

@Service
class LotExistsUseCase (
    private val warehouseRepository: WarehouseRepository
) {
    operator fun invoke(filter: SearchLotDetailShotFilter) = warehouseRepository.getLotDetail(filter) != null
}