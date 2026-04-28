package ru.bz.baltic_shipyard_inventory_service.infrastructure.repository.impl

import org.springframework.stereotype.Service
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchLocationDetailShotFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.warehouse.Bin
import ru.bz.baltic_shipyard_inventory_service.domain.model.warehouse.Warehouse
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.repository.RabbitRepository
import ru.bz.baltic_shipyard_inventory_service.domain.repository.WarehouseRepository

@Service
class WarehouseRepositoryImpl(
    private val rabbitRepository: RabbitRepository
): WarehouseRepository {
    override fun getWarehouseDetail(warehouseCode: String): Warehouse? = rabbitRepository.getWarehouseDetail(warehouseCode)

    override fun getLocationDetailByFilter(filter: SearchLocationDetailShotFilter): Bin? =
        rabbitRepository.getLocationDetailByFilter(filter)

}