package ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.measuredremainder

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.command.measuredremainder.UpdateMeasuredRemainderDimensionsCommand
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.measuredremainder.MeasuredRemainderDimensions
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class MeasuredRemainderDimensionsDto(
    @JsonProperty(JsonFieldsProvider.LENGTH) val length: Double,
    @JsonProperty(JsonFieldsProvider.WIDTH) val width: Double,
    @JsonProperty(JsonFieldsProvider.THICKNESS) val thickness: Double
)

fun MeasuredRemainderDimensionsDto.toMeasuredRemainderDimensions() = MeasuredRemainderDimensions(
    length = length,
    width = width,
    thickness = thickness
)


fun MeasuredRemainderDimensions.toMeasuredRemainderDimensionsDto() = MeasuredRemainderDimensionsDto(
    length = length,
    width = width,
    thickness = thickness
)