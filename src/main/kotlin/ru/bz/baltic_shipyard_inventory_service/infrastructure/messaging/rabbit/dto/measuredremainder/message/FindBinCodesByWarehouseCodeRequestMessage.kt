package ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.measuredremainder.message

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.JsonFieldsProvider
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.RabbitMessage


@JsonInclude(JsonInclude.Include.ALWAYS)
data class FindBinCodesByWarehouseCodeRequestMessage(
    @field:JsonProperty(JsonFieldsProvider.WAREHOUSE_CODE) val warehouseCode: String
): RabbitMessage(messageType = "BIN_CODES_BY_WAREHOUSE_CODE_SEARCH_REQUEST")

