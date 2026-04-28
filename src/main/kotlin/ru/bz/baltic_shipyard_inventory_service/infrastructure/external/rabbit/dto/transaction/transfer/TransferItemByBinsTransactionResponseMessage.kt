package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.transaction.transfer

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.RabbitMessage


@JsonInclude(JsonInclude.Include.ALWAYS)
class TransferItemByBinsTransactionResponseMessage (
    @JsonProperty(JsonFieldsProvider.TRANSACTION) val transferItemByBinsTransactionResponse: TransferItemByBinsTransactionResponseDto?
): RabbitMessage(messageType = "TransferItemByBinsTransactionResponseMessage")