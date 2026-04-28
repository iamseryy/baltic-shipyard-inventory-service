package ru.bz.baltic_shipyard_inventory_service.domain.usecases.container

import org.springframework.stereotype.Component


@Component
data class ContainerUseCases(
    val findContainerByCode: UseCaseFindContainerByCode,
    val findContainersByFilter: UseCaseFindContainersByFilter,
    val containerExists: UseCaseContainerExists,
    val validatePlacementContainer: UseCaseValidatePlacementContainer,
    val placeContainer: UseCasePlaceContainer,
    val findPlacedContainerByTransactionId: UseCaseFindPlacedContainerByTransactionId,
    val updatePlacedContainer: UseCaseUpdatePlacedContainer
)
