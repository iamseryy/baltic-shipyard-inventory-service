package ru.bz.baltic_shipyard_inventory_service.applications.usecases.transaction

import org.springframework.stereotype.Component

@Component
data class TransactionUseCases(
    val findById: FindTransactionByIdUseCase,
    val updateTransaction: UpdateTransactionUseCase,
    val updateTransactionById: UpdateTransactionByIdUseCase
)