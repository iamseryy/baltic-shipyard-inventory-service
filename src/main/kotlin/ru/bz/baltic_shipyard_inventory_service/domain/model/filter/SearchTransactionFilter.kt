package ru.bz.baltic_shipyard_inventory_service.domain.model.filter

import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.TransactionStatus
import java.time.LocalDateTime

data class SearchTransactionFilter(
    val id: Int?,
    val createdFrom: LocalDateTime?,
    val createdTo: LocalDateTime?,
    val status: TransactionStatus?,
    val userLogin: String?,
    val page: Int,
    val pageSize: Int
)
