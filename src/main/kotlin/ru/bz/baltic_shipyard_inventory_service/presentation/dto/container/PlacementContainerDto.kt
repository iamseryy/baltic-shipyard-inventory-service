package ru.bz.baltic_shipyard_inventory_service.presentation.dto.container

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.validation.annotation.NotEmpty
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacementContainer


@Schema(description = "schema.placement.container.desc")
@JsonInclude(JsonInclude.Include.ALWAYS)
data class PlacementContainerDto(
    @Schema(
        description = "schema.container.code.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "R020000013"
    )
    @NotEmpty
    @JsonProperty(JsonFields.CONTAINER_CODE) val containerCode: String,

    @Schema(
        description = "schema.bin.code.target.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example = "20003"
    )
    @NotEmpty
    @JsonProperty(JsonFields.BIN_CODE_TARGET) val binCodeTarget: String
)


fun PlacementContainerDto.toPlacementContainer(userLogin: String) = PlacementContainer(
    containerCode = containerCode,
    binCodeTarget = binCodeTarget,
    userLogin = userLogin
)
