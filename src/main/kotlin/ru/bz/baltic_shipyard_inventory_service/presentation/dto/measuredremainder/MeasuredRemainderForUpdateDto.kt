package ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.abortreason.AbortReasonDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.measuredremainder.MeasuredRemainderForUpdateViolationByFieldDto
import ru.bz.baltic_shipyard_inventory_service.presentation.validation.annotation.NotEmpty
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemainderForUpdate
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemainderStatus


@Schema(description = "schema.measuredremainderforupdatedto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class MeasuredRemainderForUpdateDto (
    @Schema(
        description = "schema.measuredremainder.id.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "R202011040000000009"
    )
    @NotEmpty
    @JsonProperty(JsonFields.ID)
    val id: String,

    @Schema(
        description="schema.measuredremainder.status.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d"
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
    @get:Min(message = "code={error.1005.code};description={error.1005.description}", value = 0)
    @get:Max(message = "code={error.1004.code};description={error.1004.description}", value = 1_000_000_000)
    @JsonProperty(JsonFields.WIDTH)
    val width: Double? = null
)

fun MeasuredRemainderForUpdateDto.mergeWith(
    measuredRemainder: MeasuredRemainder,
    userLogin: String
) = MeasuredRemainderForUpdate(
    measuredRemainderId = id,
    remainder = measuredRemainder.remainder,
    project = measuredRemainder.project,
    material = measuredRemainder.material,
    warehouse = measuredRemainder.warehouse,
    location = measuredRemainder.location,
    sequence = measuredRemainder.sequence,
    status = MeasuredRemainderStatus.getByNumber(status ?: measuredRemainder.status.number),
    comment = comment ?: measuredRemainder.comment,
    length = length ?: measuredRemainder.length,
    width = width ?: measuredRemainder.width,
    depth = measuredRemainder.depth,
    userLogin = userLogin
)

fun MeasuredRemainderForUpdateDto.toMeasuredRemainderForUpdateValidationErrorResponse(
    abortReasonDto: AbortReasonDto<MeasuredRemainderForUpdateViolationByFieldDto>
) = MeasuredRemainderForUpdateValidationErrorResponse(
    id = id,
    status = status,
    comment = comment,
    length = length,
    width = width,
    abortReason = abortReasonDto
)