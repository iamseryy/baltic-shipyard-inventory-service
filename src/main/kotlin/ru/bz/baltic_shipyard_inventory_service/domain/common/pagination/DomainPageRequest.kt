package ru.bz.baltic_shipyard_inventory_service.domain.common.pagination

data class DomainPageRequest(
    val number: Int,
    val size: Int
) {
    val offset: Int get() = number * size
}