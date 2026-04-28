package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.container

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.ContainerStock
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class ContainerStockDto(
    @JsonProperty(JsonFieldsProvider.STOCK) val containerStock: List<ContainerStockPositionDto>
)

fun ContainerStock.toContainerStockDto() = ContainerStockDto(
    containerStock = containerStock.map {it.toContainerStockPositionDto()}
)

fun ContainerStockDto.toContainerStock() = ContainerStock(
    containerStock = containerStock.map {it.toContainerStockPosition()}
)
