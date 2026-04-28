package ru.bz.baltic_shipyard_inventory_service.presentation.dto.transaction.transfer

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.transfer.TransferredItemByLocationsDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.transfer.toTransferredItemByLocationsDto
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.TransactionBase
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.TransactionStatus
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferredItemByLocations
import java.time.LocalDateTime



@Schema(description = "schema.transactionoftransferreditembylocationsdto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class TransactionOfTransferredItemByLocationsDto(
    @Schema(
        description = "schema.transaction.id.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "18"
    )
    @JsonProperty(JsonFields.TRANSACTION_ID) override val id: Int,

    @Schema(
        description = "schema.transaction.date.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        type = "string",
        example="2024-12-26T09:00:48.169662"
    )
    @JsonProperty(JsonFields.TRANSACTION_DATE) override val created: LocalDateTime,

    @Schema(
        description = "schema.transaction.status.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        allowableValues = ["RUNNING", "COMPLETED", "FAILED"],
        type = "String",
        example="FAILED"
    )
    @JsonProperty(JsonFields.TRANSACTION_STATUS) override val status: TransactionStatus,

    @Schema(
        description = "schema.transaction.user.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example="user123"
    )
    @JsonProperty(JsonFields.TRANSACTION_USER_LOGIN) override val userLogin: String,

    @Schema(
        description = "schema.transfer.desc",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @JsonProperty(JsonFields.TRANSFER) val transfer: TransferredItemByLocationsDto
): TransactionBase

fun TransferredItemByLocations.toTransactionOfTransferredItemByLocationsDto() = TransactionOfTransferredItemByLocationsDto(
    id = transaction.id,
    created = transaction.created,
    status = transaction.status,
    userLogin = transaction.userLogin,
    transfer = toTransferredItemByLocationsDto()
)