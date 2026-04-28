package ru.bz.baltic_shipyard_inventory_service.presentation.dto.container

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacedContainer
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacementContainerAbortReason


@Schema(description = "schema.placed.container.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class PlacedContainerDto(
    @Schema(
        description = "schema.container.code.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "R020000013"
    )
    @JsonProperty(JsonFields.CONTAINER_CODE) val containerCode: String,


    @Schema(
        description = "schema.bin.code.target.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "20003"
    )
    @JsonProperty(JsonFields.BIN_CODE_TARGET) val binCodeTarget: String,


    @Schema(
        description="schema.abortreasondto.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
    )
    @JsonProperty(JsonFields.ABORT_REASON)
    val abortReason: PlacementContainerAbortReasonDto? = null

)

fun PlacedContainer.toPlacedContainerDto() = PlacedContainerDto(
    containerCode = containerCode,
    binCodeTarget = binCodeTarget,
    abortReason = abortReason?.toPlacementContainerAbortReasonDto()
)


fun PlacementContainerAbortReason.toPlacedContainerDto() = PlacedContainerDto(
    containerCode = containerCode,
    binCodeTarget = binCodeTarget,
    abortReason = abortReason?.toPlacementContainerAbortReasonDto()
)
