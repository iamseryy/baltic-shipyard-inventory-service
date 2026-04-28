package ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemainderBase
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemainderStatus


@Schema(description="schema.measuredremainderdto.desc")
data class MeasuredRemainderDto (
    @Schema(
        description = "schema.measuredremainder.id.desc",
        example = "R202011040000000009"
    )
    @JsonProperty(JsonFields.ID)
    override val measuredRemainderId: String,

    @Schema(
        description="schema.measuredremainder.remainder.desc",
        example="251-3214"
    )
    @JsonProperty(JsonFields.REMAINDER)
    override val remainder: String,

    @Schema(
        description="schema.project.desc",
        example="057120"
    )
    @JsonProperty(JsonFields.PROJECT)
    override val project: String,

    @Schema(
        description="schema.measuredremainder.material.desc",
        example="\u0421\u04423\u0441\u043f2"
    )
    @JsonProperty(JsonFields.MATERIAL)
    override val material: String,

    @Schema(
        description="schema.warehouse.desc",
        example="R0200"
    )
    @JsonProperty(JsonFields.WAREHOUSE)
    override val warehouse: String,

    @Schema(
        description="schema.location.desc",
        example="01"
    )
    @JsonProperty(JsonFields.LOCATION)
    override val location: String,

    @Schema(
        description="schema.measuredremainder.sequence.desc",
        example="10"
    )
    @JsonProperty(JsonFields.SEQUENCE)
    override val sequence: Int,

    @Schema(
        description="schema.measuredremainder.status.desc",
        example="4"
    )
    @JsonProperty(JsonFields.STATUS)
    override val status: MeasuredRemainderStatus,

    @Schema(
        description="schema.measuredremainder.comment.desc",
        example="\u0441/\u0437 \u2116 12-3877 \u043e\u0442 18.11.2020"
    )
    @JsonProperty(JsonFields.COMMENT)
    override val comment: String,

    @Schema(
        description="schema.measuredremainder.length.desc",
        example="22.2"
    )
    @JsonProperty(JsonFields.LENGTH)
    override val length: Double,

    @Schema(
        description="schema.measuredremainder.width.desc",
        example="30"
    )
    @JsonProperty(JsonFields.WIDTH)
    override val width: Double,

    @Schema(
        description="schema.measuredremainder.depth.desc",
        example="15"
    )
    @JsonProperty(JsonFields.DEPTH)
    override val depth: Double
): MeasuredRemainderBase

fun MeasuredRemainder.toMeasuredRemainderDto() = MeasuredRemainderDto(
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
    depth = depth
)