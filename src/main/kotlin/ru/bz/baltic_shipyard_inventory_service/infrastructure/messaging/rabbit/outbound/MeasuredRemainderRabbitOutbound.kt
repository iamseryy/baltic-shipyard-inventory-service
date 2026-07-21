package ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.outbound

import ru.bz.baltic_shipyard_inventory_service.domain.common.Result
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.InventoriedMeasuredRemainders
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.measuredremainder.MeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.filter.MeasuredRemainderFilter
import ru.bz.baltic_shipyard_inventory_service.domain.common.pagination.DomainPage
import ru.bz.baltic_shipyard_inventory_service.domain.common.pagination.DomainPageRequest


interface MeasuredRemainderRabbitOutbound {
    fun findPage(filter: MeasuredRemainderFilter, pageRequest: DomainPageRequest): DomainPage<MeasuredRemainder>?
    fun update(measuredRemainder: MeasuredRemainder, userLogin: String): Result<MeasuredRemainder>
    fun inventoryMeasuredRemainder(inventoriedMeasuredRemainders: InventoriedMeasuredRemainders)
    fun findBinCodesByWarehouseCode(warehouseCode: String): List<String>?
}