package ru.bz.baltic_shipyard_inventory_service.domain.usecases.transaction

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.Transaction
import ru.bz.baltic_shipyard_inventory_service.domain.exception.ResourceNotFoundException
import ru.bz.baltic_shipyard_inventory_service.domain.repository.TransactionRepository


@Component
class UseCaseFindTransactionById (
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke(id: Int): Transaction =
        transactionRepository.findById(id) ?: throw ResourceNotFoundException()
}