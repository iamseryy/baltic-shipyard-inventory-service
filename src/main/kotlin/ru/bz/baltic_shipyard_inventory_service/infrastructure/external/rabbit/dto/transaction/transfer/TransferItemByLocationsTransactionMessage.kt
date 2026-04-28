package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.transaction.transfer

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.RabbitMessage


@JsonInclude(JsonInclude.Include.ALWAYS)
class TransferItemByBinTransactionMessage (
    @field:JsonProperty(JsonFieldsProvider.TRANSACTION) val transferItemByBinTransaction: TransferItemByBinTransactionDto?
): RabbitMessage(messageType = "TransferItemByBinTransactionMessage")