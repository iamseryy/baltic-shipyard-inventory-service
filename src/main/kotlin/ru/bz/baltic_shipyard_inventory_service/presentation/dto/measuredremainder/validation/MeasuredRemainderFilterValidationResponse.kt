package ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.validation

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.filter.MeasuredRemainderFilterDto


@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description="schema.validationresponse.desc")
data class MeasuredRemainderFilterValidationResponse (
    @Schema(description="schema.searchmeasuredremainderfilterdto.desc")
    @JsonProperty(JsonFields.MEASURED_REMAINDER_FILTER) val filter: MeasuredRemainderFilterDto,

    @Schema(description="schema.abortreasondto.desc")
    @JsonProperty(JsonFields.ABORT_REASON) val abortReasonDto: MeasuredRemainderFilterAbortReasonDto
)