package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.transaction.measuredremainder

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.InventoriedMeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.InventoriedMeasuredRemainders
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.UpdatableMeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.Transaction
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.TransactionStatus
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.abortreason.toAbortReason
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.measuredremainder.MeasuredRemainderResponseDto
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.ALWAYS)
data class InventoryMeasuredRemaindersTransactionResponseDto(
    @JsonProperty(JsonFieldsProvider.TRANSACTION_ID) val id: Int,

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonProperty(JsonFieldsProvider.TRANSACTION_DATE) val created: LocalDateTime,

    @JsonProperty(JsonFieldsProvider.TRANSACTION_STATUS) val status: TransactionStatus,
    @JsonProperty(JsonFieldsProvider.USER) val userLogin: String,
    @JsonProperty(JsonFieldsProvider.MEASURED_REMAINDERS) val measuredRemainders: List<MeasuredRemainderResponseDto>,
)

fun InventoryMeasuredRemaindersTransactionResponseDto.toInventoriedMeasuredRemainders(currentUpdatableMeasuredRemainders: List<UpdatableMeasuredRemainder>) =
    InventoriedMeasuredRemainders(
        transaction = Transaction(id = id, created = created, status = status, userLogin = userLogin),
        measuredRemainders = measuredRemainders.mapIndexed { index, measuredRemainderResponseDto ->
            InventoriedMeasuredRemainder(
                id = currentUpdatableMeasuredRemainders[index].id,
                measuredRemainderId = measuredRemainderResponseDto.id,
                remainder = currentUpdatableMeasuredRemainders[index].remainder,
                project = currentUpdatableMeasuredRemainders[index].project,
                material = currentUpdatableMeasuredRemainders[index].material,
                warehouse = currentUpdatableMeasuredRemainders[index].warehouse,
                location = currentUpdatableMeasuredRemainders[index].location,
                sequence = currentUpdatableMeasuredRemainders[index].sequence,
                status = currentUpdatableMeasuredRemainders[index].status,
                comment = currentUpdatableMeasuredRemainders[index].comment,
                length = currentUpdatableMeasuredRemainders[index].length,
                width = currentUpdatableMeasuredRemainders[index].width,
                depth = currentUpdatableMeasuredRemainders[index].depth,
                abortReason = measuredRemainderResponseDto.abortReason?.toAbortReason()
            )
        }
    )
