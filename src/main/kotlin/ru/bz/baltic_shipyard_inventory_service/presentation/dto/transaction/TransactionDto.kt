package ru.bz.baltic_shipyard_inventory_service.presentation.dto.transaction

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.toUpdatableMeasuredRemainderDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.transaction.measuredremainder.TransactionOfUpdatableMeasuredRemaindersDto
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacedContainer
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.UpdatableMeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.Transaction
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.TransactionBase
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.TransactionStatus
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferredItemByLocations
import java.time.LocalDateTime


@Schema(description = "schema.transactiondto.desc")
data class TransactionDto (
    @Schema(
        description="schema.transaction.id.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example="15"
    )
    @JsonProperty(JsonFields.TRANSACTION_ID) override val id: Int,

    @Schema(
        description="schema.transaction.date.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        type = "string",
        example="2024-12-26T09:00:48.169662"
    )
    @JsonProperty(JsonFields.TRANSACTION_DATE) override val created: LocalDateTime,

    @Schema(
        description="schema.transaction.status.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        allowableValues = ["RUNNING", "COMPLETED", "FAILED"],
        type = "String",
        example="FAILED"
    )
    @JsonProperty(JsonFields.TRANSACTION_STATUS) override val status: TransactionStatus,

    @Schema(
        description="schema.transaction.user.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example="user123"
    )
    @JsonProperty(JsonFields.TRANSACTION_USER_LOGIN) override val userLogin: String
): TransactionBase

fun UpdatableMeasuredRemainder.toTransactionDto() = TransactionDto(
    id = transaction.id,
    created = transaction.created,
    status = transaction.status,
    userLogin = transaction.userLogin
)

fun Transaction.toTransactionDto() = TransactionDto(
    id = id,
    created = created,
    status = status,
    userLogin = userLogin
)

fun TransactionDto.toTransactionOfUpdatableMeasuredRemaindersDto(updatableMeasuredRemainders: List<UpdatableMeasuredRemainder>) =
    TransactionOfUpdatableMeasuredRemaindersDto(
        id = id,
        created = created,
        status = status,
        userLogin = userLogin,
        measuredRemainders = updatableMeasuredRemainders.map { it.toUpdatableMeasuredRemainderDto() }
    )

fun TransferredItemByLocations.toTransactionDto() = TransactionDto(
    id = transaction.id,
    created = transaction.created,
    status = transaction.status,
    userLogin = transaction.userLogin
)

fun PlacedContainer.toTransactionDto() = TransactionDto(
    id = transaction.id,
    created = transaction.created,
    status = transaction.status,
    userLogin = transaction.userLogin
)

