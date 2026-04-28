package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.container

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.ContainerStockPosition
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.item.ItemDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.item.toItem
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.item.toItemDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.order.WarehouseOrderDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.order.toWarehouseOrder
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.order.toWarehouseOrderDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.warehouse.LocationDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.warehouse.toLocation
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.warehouse.toLocationDto


@JsonInclude(JsonInclude.Include.ALWAYS)
data class ContainerStockPositionDto(
    @JsonProperty(JsonFieldsProvider.LINE) val line: Int,
    @JsonProperty(JsonFieldsProvider.WAREHOUSE_ORDER) val warehouseOrder: WarehouseOrderDto,
    @JsonProperty(JsonFieldsProvider.ITEM) val item: ItemDto,
    @JsonProperty(JsonFieldsProvider.LOT_CODE) val lotCode: String,
    @JsonProperty(JsonFieldsProvider.QUANTITY) val quantity: Double,
    @JsonProperty(JsonFieldsProvider.LOCATION) val stockLocation: LocationDto
)

fun ContainerStockPosition.toContainerStockPositionDto() = ContainerStockPositionDto(
    line = line,
    warehouseOrder = warehouseOrder.toWarehouseOrderDto(),
    item = item.toItemDto(),
    lotCode = lotCode,
    quantity = quantity,
    stockLocation = stockLocation.toLocationDto()
)

fun ContainerStockPositionDto.toContainerStockPosition() = ContainerStockPosition(
    line = line,
    warehouseOrder = warehouseOrder.toWarehouseOrder(),
    item = item.toItem(),
    lotCode = lotCode,
    quantity = quantity,
    stockLocation = stockLocation.toLocation()
)