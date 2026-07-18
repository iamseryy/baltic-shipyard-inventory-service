package ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.location

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.location.Bin
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.JsonFieldsProvider

@JsonInclude(JsonInclude.Include.ALWAYS)
data class BinDto(
    @JsonProperty(JsonFieldsProvider.BIN_CODE) val code: String?,
    @JsonProperty(JsonFieldsProvider.BIN_DESC) val description: String,
    @JsonProperty(JsonFieldsProvider.WAREHOUSE_CODE) val warehouseCode: String
)

fun BinDto.toBin() = Bin(
    code = code ?: "",
    description = description,
    warehouseCode = warehouseCode
)
