package ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.validation

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.domain.common.Result.Failure
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.ViolationDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.toViolationDto


@Schema(description="schema.abortreasondto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class MeasuredRemainderUpdateAbortReasonDto(
    @Schema(description="schema.violationbyfieldsdto.desc")
    @JsonProperty(JsonFields.ERRORS) val violationsByFields: MeasuredRemainderUpdateViolationDto? = null,

    @Schema(description="schema.generalViolation.desc")
    @JsonProperty(JsonFields.GENERAL) val generalViolation: ViolationDto? = null
)

fun Failure.toMeasuredRemainderUpdateAbortReasonDto() = MeasuredRemainderUpdateAbortReasonDto(
    generalViolation = abortReason.generalViolation?.toViolationDto()
)
