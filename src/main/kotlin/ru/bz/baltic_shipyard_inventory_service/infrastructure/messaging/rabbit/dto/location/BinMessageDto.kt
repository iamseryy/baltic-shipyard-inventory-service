package ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.location

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class BinMessageDto(
    @JsonProperty(JsonFieldsProvider.BIN) val bin: BinDto?
)
