package ru.bz.baltic_shipyard_inventory_service.domain.repository

import ru.bz.baltic_shipyard_inventory_service.domain.model.container.Container
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.ContainersPagination
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacedContainer
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacementContainer
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchContainerFilter

interface ContainerRepository {
    fun findContainerByCode(code: String): Container?
    fun findContainersByFilter(filter: SearchContainerFilter): ContainersPagination?
    fun placeContainer(placementContainer: PlacementContainer): PlacedContainer
    fun findPlacedContainerByTransactionId(id: Int): PlacedContainer?
    fun updatePlacedContainer(placedContainer: PlacedContainer): PlacedContainer
}