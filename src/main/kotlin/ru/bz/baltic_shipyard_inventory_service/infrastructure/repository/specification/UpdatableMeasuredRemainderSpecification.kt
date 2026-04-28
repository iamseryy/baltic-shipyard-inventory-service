package ru.bz.baltic_shipyard_inventory_service.infrastructure.repository.specification

import jakarta.persistence.criteria.*
import org.springframework.data.jpa.domain.Specification
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.measuredremainder.UpdatableMeasuredRemainderEntity
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction.TransactionEntity


class UpdatableMeasuredRemainderSpecification <T: Comparable<T>>(
    private val criteria: SearchCriteria<T>
): Specification<UpdatableMeasuredRemainderEntity>  {
    override fun toPredicate(
        root: Root<UpdatableMeasuredRemainderEntity>,
        query: CriteriaQuery<*>,
        builder: CriteriaBuilder
    ): Predicate? {
        val transactions: Join<TransactionEntity, UpdatableMeasuredRemainderEntity> = root.join("transaction")

        return when (criteria.operation) {
                SearchOperation.EQUAL_TO -> builder.equal(transactions.get<Any>(criteria.key), criteria.value)
                SearchOperation.GREATER_THAN_OR_EQUAL_TO -> builder.greaterThanOrEqualTo(transactions.get(criteria.key), criteria.value)
                SearchOperation.LESS_THAN_OR_EQUAL_TO -> builder.lessThanOrEqualTo(transactions.get(criteria.key), criteria.value)
                else -> null
            }
        }
}