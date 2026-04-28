package ru.bz.baltic_shipyard_inventory_service.domain.usecases.transaction

import org.springframework.stereotype.Component


@Component
data class TransactionUseCases(
    val findById: UseCaseFindTransactionById,
    val updateTransaction: UseCaseUpdateTransaction,
    val findTransactionsOfMeasuredRemaindersByFilterPagination: UseCaseFindTransactionsOfMeasuredRemaindersByFilterPagination,
    val updateTransactionById: UseCaseUpdateTransactionById
)
