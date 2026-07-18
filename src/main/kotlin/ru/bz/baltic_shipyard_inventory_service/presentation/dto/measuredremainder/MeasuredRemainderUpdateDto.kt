package ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.DecimalMin
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemainderStatus
import ru.bz.baltic_shipyard_inventory_service.domain.model.command.measuredremainder.UpdateMeasuredRemainderCommand
import ru.bz.baltic_shipyard_inventory_service.domain.model.command.measuredremainder.UpdateMeasuredRemainderDimensionsCommand
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.validation.annotation.NotEmpty


@Schema(description = "schema.measuredremainderupdatedto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class MeasuredRemainderUpdateDto (
    @Schema(
        description="schema.measuredremainder.status.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        allowableValues = ["CREATED", "IN_COTZ", "IN_MSCH", "USED", "ON_CONSIGNMENT", "DELETED"],
        type = "String",
        example="USED"
    )
//    @EnumTypeSubset(
//        anyOf = [MeasuredRemainderStatus.CREATED, MeasuredRemainderStatus.IN_COTZ, MeasuredRemainderStatus.IN_MSCH],
//        message = "code={error.1009.code};description={error.1009.description} CREATED, IN_COTZ, IN_MSCH, USED, ON_CONSIGNMENT, DELETED"
//    )
//    @ValidEnumWithAllowed(
//        allowedValues = ["CREATED", "IN_COTZ", "IN_MSCH", "USED", "ON_CONSIGNMENT", "DELETED"],
//        message = "code={error.1009.code};description={error.1009.description} CREATED, IN_COTZ, IN_MSCH, USED, ON_CONSIGNMENT, DELETED"
//    )
    @JsonProperty(JsonFields.STATUS)
    val status: MeasuredRemainderStatus? = null,

    @Schema(
        description="schema.measuredremainder.comment.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="\u0441/\u0437 \u2116 12-3877 \u043e\u0442 18.11.2020"
    )
    @NotEmpty
    @JsonProperty(JsonFields.COMMENT)
    val comment: String? = null,

    @Schema(
        description = "schema.length.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "10.1"
    )
    @get:DecimalMin(message = "code={error.1011.code};description={error.1011.description}", value = "0.001")
    @get:DecimalMax(message = "code={error.1004.code};description={error.1004.description}", value = "1000000000")
    @JsonProperty(JsonFields.LENGTH) val length: Double? = null,

    @Schema(
        description = "schema.width.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "20.2"
    )
    @get:DecimalMin(message = "code={error.1011.code};description={error.1011.description}", value = "0.001")
    @get:DecimalMax(message = "code={error.1004.code};description={error.1004.description}", value = "1000000000")
    @JsonProperty(JsonFields.WIDTH) val width: Double? = null,

    @Schema(
        description = "schema.thickness.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "1.5"
    )
    @get:DecimalMin(message = "code={error.1011.code};description={error.1011.description}", value = "0.001")
    @get:DecimalMax(message = "code={error.1004.code};description={error.1004.description}", value = "1000000000")
    @JsonProperty(JsonFields.THICKNESS) val thickness: Double? = null,
)

fun MeasuredRemainderUpdateDto.toUpdateMeasuredRemainderCommand(id: String) = UpdateMeasuredRemainderCommand(
    id = id,
    status = status,
    comment = comment,
    dimensions = UpdateMeasuredRemainderDimensionsCommand(
        length = length,
        width = width,
        thickness = thickness
    )
)