package ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.DecimalMin
import ru.bz.baltic_shipyard_inventory_service.domain.model.command.measuredremainder.UpdateMeasuredRemainderDimensionsCommand
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.dimensions.Dimensions
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.measuredremainder.MeasuredRemainderDimensions
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields


@Schema(description = "schema.dimensions.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class MeasuredRemainderDimensionsDto(
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

fun MeasuredRemainderDimensionsDto.toUpdateMeasuredRemainderDimensionsCommand() = UpdateMeasuredRemainderDimensionsCommand(
    length = length,
    width = width,
    thickness = thickness
)


fun MeasuredRemainderDimensions.toMeasuredRemainderDimensionsDto() = MeasuredRemainderDimensionsDto(
    length = length,
    width = width,
    thickness = thickness
)