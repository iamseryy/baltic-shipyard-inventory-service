package ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.measuredremainder

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.measuredremainder.MeasuredRemainderLocation
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class MeasuredRemainderLocationDto(
    @JsonProperty(JsonFieldsProvider.WAREHOUSE_CODE) val warehouseCode: String,
    @JsonProperty(JsonFieldsProvider.BIN_CODE) val binCode: String,
    @JsonProperty(JsonFieldsProvider.SEQUENCE) val sequence: Int,
)

fun MeasuredRemainderLocationDto.toMeasuredRemainderLocation() =  MeasuredRemainderLocation(
    warehouseCode = warehouseCode,
    binCode = binCode,
    sequence = sequence
)

fun MeasuredRemainderLocation.toMeasuredRemainderLocationDto() =  MeasuredRemainderLocationDto(
    warehouseCode = warehouseCode,
    binCode = binCode,
    sequence = sequence
)
