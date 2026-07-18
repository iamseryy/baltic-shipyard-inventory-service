package ru.bz.baltic_shipyard_inventory_service.infrastructure.repository.specification

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification

/**
 * Generic implementation of Specification in the sense of Domain Driven Design.
 *
 * @param T the entity type referenced by the root
 * @param V the type of SearchCriteria value
 *
 * @author Sergei Perminov
 * @version 1.0
 */

class GenericSpecification<T : Any>(
    private val criteria: SearchCriteria<*>
) : Specification<T> {

    @Suppress("UNCHECKED_CAST")
    override fun toPredicate(
        root: Root<T>,
        query: CriteriaQuery<*>,
        builder: CriteriaBuilder
    ): Predicate? {
        val path = root.get<Comparable<Any>>(criteria.key)
        val value = criteria.value as Comparable<Any>

        return when (criteria.operation) {
            SearchOperation.EQUAL_TO -> builder.equal(path, value)
            SearchOperation.GREATER_THAN_OR_EQUAL_TO -> builder.greaterThanOrEqualTo(path, value)
            SearchOperation.LESS_THAN_OR_EQUAL_TO -> builder.lessThanOrEqualTo(path, value)
            else -> builder.conjunction()
        }
    }
}