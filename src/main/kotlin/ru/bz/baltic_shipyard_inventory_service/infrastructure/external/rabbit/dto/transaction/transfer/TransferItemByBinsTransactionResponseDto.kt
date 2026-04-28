package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.transaction.transfer


import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.Transaction
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.TransactionStatus
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferredItemByLocations
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.abortreason.AbortReasonDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.abortreason.toAbortReason
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class TransferItemByBinsTransactionResponseDto(
    @JsonProperty(JsonFieldsProvider.TRANSACTION_ID) val id: Int,

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonProperty(JsonFieldsProvider.TRANSACTION_DATE) val created: LocalDateTime,

    @JsonProperty(JsonFieldsProvider.TRANSACTION_STATUS) val status: TransactionStatus,
    @JsonProperty(JsonFieldsProvider.TRANSACTION_USER_LOGIN) val userLogin: String,

    @JsonProperty(JsonFieldsProvider.ABORT_REASON) val abortReason: AbortReasonDto? = null
)

fun TransferItemByBinsTransactionResponseDto.toTransferredItemByLocations(
    transferredItemByLocations: TransferredItemByLocations
) = TransferredItemByLocations(
    id = transferredItemByLocations.id,
    warehouseCode = transferredItemByLocations.warehouseCode,
    binCodeSource = transferredItemByLocations.binCodeSource,
    binCodeTarget = transferredItemByLocations.binCodeTarget,
    itemCode = transferredItemByLocations.itemCode,
    lotCode = transferredItemByLocations.lotCode,
    quantity = transferredItemByLocations.quantity,
    transaction = Transaction(id = id, created = created, status = status, userLogin = userLogin),
    abortReason = abortReason?.toAbortReason()
)
