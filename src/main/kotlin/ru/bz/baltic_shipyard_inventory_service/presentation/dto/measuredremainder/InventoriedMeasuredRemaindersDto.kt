package ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.abortreason.AbortReasonDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.measuredremainder.InventoriedMeasuredRemainderViolationByFieldDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.measuredremainder.InventoriedMeasuredRemaindersViolationByFieldDto
import ru.bz.baltic_shipyard_inventory_service.presentation.validation.annotation.NotEmpty
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemainderStatus


@Schema(description="schema.inventoriedmeasuredremaindersdto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class InventoriedMeasuredRemaindersDto(
    @Schema(
        description = "schema.warehouse.desc",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotEmpty
    @JsonProperty(JsonFields.WAREHOUSE) val warehouse: String,

    @Schema(
        description = "schema.location.desc",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotEmpty
    @JsonProperty(JsonFields.LOCATION) val location: String,

    @Schema(
        description = "schema.inventoriedmeasuredremaindersdto.measuredremainders.desc",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @field:Valid
    @JsonProperty(JsonFields.MEASURED_REMAINDERS) val measuredRemainders: List<InventoriedMeasuredRemainderDto>
)

fun InventoriedMeasuredRemaindersDto.mergeWith(
    measuredRemainders: List<MeasuredRemainder>
) = measuredRemainders.mapIndexed { index, measuredRemainder ->
        MeasuredRemainder(
            measuredRemainderId = this.measuredRemainders[index].id,
            remainder =  measuredRemainder.remainder,
            project = measuredRemainder.project,
            material = measuredRemainder.material,
            warehouse = measuredRemainder.warehouse,
            location = measuredRemainder.location,
            sequence = this.measuredRemainders[index].sequence,
            status = this.measuredRemainders[index].status?.let { MeasuredRemainderStatus.getByNumber(it) } ?: measuredRemainder.status,
            comment = this.measuredRemainders[index].comment ?: measuredRemainder.comment,
            length = this.measuredRemainders[index].length ?: measuredRemainder.length,
            width = this.measuredRemainders[index].width ?: measuredRemainder.width,
            depth = measuredRemainder.depth
        )
    }

fun InventoriedMeasuredRemaindersDto.toInventoriedMeasuredRemaindersValidationErrorResponse(
    abortReasonDtoHeader: AbortReasonDto<InventoriedMeasuredRemaindersViolationByFieldDto>,
    abortReasonDtoLines: List<AbortReasonDto<InventoriedMeasuredRemainderViolationByFieldDto>>
) = InventoriedMeasuredRemaindersValidationErrorResponse (
    warehouse = warehouse,
    location = location,
    abortReason = abortReasonDtoHeader,
    measuredRemainders = measuredRemainders.mapIndexed {index: Int,  inventoriedMeasuredRemainderDto ->
        inventoriedMeasuredRemainderDto.toInventoriedMeasuredRemainderValidationErrorResponse(
            abortReasonDto = abortReasonDtoLines[index]
        )
    }
)


