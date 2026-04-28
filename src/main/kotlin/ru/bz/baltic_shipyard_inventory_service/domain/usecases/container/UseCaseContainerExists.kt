package ru.bz.baltic_shipyard_inventory_service.domain.usecases.container

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.repository.ContainerRepository


@Component
class UseCaseContainerExists (
    private val containerRepository: ContainerRepository
){
    operator fun invoke(code: String): Boolean = containerRepository.findContainerByCode(code) != null
}