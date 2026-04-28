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

class GenericSpecification  <T, V: Comparable<V>> (
    private val criteria: SearchCriteria<V>
): Specification<T> {
    override fun toPredicate(
        root: Root<T>,
        query: CriteriaQuery<*>,
        builder: CriteriaBuilder
    ): Predicate? = when (criteria.operation) {
        SearchOperation.EQUAL_TO -> builder.equal(root.get<Any>(criteria.key), criteria.value)
        SearchOperation.GREATER_THAN_OR_EQUAL_TO -> builder.greaterThanOrEqualTo(root.get(criteria.key), criteria.value)
        SearchOperation.LESS_THAN_OR_EQUAL_TO -> builder.lessThanOrEqualTo(root.get(criteria.key), criteria.value)
        else -> null
    }
}