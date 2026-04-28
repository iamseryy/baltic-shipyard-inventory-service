package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.stock

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.stock.StockListPagination
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class StockListPaginationDto(
    @JsonProperty(JsonFieldsProvider.STOCK) val stockList: List<StockDto>,
    @JsonProperty(JsonFieldsProvider.PAGE) val page: Int,
    @JsonProperty(JsonFieldsProvider.PAGE_SIZE) val pageSize: Int
)

fun StockListPaginationDto.toStockListPagination() = StockListPagination(
    stockList = stockList.map { it.toStock() },
    page = page,
    pageSize = pageSize
)


