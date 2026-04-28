package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.filter

import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchLotDetailShotFilter
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider


data class SearchLotDetailShotFilterDto(
    @field:JsonProperty(JsonFieldsProvider.ITEM_CODE) val itemCode: String,
    @field:JsonProperty(JsonFieldsProvider.LOT_CODE) val lotCode: String
)

fun SearchLotDetailShotFilter.toSearchLotDetailShotFilterDto() = SearchLotDetailShotFilterDto(
    itemCode = itemCode,
    lotCode = lotCode
)

