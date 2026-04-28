package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.order

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.order.WarehouseOrder
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class WarehouseOrderDto(
    @JsonProperty(JsonFieldsProvider.ORIGIN) val origin: Int,
    @JsonProperty(JsonFieldsProvider.CODE) val code: String,
    @JsonProperty(JsonFieldsProvider.SET) val set: Int,
    @JsonProperty(JsonFieldsProvider.LINE) val line: Int,
    @JsonProperty(JsonFieldsProvider.SEQUENCE) val sequence: Int
)

fun WarehouseOrderDto.toWarehouseOrder() = WarehouseOrder(
    origin = origin,
    code = code,
    set = set,
    line = line,
    sequence = sequence
)

fun WarehouseOrder.toWarehouseOrderDto() = WarehouseOrderDto(
    origin = origin,
    code = code,
    set = set,
    line = line,
    sequence = sequence
)