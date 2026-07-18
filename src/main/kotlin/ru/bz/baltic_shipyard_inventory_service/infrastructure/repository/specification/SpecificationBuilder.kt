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
class SpecificationBuilder<T : Any>(
    private val specificationFactory: (SearchCriteria<*>) -> Specification<T>
) {
    private val parameters: MutableList<SearchCriteria<*>> = mutableListOf()

    fun with(key: String, operation: SearchOperation, value: Comparable<*>?): SpecificationBuilder<T> {
        if (value != null) {
            parameters.add(SearchCriteria(key, operation, value))
        }
        return this
    }

    fun build(): Specification<T>? {
        if (parameters.isEmpty()) return null

        var specification: Specification<T> = specificationFactory(parameters[0])

        parameters.drop(1).forEach {
            specification = Specification.where(specification).and(specificationFactory(it))
        }

        return specification
    }
}