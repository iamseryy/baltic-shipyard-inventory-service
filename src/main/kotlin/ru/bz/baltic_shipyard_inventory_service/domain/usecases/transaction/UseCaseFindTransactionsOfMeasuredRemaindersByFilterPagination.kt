package ru.bz.baltic_shipyard_inventory_service.domain.usecases.transaction

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchTransactionFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.measuredremainder.TransactionOfUpdatableMeasuredRemainders
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.measuredremainder.TransactionsOfUpdatableMeasuredRemaindersPagination
import ru.bz.baltic_shipyard_inventory_service.domain.repository.MeasuredRemainderRepository
import ru.bz.baltic_shipyard_inventory_service.domain.repository.TransactionRepository


@Component
class UseCaseFindTransactionsOfMeasuredRemaindersByFilterPagination (
    private val transactionRepository: TransactionRepository,
    private val measuredRemainderRepository: MeasuredRemainderRepository
) {
    operator fun invoke(filter: SearchTransactionFilter): TransactionsOfUpdatableMeasuredRemaindersPagination =
        transactionRepository.findTransactionsByFilterPagination(filter)
            .let {transactions ->
                measuredRemainderRepository.findUpdatableMeasuredRemaindersByTransactions(transactions)
            }.let {updatableMeasuredRemainders ->
                updatableMeasuredRemainders
                    .sortedBy { it.transaction.id }
                    .groupBy { updateMeasuredRemainders -> updateMeasuredRemainders.transaction }
                    .map {
                        TransactionOfUpdatableMeasuredRemainders(
                            id = it.key.id,
                            created = it.key.created,
                            status = it.key.status,
                            userLogin = it.key.userLogin,
                            measuredRemainders = it.value
                        )
                    }
            }.let {transactionsOfUpdatableMeasuredRemainders ->
                TransactionsOfUpdatableMeasuredRemaindersPagination(
                    transactionsOfUpdatableMeasuredRemainders,
                    filter.page,
                    filter.pageSize
                )
            }
}