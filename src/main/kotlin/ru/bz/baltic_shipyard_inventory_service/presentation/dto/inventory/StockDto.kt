package ru.bz.baltic_shipyard_inventory_service.presentation.dto.inventory

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.item.ItemDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.item.toItemDto
import ru.bz.baltic_shipyard_inventory_service.domain.model.stock.Stock


@Schema(description = "schema.stockdto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class StockDto(
    @Schema(
        description = "schema.warehouse.code.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "R0100"
    )
    @JsonProperty(JsonFields.WAREHOUSE_CODE) val warehouseCode: String,

    @Schema(
        description = "schema.bin.code.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "20003"
    )
    @JsonProperty(JsonFields.BIN_CODE) val binCode: String,

    @Schema(
        description = "schema.item.desc",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @JsonProperty(JsonFields.ITEM) val item: ItemDto,

    @Schema(
        description = "schema.lot.code.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "210901-00019"
    )
    @JsonProperty(JsonFields.LOT_CODE) val lotCode: String,

    @Schema(
        description = "schema.quantity.available.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "33.14"
    )
    @JsonProperty(JsonFields.QUANTITY_AVAILABLE) val quantityAvailable: Double = 0.0,

    @Schema(
        description = "schema.quantity.blocked.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "2.1"
    )
    @JsonProperty(JsonFields.QUANTITY_BLOCKED) val quantityBlocked: Double = 0.0,

    @Schema(
        description = "schema.quantity.allocated.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "1.4"
    )
    @JsonProperty(JsonFields.QUANTITY_ALLOCATED) val quantityAllocated: Double = 0.0
)

fun Stock.toStockDto() = StockDto(
    warehouseCode = warehouseCode,
    binCode = binCode,
    item = item.toItemDto(),
    lotCode = lotCode,
    quantityAvailable = quantityAvailable,
    quantityBlocked = quantityBlocked,
    quantityAllocated = quantityAllocated
)