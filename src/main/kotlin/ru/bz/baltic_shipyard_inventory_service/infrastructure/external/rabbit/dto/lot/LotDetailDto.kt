package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.lot

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.lot.LotDetail
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.item.ItemDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.item.toItem

@JsonInclude(JsonInclude.Include.ALWAYS)
data class LotDetailDto(
    @JsonProperty(JsonFieldsProvider.ITEM) val item: ItemDto,
    @JsonProperty(JsonFieldsProvider.LOT_CODE) val lotCode: String,
    @JsonProperty(JsonFieldsProvider.EFFECTIVITY_UNIT) val effectivityUnit: Long,
    @JsonProperty(JsonFieldsProvider.LOT_CODE_PARENT) val lotCodeParent: String,
    @JsonProperty(JsonFieldsProvider.LOT_CODE_SOURCE) val lotCodeSource: String,
)

fun LotDetailDto.toLotDetail() = LotDetail(
    item = item.toItem(),
    lotCode = lotCode,
    effectivityUnit = effectivityUnit,
    lotCodeParent = lotCodeParent,
    lotCodeSource = lotCodeSource
)
