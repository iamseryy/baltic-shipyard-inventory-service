package ru.bz.baltic_shipyard_inventory_service.presentation.dto.warehouse

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.domain.model.warehouse.Location

data class LocationDto (
    @Schema(
        description = "schema.warehouse.code.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "R0200"
    )
    @JsonProperty(JsonFields.WAREHOUSE_CODE) val warehouseCode: String,

    @Schema(
        description = "schema.bin.code.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "222-001"
    )
    @JsonProperty(JsonFields.BIN_CODE) val binCode: String
)

fun Location.toLocationDto() = LocationDto(
    warehouseCode = warehouseCode,
    binCode = binCode
)

fun LocationDto.toLocation() = Location(
    warehouseCode = warehouseCode,
    binCode = binCode
)