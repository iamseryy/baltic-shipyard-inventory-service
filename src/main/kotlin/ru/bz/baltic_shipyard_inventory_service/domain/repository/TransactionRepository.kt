package ru.bz.baltic_shipyard_inventory_service.domain.repository


import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchTransactionFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.AbortReason
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.Transaction
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction.TransactionAbortReasonEntity


interface TransactionRepository {
    fun findById(id: Int): Transaction?
    fun updateTransaction(transaction: Transaction): Transaction
    fun saveTransactionAbortReason(transaction: Transaction, abortReason: AbortReason): TransactionAbortReasonEntity
    fun findTransactionsByFilterPagination(filter: SearchTransactionFilter): List<Transaction>
}