package ru.bz.baltic_shipyard_inventory_service.domain.model.entity.measuredremainder

import ru.bz.baltic_shipyard_inventory_service.domain.model.command.measuredremainder.UpdateMeasuredRemainderCommand
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemainderStatus
import java.time.LocalDateTime

class MeasuredRemainder(
    val id: String,
    var code: String,
    var projectCode: String,
    var material: String,
    var location: MeasuredRemainderLocation,
    var status: MeasuredRemainderStatus,
    var comment: String,
    var dimensions: MeasuredRemainderDimensions,
    var inventoryDate: LocalDateTime
){
    fun update(command: UpdateMeasuredRemainderCommand) {
        material = command.material ?: material
        location = command.location ?: location
        status = command.status ?: status
        comment = command.comment ?: comment
        if (command.dimensions != null) dimensions.update(command.dimensions)
        inventoryDate = command.inventoryDate ?: inventoryDate
    }
}