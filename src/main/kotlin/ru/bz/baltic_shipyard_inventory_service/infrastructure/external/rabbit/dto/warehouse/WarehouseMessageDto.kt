package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.warehouse

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class WarehouseMessageDto(
    @JsonProperty(JsonFieldsProvider.WAREHOUSE) val warehouse: WarehouseDto?
)


