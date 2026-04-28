package ru.bz.baltic_shipyard_inventory_service.presentation.dto.transfer

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.abortreason.transfer.TransferItemByLocationsAbortReasonDto


@Schema(description = "schema.transferitembylocationsvalidationrrrorresponse.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class TransferItemByLocationsValidationErrorResponse(
    @Schema(
        description = "schema.warehouse.code.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example = "R0200"
    )
    @JsonProperty(JsonFields.WAREHOUSE_CODE) val warehouseCode: String? = null,

    @Schema(
        description = "schema.bin.code.source.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example = "20003"
    )
    @JsonProperty(JsonFields.BIN_CODE_SOURCE) val binCodeSource: String? = null,

    @Schema(
        description = "schema.bin.code.target.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example = "20004"
    )
    @JsonProperty(JsonFields.BIN_CODE_TARGET) val binCodeTarget: String? = null,

    @Schema(
        description = "schema.item.code.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example = "01362783045"
    )
    @JsonProperty(JsonFields.ITEM_CODE) val itemCode: String? = null,

    @Schema(
        description = "schema.lot.code.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example = "210901-00019"
    )
    @JsonProperty(JsonFields.LOT_CODE) val lotCode: String? = null,

    @Schema(
        description = "schema.quantity.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example = "100.5"
    )
    @JsonProperty(JsonFields.QUANTITY) val quantity: Double? = null,

    @Schema(
        description="schema.abortreasondto.desc",
    )
    @JsonProperty(JsonFields.ABORT_REASON)
    val abortReason: TransferItemByLocationsAbortReasonDto? = null
)

