package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.container

import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacedContainer
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider


data class PlacementContainerDto (
    @field:JsonProperty(JsonFieldsProvider.CONTAINER_CODE) val containerCode: String,
    @field:JsonProperty(JsonFieldsProvider.BIN_CODE) val binCodeTarget: String
)


fun PlacedContainer.toPlacementContainerDto() = PlacementContainerDto(
    containerCode = containerCode,
    binCodeTarget = binCodeTarget
)