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
