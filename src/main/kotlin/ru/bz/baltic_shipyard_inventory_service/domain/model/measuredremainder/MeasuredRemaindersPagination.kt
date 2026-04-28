package ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder

data class MeasuredRemaindersPagination(
    val measuredRemainders: List<MeasuredRemainder>,
    val page: Int,
    val pageSize: Int
)
