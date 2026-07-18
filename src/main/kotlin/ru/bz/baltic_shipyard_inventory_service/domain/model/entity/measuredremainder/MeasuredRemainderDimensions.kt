package ru.bz.baltic_shipyard_inventory_service.domain.model.entity.measuredremainder

import ru.bz.baltic_shipyard_inventory_service.domain.model.command.measuredremainder.UpdateMeasuredRemainderDimensionsCommand


data class MeasuredRemainderDimensions (
    var length: Double,
    var width: Double,
    var thickness: Double
) {
    fun update(command: UpdateMeasuredRemainderDimensionsCommand) {
        length = command.length ?: length
        width = command.width ?: width
        thickness = command.thickness ?: thickness

    }
}