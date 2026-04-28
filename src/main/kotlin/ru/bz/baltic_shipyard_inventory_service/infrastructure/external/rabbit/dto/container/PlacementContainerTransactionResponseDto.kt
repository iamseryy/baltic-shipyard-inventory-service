package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.container

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacedContainer
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.Transaction
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.TransactionStatus
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.abortreason.AbortReasonDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.abortreason.toAbortReason
import java.time.LocalDateTime


@JsonInclude(JsonInclude.Include.NON_NULL)
data class PlacementContainerTransactionResponseDto(
    @param:JsonProperty(JsonFieldsProvider.TRANSACTION_ID) val id: Int,

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @param:JsonProperty(JsonFieldsProvider.TRANSACTION_DATE) val created: LocalDateTime,

    @param:JsonProperty(JsonFieldsProvider.TRANSACTION_STATUS) val status: TransactionStatus,
    @param:JsonProperty(JsonFieldsProvider.TRANSACTION_USER_LOGIN) val userLogin: String,
    @param:JsonProperty(JsonFieldsProvider.ABORT_REASON) val abortReason: AbortReasonDto? = null
)

fun PlacementContainerTransactionResponseDto.toPlacedContainer(placedContainer: PlacedContainer) = PlacedContainer(
    id = placedContainer.id,
    containerCode = placedContainer.containerCode,
    binCodeTarget = placedContainer.binCodeTarget,
    transaction = Transaction(id = id, created = created, status = status, userLogin = userLogin),
    abortReason = abortReason?.toAbortReason()
)
