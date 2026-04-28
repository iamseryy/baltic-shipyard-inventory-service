package ru.bz.baltic_shipyard_inventory_service.presentation.dto.inventory

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.domain.model.stock.StockListPagination


@Schema(description = "schema.stocklistpaginationdto.desc")
data class StockListPaginationDto(
    @Schema(
        description = "schema.stocklist.desc",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @JsonProperty(JsonFields.STOCK) val stockList: List<StockDto>,

    @Schema(
        description = "schema.pagination.page.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "1"
    )
    @JsonProperty(JsonFields.PAGE) val page: Int,

    @Schema(
        description = "schema.pagination.page_size.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "10"
    )
    @JsonProperty(JsonFields.PAGE_SIZE) val pageSize: Int
)

fun StockListPagination.toStockListPaginationDto() = StockListPaginationDto(
    stockList = stockList.map { it.toStockDto() },
    page = page,
    pageSize = pageSize
)
