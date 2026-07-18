package ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.container

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.JsonFieldsProvider
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.RabbitMessage


@JsonInclude(JsonInclude.Include.ALWAYS)
data class PlacementContainerTransactionResponseMessage(
    @JsonProperty(JsonFieldsProvider.TRANSACTION) val placementContainerTransactionResponse: PlacementContainerTransactionResponseDto?
): RabbitMessage(messageType = "PlacementContainerTransactionResponseMessage")