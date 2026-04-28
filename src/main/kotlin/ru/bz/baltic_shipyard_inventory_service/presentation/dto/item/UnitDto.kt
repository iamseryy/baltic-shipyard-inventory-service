package ru.bz.baltic_shipyard_inventory_service.presentation.dto.item

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.domain.model.item.Unit


@Schema(description = "schema.unit.desc")
@JsonInclude(JsonInclude.Include.ALWAYS)
data class UnitDto(
    @Schema(
        description = "schema.unit.code.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "796"
    )
    @JsonProperty(JsonFields.CODE) val code: String,

    @Schema(
        description = "schema.unit.desc.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "\u0448\u0442"
    )
    @JsonProperty(JsonFields.DESCRIPTION) val description: String
)

fun Unit.toUnitDto() = UnitDto(
    code = code,
    description = description
)

fun UnitDto.toUnit() = Unit(
    code = code,
    description = description
)