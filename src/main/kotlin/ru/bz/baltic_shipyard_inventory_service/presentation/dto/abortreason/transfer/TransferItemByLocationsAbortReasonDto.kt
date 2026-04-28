package ru.bz.baltic_shipyard_inventory_service.presentation.dto.abortreason.transfer

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.ViolationDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.toViolationDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.transfer.TransferItemByLocationsViolationByFieldDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.transfer.toTransferItemByLocationsViolationByFieldDto
import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.AbortReason


@Schema(description="schema.abortreasondto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class TransferItemByLocationsAbortReasonDto (
    @Schema(description="schema.violationbyfieldsdto.desc")
    @JsonProperty(JsonFields.ERRORS) val violationsByFields: TransferItemByLocationsViolationByFieldDto? = null,

    @Schema(description="schema.generalViolation.desc")
    @JsonProperty(JsonFields.GENERAL) val generalViolation: ViolationDto? = null
)

fun  AbortReason.toTransferItemByLocationsAbortReasonDto() = TransferItemByLocationsAbortReasonDto(
    violationsByFields = violationByFields?.toTransferItemByLocationsViolationByFieldDto(),
    generalViolation = generalViolation?.toViolationDto()
)
