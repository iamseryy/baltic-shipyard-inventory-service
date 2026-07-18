package ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.validation

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.MeasuredRemainderUpdateDto


@Schema(description="schema.validationresponse.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class MeasuredRemainderUpdateValidationResponse(
    @Schema(
        description = "schema.measuredremainder.id.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "R202011040000000009"
    )
    @JsonProperty(JsonFields.MEASURED_REMAINDER_ID) val id: String,

    @Schema(
        description = "schema.measuredremainderupdatedto.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
    )
    @JsonProperty(JsonFields.MEASURED_REMAINDER_FIELDS_UPDATE) val updateDto: MeasuredRemainderUpdateDto,

    @Schema(description="schema.abortreasondto.desc")
    @JsonProperty(JsonFields.ABORT_REASON) val abortReasonDto: MeasuredRemainderUpdateAbortReasonDto
)
