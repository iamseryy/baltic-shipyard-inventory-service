package ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.measuredremainder.message

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.JsonFieldsProvider
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.RabbitMessage


@JsonInclude(JsonInclude.Include.ALWAYS)
data class BinCodesResponseMessage(
    @field:JsonProperty(JsonFieldsProvider.BIN_CODES) val binCodes: List<String>,
): RabbitMessage(messageType = "BIN_CODES_SEARCH_RESPONSE")
