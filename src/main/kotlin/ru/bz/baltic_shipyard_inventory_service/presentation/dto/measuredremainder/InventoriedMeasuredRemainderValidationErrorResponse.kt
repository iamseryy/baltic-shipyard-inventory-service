package ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.abortreason.AbortReasonDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.measuredremainder.InventoriedMeasuredRemainderViolationByFieldDto
import ru.bz.baltic_shipyard_inventory_service.presentation.validation.annotation.NotEmpty


@Schema(description="schema.inventoriedmeasuredremaindersdto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class InventoriedMeasuredRemainderValidationErrorResponse(
    @Schema(
        description = "schema.measuredremainder.id.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "R202011040000000009"
    )
    @NotEmpty
    @JsonProperty(JsonFields.ID)
    val id: String,

    @Schema(
        description="schema.measuredremainder.sequence.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example="10"
    )
    @get:Min(message = "code={error.1005.code};description={error.1005.description}", value = 1)
    @get:Max(message = "code={error.1004.code};description={error.1004.description}", value = 1_000_000_000)
    @JsonProperty(JsonFields.SEQUENCE)
    val sequence: Int,

    @Schema(
        description="schema.measuredremainder.status.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="4"
    )
    @get:Min(message = "code={error.1007.code};description={error.1007.description}", value = 1)
    @get:Max(message = "code={error.1007.code};description={error.1007.description}", value = 4)
    @JsonProperty(JsonFields.STATUS)
    val status: Int? = null,

    @Schema(
        description="schema.measuredremainder.comment.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="\u0441/\u0437 \u2116 12-3877 \u043e\u0442 18.11.2020"
    )
    @NotEmpty
    @JsonProperty(JsonFields.COMMENT)
    val comment: String? = null,

    @Schema(
        description="schema.measuredremainder.length.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="22.2"
    )
    @get:Min(message = "code={error.1005.code};description={error.1005.description}", value = 1)
    @get:Max(message = "code={error.1004.code};description={error.1004.description}", value = 1_000_000_000)
    @JsonProperty(JsonFields.LENGTH)
    val length: Double? = null,

    @Schema(
        description="schema.measuredremainder.width.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="30"
    )
    @get:Min(message = "code={error.1005.code};description={error.1005.description}", value = 1)
    @get:Max(message = "code={error.1004.code};description={error.1004.description}", value = 1_000_000_000)
    @JsonProperty(JsonFields.WIDTH)
    val width: Double? = null,

    @Schema(description="schema.abortreasondto.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty(JsonFields.ABORT_REASON)
    val abortReason: AbortReasonDto<InventoriedMeasuredRemainderViolationByFieldDto>? = null
)
