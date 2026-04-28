package ru.bz.baltic_shipyard_inventory_service.domain.usecases.measuredremainder

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemainderForUpdate
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.UpdatableMeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.domain.repository.MeasuredRemainderRepository


@Component
class UseCaseSaveForUpdate (
    private val measuredRemainderRepository: MeasuredRemainderRepository
) {
    operator fun invoke(measuredRemainder: MeasuredRemainderForUpdate): UpdatableMeasuredRemainder =
        measuredRemainderRepository.saveForUpdate(measuredRemainder)
}