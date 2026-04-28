package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.stock

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.stock.Stock
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.item.ItemDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.item.toItem


@JsonInclude(JsonInclude.Include.ALWAYS)
data class StockDto(
    @JsonProperty(JsonFieldsProvider.WAREHOUSE_CODE) val warehouseCode: String,
    @JsonProperty(JsonFieldsProvider.BIN_CODE) val binCode: String,
    @JsonProperty(JsonFieldsProvider.ITEM) val item: ItemDto,
    @JsonProperty(JsonFieldsProvider.LOT_CODE) val lotCode: String,
    @JsonProperty(JsonFieldsProvider.QUANTITY_AVAILABLE) val quantityAvailable: Double = 0.0,
    @JsonProperty(JsonFieldsProvider.QUANTITY_BLOCKED) val quantityBlocked: Double = 0.0,
    @JsonProperty(JsonFieldsProvider.QUANTITY_ALLOCATED) val quantityAllocated: Double = 0.0
)

fun StockDto.toStock() = Stock(
    warehouseCode = warehouseCode,
    binCode = binCode,
    item = item.toItem(),
    lotCode = lotCode,
    quantityAvailable = quantityAvailable,
    quantityBlocked = quantityBlocked,
    quantityAllocated = quantityAllocated
)