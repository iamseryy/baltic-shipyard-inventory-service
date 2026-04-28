package ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.abortreason.AbortReasonDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.abortreason.toAbortReasonDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.measuredremainder.UpdatableMeasuredRemainderViolationByFieldDto
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemainderBase
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemainderStatus
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.UpdatableMeasuredRemainder

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description="schema.updatablemeasuredremainderdto.desc")
data class UpdatableMeasuredRemainderDto (
    @Schema(
        description = "schema.measuredremainder.id.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "R202011040000000009"
    )
    @JsonProperty(JsonFields.ID)
    override val measuredRemainderId:  String,

    @Schema(
        description="schema.measuredremainder.remainder.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example="251-3214"
    )
    @JsonProperty(JsonFields.REMAINDER)
    override val remainder: String,

    @Schema(
        description="schema.project.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example="057120"
    )
    @JsonProperty(JsonFields.PROJECT)
    override val project: String,

    @Schema(
        description="schema.measuredremainder.material.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example="\u0421\u04423\u0441\u043f2"
    )
    @JsonProperty(JsonFields.MATERIAL)
    override val material: String,

    @Schema(
        description="schema.warehouse.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example="R0200"
    )
    @JsonProperty(JsonFields.WAREHOUSE)
    override val warehouse: String,

    @Schema(
        description="schema.location.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example="01"
    )
    @JsonProperty(JsonFields.LOCATION)
    override val location: String,

    @Schema(
        description="schema.measuredremainder.sequence.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example="10"
    )
    @JsonProperty(JsonFields.SEQUENCE)
    override val sequence: Int,

    @Schema(
        description="schema.measuredremainder.status.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example="\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d"
    )
    @JsonProperty(JsonFields.STATUS)
    override val status: MeasuredRemainderStatus,

    @Schema(
        description="schema.measuredremainder.comment.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example="\u0441/\u0437 \u2116 12-3877 \u043e\u0442 18.11.2020"
    )
    @JsonProperty(JsonFields.COMMENT)
    override val comment: String,

    @Schema(
        description="schema.measuredremainder.length.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example="22.2"
    )
    @JsonProperty(JsonFields.LENGTH)
    override val length: Double,

    @Schema(
        description="schema.measuredremainder.width.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example="30"
    )
    @JsonProperty(JsonFields.WIDTH)
    override val width: Double,

    @Schema(
        description="schema.measuredremainder.depth.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example="15"
    )
    @JsonProperty(JsonFields.DEPTH)
    override val depth: Double,

    @Schema(
        description="schema.abortreasondto.desc",
    )
    @JsonProperty(JsonFields.ABORT_REASON)
    val abortReason: AbortReasonDto<UpdatableMeasuredRemainderViolationByFieldDto>?
): MeasuredRemainderBase

fun UpdatableMeasuredRemainder.toUpdatableMeasuredRemainderDto() = UpdatableMeasuredRemainderDto(
    measuredRemainderId = measuredRemainderId,
    remainder = remainder,
    project = project,
    material = material,
    warehouse = warehouse,
    location = location,
    sequence = sequence,
    status = status,
    comment = comment,
    length = length,
    width = width,
    depth = depth,
    abortReason = abortReason?.toAbortReasonDto()
)

