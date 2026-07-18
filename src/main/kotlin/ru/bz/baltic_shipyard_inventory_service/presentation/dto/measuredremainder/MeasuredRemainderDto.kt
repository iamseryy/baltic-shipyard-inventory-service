package ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.domain.model.command.measuredremainder.UpdateMeasuredRemainderCommand
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.measuredremainder.MeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.measuredremainder.MeasuredRemainderLocation
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemainderStatus
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.location.LocationDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.location.toLocationDto


@Schema(description="schema.measuredremainderdto.desc")
data class MeasuredRemainderDto (
    @Schema(
        description = "schema.measuredremainder.id.desc",
        example = "R202011040000000009"
    )
    @JsonProperty(JsonFields.ID)
    val id: String,

    @Schema(
        description="schema.code.desc",
        example="251-3214"
    )
    @JsonProperty(JsonFields.CODE)
    val code: String,

    @Schema(
        description="schema.project.desc",
        example="057120"
    )
    @JsonProperty(JsonFields.PROJECT_CODE)
    val projectCode: String,

    @Schema(
        description="schema.measuredremainder.material.desc",
        example="\u0421\u04423\u0441\u043f2"
    )
    @JsonProperty(JsonFields.MATERIAL)
    val material: String,

    @Schema(
        description="schema.location.desc"
    )
    @JsonProperty(JsonFields.LOCATION)
    val location: LocationDto,

    @Schema(
        description="schema.measuredremainder.sequence.desc",
        example="10"
    )
    @JsonProperty(JsonFields.SEQUENCE)
    val sequence: Int,

    @Schema(
        description="schema.measuredremainder.status.desc",
        example="4"
    )
    @JsonProperty(JsonFields.STATUS)
    val status: MeasuredRemainderStatus,

    @Schema(
        description="schema.measuredremainder.comment.desc",
        example="\u0441/\u0437 \u2116 12-3877 \u043e\u0442 18.11.2020"
    )
    @JsonProperty(JsonFields.COMMENT)
    val comment: String,

    @Schema(
        description="schema.dimensions.desc"
    )
    @JsonProperty(JsonFields.DIMENSIONS)
    val dimensions: MeasuredRemainderDimensionsDto,

//    @Schema(
//        description="schema.dimensions.desc"
//    )
//    @JsonProperty(JsonFields.INVENTORY_DATE)
//    val inventoryDate: LocalDateTime
)

fun MeasuredRemainder.toMeasuredRemainderDto() = MeasuredRemainderDto(
    id = id,
    code = code,
    projectCode = projectCode,
    material = material,
    location = location.toLocationDto(),
    sequence = location.sequence,
    status = status,
    comment = comment,
    dimensions = dimensions.toMeasuredRemainderDimensionsDto()
    //inventoryDate = inventoryDate
)

fun MeasuredRemainderDto.toUpdateMeasuredRemainderCommand() = UpdateMeasuredRemainderCommand(
    id = id,
    location = MeasuredRemainderLocation(
        warehouseCode = location.warehouseCode,
        binCode = location.binCode,
        sequence = sequence),
    material = material,
    status = status,
    comment = comment,
    dimensions = dimensions.toUpdateMeasuredRemainderDimensionsCommand()
    //inventoryDate = inventoryDate
)
