package ru.bz.baltic_shipyard_inventory_service.presentation.dto.transfer

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.abortreason.transfer.TransferItemByLocationsAbortReasonDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.abortreason.transfer.toTransferItemByLocationsAbortReasonDto
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferItemByLocationAbortReason
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferredItemByLocations


@Schema(description = "schema.transferreditembylocationsdto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class TransferredItemByLocationsDto(
    @Schema(
        description = "schema.warehouse.code.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "R0200"
    )
    @JsonProperty(JsonFields.WAREHOUSE_CODE) val warehouseCode: String,

    @Schema(
        description = "schema.bin.code.source.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "20003"
    )
    @JsonProperty(JsonFields.BIN_CODE_SOURCE) val binCodeSource: String,

    @Schema(
        description = "schema.bin.code.target.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "20004"
    )
    @JsonProperty(JsonFields.BIN_CODE_TARGET) val binCodeTarget: String,

    @Schema(
        description = "schema.item.code.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "01362783045"
    )
    @JsonProperty(JsonFields.ITEM_CODE) val itemCode: String,

    @Schema(
        description = "schema.lot.code.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "210901-00019"
    )
    @JsonProperty(JsonFields.LOT_CODE) val lotCode: String,

    @Schema(
        description = "schema.quantity.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "100.5"
    )
    @JsonProperty(JsonFields.QUANTITY) val quantity: Double,

    @Schema(
        description="schema.abortreasondto.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
    )
    @JsonProperty(JsonFields.ABORT_REASON)
    val abortReason: TransferItemByLocationsAbortReasonDto? = null
)

fun TransferredItemByLocations.toTransferredItemByLocationsDto() = TransferredItemByLocationsDto(
    warehouseCode = warehouseCode,
    binCodeSource = binCodeSource,
    binCodeTarget = binCodeTarget,
    itemCode = itemCode,
    lotCode = lotCode,
    quantity = quantity,
    abortReason = abortReason?.toTransferItemByLocationsAbortReasonDto()
)

fun TransferItemByLocationAbortReason.toTransferredItemByLocationsDto() = TransferredItemByLocationsDto(
    warehouseCode = warehouseCode,
    binCodeSource = binCodeSource,
    binCodeTarget = binCodeTarget,
    itemCode = itemCode,
    lotCode = lotCode,
    quantity = quantity,
    abortReason = abortReason?.toTransferItemByLocationsAbortReasonDto()
)
