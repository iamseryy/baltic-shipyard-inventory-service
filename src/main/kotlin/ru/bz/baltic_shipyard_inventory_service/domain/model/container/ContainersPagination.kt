package ru.bz.baltic_shipyard_inventory_service.domain.model.container

data class ContainersPagination(
    val containers: List<Container>,
    val page: Int,
    val pageSize: Int
)
