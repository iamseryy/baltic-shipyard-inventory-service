package ru.bz.baltic_shipyard_inventory_service.domain.model.container

import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.AbortReason


data class PlacementContainerAbortReason(
    val containerCode: String,
    val binCodeTarget: String,
    val abortReason: AbortReason?
)

fun PlacementContainer.toPlacementContainerAbortReason(abortReason: AbortReason?) = PlacementContainerAbortReason(
    containerCode = containerCode,
    binCodeTarget = binCodeTarget,
    abortReason = abortReason
)
