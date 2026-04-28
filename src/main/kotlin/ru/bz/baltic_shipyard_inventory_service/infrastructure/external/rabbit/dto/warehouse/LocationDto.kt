package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.warehouse

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.warehouse.Location
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class LocationDto (
    @JsonProperty(JsonFieldsProvider.WAREHOUSE_CODE) val warehouseCode: String,
    @JsonProperty(JsonFieldsProvider.BIN_CODE) val binCode: String
)

fun LocationDto.toLocation() = Location(
    warehouseCode = warehouseCode,
    binCode = binCode
)

fun Location.toLocationDto() = LocationDto(
    warehouseCode = warehouseCode,
    binCode = binCode
)