package ru.bz.baltic_shipyard_inventory_service.domain.usecases.container

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchContainerFilter
import ru.bz.baltic_shipyard_inventory_service.domain.exception.ResourceNotFoundException
import ru.bz.baltic_shipyard_inventory_service.domain.repository.ContainerRepository


@Component
class UseCaseFindContainersByFilter (
    private val containerRepository: ContainerRepository
){
    operator fun invoke(filter: SearchContainerFilter) =
        containerRepository.findContainersByFilter(filter)  ?: throw ResourceNotFoundException()
}