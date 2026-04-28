package ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.abortreason.AbortReasonDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.measuredremainder.MeasuredRemainderForUpdateViolationByFieldDto


@Schema(description = "schema.measuredremainderforupdatevalidationerrorresponse.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class MeasuredRemainderForUpdateValidationErrorResponse(
    @Schema(
        description = "schema.measuredremainder.id.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "R202011040000000009"
    )
    @JsonProperty(JsonFields.ID)
    val id: String,

    @Schema(
        description="schema.warehouse.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="R0200"
    )
    @JsonProperty(JsonFields.WAREHOUSE)
    val warehouse: String? = null,

    @Schema(
        description="schema.location.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="01"
    )
    @JsonProperty(JsonFields.LOCATION)
    val location: String? = null,

    @Schema(
        description="schema.measuredremainder.status.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d"
    )
    @JsonProperty(JsonFields.STATUS)
    val status: Int? = null,

    @Schema(
        description="schema.measuredremainder.comment.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="\u0441/\u0437 \u2116 12-3877 \u043e\u0442 18.11.2020"
    )
    @JsonProperty(JsonFields.COMMENT)
    val comment: String? = null,

    @Schema(
        description="schema.measuredremainder.length.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="22.2"
    )
    @JsonProperty(JsonFields.LENGTH)
    val length: Double? = null,

    @Schema(
        description="schema.measuredremainder.width.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="30"
    )
    @JsonProperty(JsonFields.WIDTH)
    val width: Double? = null,

    @Schema(
        description="schema.abortreasondto.desc",
    )
    @JsonProperty(JsonFields.ABORT_REASON)
    val abortReason: AbortReasonDto<MeasuredRemainderForUpdateViolationByFieldDto>? = null
)
