package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.transaction.measuredremainder

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.UpdatableMeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.Transaction
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.TransactionStatus
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.abortreason.toAbortReason
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.measuredremainder.MeasuredRemainderResponseDto
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class UpdateMeasuredRemainderTransactionResponseDto(
    @JsonProperty(JsonFieldsProvider.TRANSACTION_ID) val id: Int,

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonProperty(JsonFieldsProvider.TRANSACTION_DATE) val created: LocalDateTime,

    @JsonProperty(JsonFieldsProvider.TRANSACTION_STATUS) val status: TransactionStatus,
    @JsonProperty(JsonFieldsProvider.USER) val userLogin: String,
    @JsonProperty(JsonFieldsProvider.MEASURED_REMAINDER) val measuredRemainder: MeasuredRemainderResponseDto? = null,
)

fun UpdateMeasuredRemainderTransactionResponseDto.toUpdatableMeasuredRemainder(currentUpdatableMeasuredRemainder: UpdatableMeasuredRemainder) =
    UpdatableMeasuredRemainder(
        id = currentUpdatableMeasuredRemainder.id,
        measuredRemainderId = currentUpdatableMeasuredRemainder.measuredRemainderId,
        remainder = currentUpdatableMeasuredRemainder.remainder,
        project = currentUpdatableMeasuredRemainder.project,
        material = currentUpdatableMeasuredRemainder.material,
        warehouse = currentUpdatableMeasuredRemainder.warehouse,
        location = currentUpdatableMeasuredRemainder.location,
        sequence = currentUpdatableMeasuredRemainder.sequence,
        status = currentUpdatableMeasuredRemainder.status,
        comment = currentUpdatableMeasuredRemainder.comment,
        length = currentUpdatableMeasuredRemainder.length,
        width = currentUpdatableMeasuredRemainder.width,
        depth = currentUpdatableMeasuredRemainder.depth,
        transaction = Transaction(id = id, created = created, status = status, userLogin = userLogin),
        abortReason = measuredRemainder?.abortReason?.toAbortReason()
    )


