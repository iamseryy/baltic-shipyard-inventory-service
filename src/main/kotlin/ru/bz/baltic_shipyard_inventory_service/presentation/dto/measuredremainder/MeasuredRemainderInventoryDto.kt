package ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder

import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.command.measuredremainder.InventoryMeasuredRemainderCommand
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.location.LocationDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.location.toLocation

data class MeasuredRemainderInventoryDto(
    @JsonProperty(JsonFields.LOCATION) val locationDto: LocationDto,
    @JsonProperty(JsonFields.MEASURED_REMAINDERS) val measuredRemaindersDto: List<MeasuredRemainderDto>
)

fun MeasuredRemainderInventoryDto.toInventoryMeasuredRemainderCommand() = InventoryMeasuredRemainderCommand(
    location = locationDto.toLocation(),
    updateMeasuredRemainderCommands = measuredRemaindersDto.map { it.toUpdateMeasuredRemainderCommand() }
)
