package ru.bz.baltic_shipyard_inventory_service.applications.usecases.measuredremainder

import org.springframework.stereotype.Service
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.measuredremainder.MeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.common.errors.ResourceNotFoundException
import ru.bz.baltic_shipyard_inventory_service.domain.repository.MeasuredRemainderRepository


@Service
class GetMeasuredRemainderByIdUseCase (
    private val repository: MeasuredRemainderRepository
) {
    operator fun invoke(id: String): MeasuredRemainder =
        repository.findById(id) ?: throw ResourceNotFoundException()
}