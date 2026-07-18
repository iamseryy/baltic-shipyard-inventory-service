package ru.bz.baltic_shipyard_inventory_service.domain.model.command.measuredremainder

import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.location.Location
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.measuredremainder.MeasuredRemainderLocation
import java.time.LocalDateTime

data class InventoryMeasuredRemainderCommand(
    val location: Location,
    val updateMeasuredRemainderCommands: List<UpdateMeasuredRemainderCommand>,
){
    fun alingWarehouseCodeAndBinCode() = updateMeasuredRemainderCommands.map {
        it.update(warehouseCode = location.warehouseCode, binCode = location.binCode)
    }

    fun reSetCurrentInventoryDateTime() = updateMeasuredRemainderCommands.map { it.reSetInventoryDate(LocalDateTime.now()) }

}