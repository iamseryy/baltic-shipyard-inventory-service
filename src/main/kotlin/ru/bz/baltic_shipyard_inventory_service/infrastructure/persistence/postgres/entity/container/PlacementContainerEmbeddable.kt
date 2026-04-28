package ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.container

import jakarta.persistence.Access
import jakarta.persistence.AccessType
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacedContainer
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacementContainer
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.EntityFields


@Embeddable
@Access(AccessType.FIELD)
data class PlacementContainerEmbeddable(
    @field:Column(name = EntityFields.CONTAINER_CODE, nullable = false) val containerCode: String,
    @field:Column(name = EntityFields.BIN_CODE_TARGET, nullable = false) val binCodeTarget: String
)

fun PlacementContainer.toPlacementContainerEmbeddable() = PlacementContainerEmbeddable(
    containerCode = containerCode,
    binCodeTarget = binCodeTarget
)

fun PlacedContainer.toPlacementContainerEmbeddable() = PlacementContainerEmbeddable(
    containerCode = containerCode,
    binCodeTarget = binCodeTarget
)