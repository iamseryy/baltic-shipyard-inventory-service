package ru.bz.baltic_shipyard_inventory_service.domain.model.command.measuredremainder

import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.measuredremainder.MeasuredRemainderLocation
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemainderStatus
import java.time.LocalDateTime

data class UpdateMeasuredRemainderCommand(
    val id: String,
    val location: MeasuredRemainderLocation? = null,
    val material: String? = null,
    val status: MeasuredRemainderStatus? = null,
    val comment: String? = null,
    val dimensions: UpdateMeasuredRemainderDimensionsCommand? = null,
    var inventoryDate: LocalDateTime? = null
) {
    fun reSetInventoryDate(date: LocalDateTime) {
        inventoryDate = date
    }

    fun update(warehouseCode: String, binCode: String) {
        location?.update(warehouseCode, binCode)
    }




}