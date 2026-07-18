package ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.common.page

data class DomainPageDto<T>(
    val content: List<T>,
    val totalElements: Long,
    val number: Int,
    val size: Int
)
