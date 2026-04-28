package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.filter

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchInventoryBalanceFilter
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.NON_NULL)
data class SearchInventoryBalanceFilterDto(
    @field:JsonProperty(JsonFieldsProvider.WAREHOUSE_CODE) val warehouseCode: String? = null,
    @field:JsonProperty(JsonFieldsProvider.BIN_CODE) val binCode: String? = null,
    @field:JsonProperty(JsonFieldsProvider.ITEM_CODE) val itemCode: String? = null,
    @field:JsonProperty(JsonFieldsProvider.LOT_CODE) val lotCode: String? = null,
    @field:JsonProperty(JsonFieldsProvider.FIND_LOT_CHILDREN) val findLotChildren: Boolean? = false,
    @field:JsonProperty(JsonFieldsProvider.PAGE) val page: Int,
    @field:JsonProperty(JsonFieldsProvider.PAGE_SIZE) val pageSize: Int
)

fun SearchInventoryBalanceFilter.toSearchInventoryBalanceFilterDto() = SearchInventoryBalanceFilterDto(
    warehouseCode = warehouseCode,
    binCode = binCode,
    itemCode = itemCode,
    lotCode = lotCode,
    findLotChildren = findLotChildren,
    page = page,
    pageSize = pageSize
)
