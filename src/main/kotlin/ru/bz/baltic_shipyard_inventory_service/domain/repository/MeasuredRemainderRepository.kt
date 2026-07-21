package ru.bz.baltic_shipyard_inventory_service.domain.repository

import ru.bz.baltic_shipyard_inventory_service.domain.common.Result
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.filter.MeasuredRemainderFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.*
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.Transaction
import ru.bz.baltic_shipyard_inventory_service.domain.common.pagination.DomainPage
import ru.bz.baltic_shipyard_inventory_service.domain.common.pagination.DomainPageRequest
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.measuredremainder.MeasuredRemainder
import java.time.LocalDate

interface MeasuredRemainderRepository {
    fun findPage(filter: MeasuredRemainderFilter, pageRequest: DomainPageRequest): DomainPage<MeasuredRemainder>?
    fun findById(id: String): MeasuredRemainder?
    fun update(measuredRemainder: MeasuredRemainder, userLogin: String): Result<MeasuredRemainder>
    fun findBinCodesByWarehouseCode(warehouseCode: String): List<String>?
}