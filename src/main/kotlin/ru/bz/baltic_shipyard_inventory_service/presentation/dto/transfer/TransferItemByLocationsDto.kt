package ru.bz.baltic_shipyard_inventory_service.presentation.dto.transfer

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.abortreason.transfer.TransferItemByLocationsAbortReasonDto
import ru.bz.baltic_shipyard_inventory_service.presentation.validation.annotation.NotEmpty
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferItemByLocations


@Schema(description="schema.transferitembylocationsdto.desc")
data class TransferItemByLocationsDto(
    @Schema(
        description = "schema.warehouse.code.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "R0200"
    )
    @NotEmpty
    @JsonProperty(JsonFields.WAREHOUSE_CODE) val warehouseCode: String,

    @Schema(
        description = "schema.bin.code.source.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "20003"
    )
    @NotEmpty
    @JsonProperty(JsonFields.BIN_CODE_SOURCE) val binCodeSource: String,

    @Schema(
        description = "schema.bin.code.target.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "20004"
    )
    @NotEmpty
    @JsonProperty(JsonFields.BIN_CODE_TARGET) val binCodeTarget: String,

    @Schema(
        description = "schema.item.code.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "01362783045"
    )
    @NotEmpty
    @JsonProperty(JsonFields.ITEM_CODE) val itemCode: String,

    @Schema(
        description = "schema.lot.code.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "210901-00019"
    )
    @NotEmpty
    @JsonProperty(JsonFields.LOT_CODE) val lotCode: String,

    @Schema(
        description = "schema.quantity.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "101.5"
    )
    @get:Min(message = "code={error.1005.code};description={error.1005.description}", value = 0)
    @get:Max(message = "code={error.1004.code};description={error.1004.description}", value = 1_000_000_000)
    @JsonProperty(JsonFields.QUANTITY) val quantity: Double

)

fun TransferItemByLocationsDto.toTransferItemByLocations(userName: String) = TransferItemByLocations(
    warehouseCode = warehouseCode,
    binCodeSource = binCodeSource,
    binCodeTarget = binCodeTarget,
    itemCode = itemCode,
    lotCode = lotCode,
    quantity = quantity,
    userLogin = userName
)

fun TransferItemByLocationsDto.toTransferItemByLocationsValidationErrorResponse(
    abortReason: TransferItemByLocationsAbortReasonDto
) = TransferItemByLocationsValidationErrorResponse(
    warehouseCode = warehouseCode,
    binCodeSource = binCodeSource,
    binCodeTarget = binCodeTarget,
    itemCode = itemCode,
    lotCode = lotCode,
    quantity = quantity,
    abortReason = abortReason
)
