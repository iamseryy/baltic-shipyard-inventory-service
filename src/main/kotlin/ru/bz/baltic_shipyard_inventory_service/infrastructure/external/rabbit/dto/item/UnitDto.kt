package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.item

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.item.Unit
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider

@JsonInclude(JsonInclude.Include.ALWAYS)
data class UnitDto(
    @JsonProperty(JsonFieldsProvider.UNIT_CODE) val code: String,
    @JsonProperty(JsonFieldsProvider.UNIT_DESC) val description: String
)

fun UnitDto.toUnit() = Unit(
    code = code,
    description = description
)

fun Unit.toUnitDto() = UnitDto(
    code = code,
    description = description
)