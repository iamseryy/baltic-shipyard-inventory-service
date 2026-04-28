package ru.bz.baltic_shipyard_inventory_service.domain.usecases.measuredremainder

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.domain.exception.ResourceNotFoundException
import ru.bz.baltic_shipyard_inventory_service.domain.repository.MeasuredRemainderRepository


@Component
class UseCaseFindMeasuredRemainderById (
    private val measuredRemainderRepository: MeasuredRemainderRepository
) {
    operator fun invoke(id: String): MeasuredRemainder =
        measuredRemainderRepository.findById(id) ?: throw ResourceNotFoundException()
}