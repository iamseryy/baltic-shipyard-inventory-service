package ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.measuredremainder

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.ViolationDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.toViolationDto
import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.ViolationByFields


@Schema(description="schema.violationbyfieldsdto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class UpdatableMeasuredRemainderViolationByFieldDto(
    @Schema(description="schema.measuredremainder.remainder.desc")
    @JsonProperty(JsonFields.REMAINDER) val remainderViolation: ViolationDto? = null,

    @Schema(description="schema.project.desc")
    @JsonProperty(JsonFields.PROJECT) val projectViolation: ViolationDto? = null,

    @Schema(description="schema.measuredremainder.material.desc")
    @JsonProperty(JsonFields.MATERIAL) val materialViolation: ViolationDto? = null,

    @Schema(description="schema.warehouse.desc")
    @JsonProperty(JsonFields.WAREHOUSE) val warehouseViolation: ViolationDto? = null,

    @Schema(description="schema.location.desc")
    @JsonProperty(JsonFields.LOCATION) val locationViolation: ViolationDto? = null,

    @Schema(description="schema.measuredremainder.sequence.desc")
    @JsonProperty(JsonFields.SEQUENCE) val sequenceViolation: ViolationDto? = null,

    @Schema(description="schema.measuredremainder.status.desc")
    @JsonProperty(JsonFields.STATUS) val statusViolation: ViolationDto? = null,

    @Schema(description="schema.measuredremainder.comment.desc")
    @JsonProperty(JsonFields.COMMENT) val commentViolation: ViolationDto? = null,

    @Schema(description="schema.measuredremainder.length.desc")
    @JsonProperty(JsonFields.LENGTH) val lengthViolation: ViolationDto? = null,

    @Schema(description="schema.measuredremainder.width.desc")
    @JsonProperty(JsonFields.WIDTH) val widthViolation: ViolationDto? = null,

    @Schema(description="schema.measuredremainder.depth.desc")
    @JsonProperty(JsonFields.DEPTH) val depthViolation: ViolationDto? = null
)

fun ViolationByFields.toUpdatableMeasuredRemainderViolationByFieldDto() = UpdatableMeasuredRemainderViolationByFieldDto (
    remainderViolation = remainderViolation?.toViolationDto(),
    projectViolation = projectViolation?.toViolationDto(),
    materialViolation = materialViolation?.toViolationDto(),
    warehouseViolation = warehouseViolation?.toViolationDto(),
    locationViolation = locationViolation?.toViolationDto(),
    sequenceViolation = sequenceViolation?.toViolationDto(),
    statusViolation = statusViolation?.toViolationDto(),
    commentViolation = commentViolation?.toViolationDto(),
    lengthViolation = lengthViolation?.toViolationDto(),
    widthViolation = widthViolation?.toViolationDto(),
    depthViolation = depthViolation?.toViolationDto()
)