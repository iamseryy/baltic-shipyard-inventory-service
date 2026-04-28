package ru.bz.baltic_shipyard_inventory_service.presentation.dto.abortreason

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.ViolationDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.measuredremainder.UpdatableMeasuredRemainderViolationByFieldDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.measuredremainder.toUpdatableMeasuredRemainderViolationByFieldDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.toViolationDto
import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.AbortReason
import java.io.Serializable


@Schema(description="schema.abortreasondto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class AbortReasonDto <T> (
    @Schema(description="schema.violationbyfieldsdto.desc")
    @JsonProperty(JsonFields.ERRORS) val violationsByFields: T? = null,

    @Schema(description="schema.generalViolation.desc")
    @JsonProperty(JsonFields.GENERAL) val generalViolation: ViolationDto? = null
): Serializable


fun  AbortReason.toAbortReasonDto() = AbortReasonDto<UpdatableMeasuredRemainderViolationByFieldDto>(

    violationsByFields = violationByFields?.toUpdatableMeasuredRemainderViolationByFieldDto(),
    generalViolation = generalViolation?.toViolationDto()
)
