package ru.bz.baltic_shipyard_inventory_service.infrastructure.repository.specification

import org.springframework.data.jpa.domain.Specification


/**
 * Generic builder to compose specifications.
 *
 * @param T the entity type referenced by the root
 *
 * @author Sergei Perminov
 * @version 1.0
 */
class SpecificationBuilder <T> (
    private val anySpecification: (SearchCriteria<Comparable<Any>>) -> Specification<T>
) {
    private val parameters: MutableList<SearchCriteria<Comparable<Any>>> = mutableListOf()

    fun with(key: String, operation: SearchOperation, value: Comparable<Any>?): SpecificationBuilder<T> {
        if(value != null) parameters.add(SearchCriteria(key, operation, value))
        return this
    }

  fun build(): Specification <T>? {
        if(parameters.isEmpty()) return null

        var specification: Specification<T> = anySpecification(parameters[0])

        parameters.drop(1).forEach{ specification = Specification.where(specification).and(anySpecification(it)) }

        return specification
    }
}