package ru.bz.baltic_shipyard_inventory_service.domain.usecases.measuredremainder

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchMeasuredRemainderFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemaindersPagination
import ru.bz.baltic_shipyard_inventory_service.domain.exception.ResourceNotFoundException
import ru.bz.baltic_shipyard_inventory_service.domain.repository.MeasuredRemainderRepository


@Component
class UseCaseFindMeasuredRemainderByFilter (
    private val measuredRemainderRepository: MeasuredRemainderRepository
){
    operator fun invoke(filter: SearchMeasuredRemainderFilter): MeasuredRemaindersPagination =
        measuredRemainderRepository.findByFilter(filter) ?: throw ResourceNotFoundException()
}