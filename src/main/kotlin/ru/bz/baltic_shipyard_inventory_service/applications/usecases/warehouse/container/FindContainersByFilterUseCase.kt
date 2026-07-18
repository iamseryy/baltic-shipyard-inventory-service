package ru.bz.baltic_shipyard_inventory_service.applications.usecases.warehouse.container

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.common.errors.ResourceNotFoundException
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchContainerFilter
import ru.bz.baltic_shipyard_inventory_service.domain.repository.WarehouseRepository

@Component
class FindContainersByFilterUseCase (
    private val containerRepository: WarehouseRepository
){
    operator fun invoke(filter: SearchContainerFilter) =
        containerRepository.findContainersByFilter(filter)  ?: throw ResourceNotFoundException()
}