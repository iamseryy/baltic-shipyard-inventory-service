package ru.bz.baltic_shipyard_inventory_service.infrastructure.repository.impl

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchTransactionFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.Transaction
import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.AbortReason
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.dto.abortreason.toAbortReasonDbDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction.TransactionAbortReasonEntity
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction.TransactionEntity
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction.toTransaction
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction.toTransactionEntity
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.repository.TransactionAbortReasonJpaRepository
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.repository.TransactionJpaRepository
import ru.bz.baltic_shipyard_inventory_service.domain.repository.TransactionRepository
import ru.bz.baltic_shipyard_inventory_service.infrastructure.repository.specification.GenericSpecification
import ru.bz.baltic_shipyard_inventory_service.infrastructure.repository.specification.SearchOperation
import ru.bz.baltic_shipyard_inventory_service.infrastructure.repository.specification.SpecificationBuilder
import kotlin.jvm.optionals.getOrNull


@Service
class TransactionRepositoryImpl (
    private val transactionJpaRepository: TransactionJpaRepository,
    private val transactionAbortReasonJpaRepository: TransactionAbortReasonJpaRepository
): TransactionRepository {

    override fun findById(id: Int): Transaction? =
        transactionJpaRepository.findById(id).getOrNull()?.toTransaction()

    override fun updateTransaction(transaction: Transaction): Transaction =
        transaction.toTransactionEntity().let {transactionEntity ->
            transactionJpaRepository.save(transactionEntity)
        }.toTransaction()



    override fun saveTransactionAbortReason(transaction: Transaction, abortReason: AbortReason) =
        transactionAbortReasonJpaRepository.save(
            TransactionAbortReasonEntity(
                abortReason = abortReason.toAbortReasonDbDto()
            )
        )

    override fun findTransactionsByFilterPagination(
        filter: SearchTransactionFilter
    ): List<Transaction> {

        val builder = SpecificationBuilder<TransactionEntity> { searchCriteria ->
            GenericSpecification(searchCriteria)
        }

        val spec: Specification<TransactionEntity>? = builder
            .with("id", SearchOperation.EQUAL_TO, filter.id as Comparable<Any>?)
            .with("status", SearchOperation.EQUAL_TO, filter.status as Comparable<Any>?)
            .with("created", SearchOperation.GREATER_THAN_OR_EQUAL_TO, filter.createdFrom as Comparable<Any>?)
            .with("created", SearchOperation.LESS_THAN_OR_EQUAL_TO, filter.createdTo as Comparable<Any>?)
            .with("userLogin", SearchOperation.EQUAL_TO, filter.userLogin as Comparable<Any>?)
            .build()

        val sortedById = PageRequest.of(filter.page, filter.pageSize, Sort.by("id").ascending())

        val transactions =
            if(spec != null) transactionJpaRepository.findAll(spec, sortedById) else transactionJpaRepository.findAll(sortedById)

        return transactions.map { it.toTransaction() }.toList()

    }
}