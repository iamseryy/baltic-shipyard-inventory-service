package ru.bz.baltic_shipyard_inventory_service.infrastructure.repository.specification


/**
 * @version
 */
data class SearchCriteria <T: Comparable<T>> (
    val key: String,
    val operation: SearchOperation,
    val value: T
)
