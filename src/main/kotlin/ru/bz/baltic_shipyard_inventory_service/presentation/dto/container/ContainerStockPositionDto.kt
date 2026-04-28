package ru.bz.baltic_shipyard_inventory_service.presentation.dto.container


import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.item.ItemDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.item.toItem
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.item.toItemDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.order.WarehouseOrderDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.order.toWarehouseOrder
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.order.toWarehouseOrderDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.warehouse.LocationDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.warehouse.toLocation
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.warehouse.toLocationDto
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.ContainerStockPosition


@Schema(description = "schema.container.stock.position.desc")
@JsonInclude(JsonInclude.Include.ALWAYS)
data class ContainerStockPositionDto(
    @Schema(
        description = "schema.line.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "2"
    )
    @JsonProperty(JsonFields.LINE) val line: Int,

    @Schema(
        description = "schema.warehouse.order.desc",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @JsonProperty(JsonFields.WAREHOUSE_ORDER) val warehouseOrder: WarehouseOrderDto,

    @Schema(
        description = "schema.item.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
    )
    @JsonProperty(JsonFields.ITEM) val item: ItemDto,

    @Schema(
        description = "schema.lot.code.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "231018-999999"
    )
    @JsonProperty(JsonFields.LOT_CODE) val lotCode: String,

    @Schema(
        description = "schema.quantity.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "10.0"
    )
    @JsonProperty(JsonFields.QUANTITY) val quantity: Double,

    @Schema(
        description = "schema.location.desc",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @JsonProperty(JsonFields.LOCATION) val stockLocation: LocationDto
)

fun ContainerStockPositionDto.toContainerStockPosition() = ContainerStockPosition(
    line = line,
    warehouseOrder = warehouseOrder.toWarehouseOrder(),
    item = item.toItem(),
    lotCode = lotCode,
    quantity = quantity,
    stockLocation = stockLocation.toLocation()
)

fun ContainerStockPosition.toContainerStockPositionDto() = ContainerStockPositionDto(
    line = line,
    warehouseOrder = warehouseOrder.toWarehouseOrderDto(),
    item = item.toItemDto(),
    lotCode = lotCode,
    quantity = quantity,
    stockLocation = stockLocation.toLocationDto()
)
