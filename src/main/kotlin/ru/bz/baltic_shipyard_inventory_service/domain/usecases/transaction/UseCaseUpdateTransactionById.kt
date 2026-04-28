package ru.bz.baltic_shipyard_inventory_service.domain.usecases.transaction

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.Transaction
import ru.bz.baltic_shipyard_inventory_service.domain.repository.TransactionRepository


@Component
class UseCaseUpdateTransactionById (
    private val transactionRepository: TransactionRepository
) {
    operator fun invoke(id: Int): Transaction =
        transactionRepository.findById(id).let {
            transactionRepository.updateTransaction(it!!.copy(userLogin = "test for update2"))
        }

}