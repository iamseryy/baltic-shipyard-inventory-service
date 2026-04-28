package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.warehouse

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.warehouse.Warehouse
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class WarehouseDto(
    @JsonProperty(JsonFieldsProvider.WAREHOUSE_CODE) val code: String,
    @JsonProperty(JsonFieldsProvider.WAREHOUSE_DESC) val description: String
)

fun WarehouseDto.toWarehouse() = Warehouse(
    code = code,
    description = description
)
