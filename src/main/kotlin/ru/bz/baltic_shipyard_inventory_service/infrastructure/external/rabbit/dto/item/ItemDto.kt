package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.item

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.item.Item
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider

@JsonInclude(JsonInclude.Include.ALWAYS)
data class ItemDto(
    @JsonProperty(JsonFieldsProvider.ITEM_CODE) val code: String,
    @JsonProperty(JsonFieldsProvider.ITEM_DESC) val description: String,
    @JsonProperty(JsonFieldsProvider.UNIT) val unit: UnitDto
)

fun ItemDto.toItem() = Item(
    code = code,
    description = description,
    unit = unit.toUnit()
)

fun Item.toItemDto() = ItemDto(
    code = code,
    description = description,
    unit = unit.toUnitDto()
)