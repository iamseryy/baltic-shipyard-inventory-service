package ru.bz.baltic_shipyard_inventory_service.infrastructure.repository.specification


enum class SearchOperation {
    EQUAL_TO,
    NEGATION,
    GREATER_THAN,
    GREATER_THAN_OR_EQUAL_TO,
    LESS_THAN,
    LESS_THAN_OR_EQUAL_TO,
    LIKE,
    STARTS_WITH,
    ENDS_WITH,
    CONTAINS
}