package ru.bz.baltic_shipyard_inventory_service.applications.usecases.warehouse.container

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.repository.WarehouseRepository

@Component
class ContainerExistsUseCase (
    private val containerRepository: WarehouseRepository
){
    operator fun invoke(code: String): Boolean = containerRepository.findContainerByCode(code) != null
}