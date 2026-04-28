package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.filter

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchLocationDetailShotFilter
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class SearchLocationDetailShotFilterDto(
    @field:JsonProperty(JsonFieldsProvider.WAREHOUSE_CODE) val warehouseCode: String,
    @field:JsonProperty(JsonFieldsProvider.BIN_CODE) val binCode: String
)

fun SearchLocationDetailShotFilter.toSearchLocationDetailShotFilterDto() = SearchLocationDetailShotFilterDto(
    warehouseCode = warehouseCode,
    binCode = binCode
)
