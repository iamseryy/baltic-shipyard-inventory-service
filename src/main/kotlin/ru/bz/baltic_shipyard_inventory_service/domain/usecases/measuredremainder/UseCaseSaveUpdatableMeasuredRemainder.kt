package ru.bz.baltic_shipyard_inventory_service.domain.usecases.measuredremainder

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.UpdatableMeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.domain.repository.MeasuredRemainderRepository


@Component
class UseCaseSaveUpdatableMeasuredRemainder (
    private val measuredRemainderRepository: MeasuredRemainderRepository
) {
    operator fun invoke(updatableMeasuredRemainder: UpdatableMeasuredRemainder): UpdatableMeasuredRemainder =
        measuredRemainderRepository.saveUpdatableMeasuredRemainder(updatableMeasuredRemainder)
}