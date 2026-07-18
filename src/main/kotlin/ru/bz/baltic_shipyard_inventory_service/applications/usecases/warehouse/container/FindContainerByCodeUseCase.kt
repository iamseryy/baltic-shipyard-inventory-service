package ru.bz.baltic_shipyard_inventory_service.applications.usecases.warehouse.container

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.common.errors.ResourceNotFoundException
import ru.bz.baltic_shipyard_inventory_service.domain.repository.WarehouseRepository

@Component
class FindContainerByCodeUseCase (
    private val containerRepository: WarehouseRepository
) {
        operator fun invoke(code: String) = containerRepository.findContainerByCode(code) ?: throw ResourceNotFoundException()
}