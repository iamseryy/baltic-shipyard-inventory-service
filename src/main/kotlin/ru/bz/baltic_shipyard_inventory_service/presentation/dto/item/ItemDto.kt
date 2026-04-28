package ru.bz.baltic_shipyard_inventory_service.presentation.dto.item

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.domain.model.item.Item


@Schema(description = "schema.item.desc")
@JsonInclude(JsonInclude.Include.ALWAYS)
data class ItemDto(
    @Schema(
        description = "schema.item.code.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "01362783045"
    )
    @JsonProperty(JsonFields.CODE) val code: String,

    @Schema(
        description = "schema.item.desc.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "\u0422\u0440\u0443\u0431\u0430 10\u04452,0 L=6000 \u0413\u041e\u0421\u0422 9941-"
    )
    @JsonProperty(JsonFields.DESCRIPTION) val description: String,

    @Schema(
        description = "schema.unit.desc",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @JsonProperty(JsonFields.UNIT) val unit: UnitDto
)

fun Item.toItemDto() = ItemDto(
    code = code.trim(),
    description = description,
    unit = unit.toUnitDto()
)

fun ItemDto.toItem() = Item(
    code = code.trim(),
    description = description,
    unit = unit.toUnit()
)