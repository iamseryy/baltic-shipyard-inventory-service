package ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.measuredremainder


data class TransactionsOfUpdatableMeasuredRemaindersPagination(
    val transactions: List<TransactionOfUpdatableMeasuredRemainders>,
    val page: Int,
    val pageSize: Int
)
