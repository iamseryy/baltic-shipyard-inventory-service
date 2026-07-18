package ru.bz.baltic_shipyard_inventory_service.applications.usecases.warehouse.container

import org.springframework.stereotype.Component

@Component
data class ContainerUseCases(
    val findContainerByCode: FindContainerByCodeUseCase,
    val findContainersByFilter: FindContainersByFilterUseCase,
    val containerExists: ContainerExistsUseCase,
    val validatePlacementContainer: ValidatePlacementContainerUseCase,
    val placeContainer: PlaceContainerUseCase,
    val findPlacedContainerByTransactionId: FindPlacedContainerByTransactionIdUseCase,
    val updatePlacedContainer: UpdatePlacedContainerUseCase
)