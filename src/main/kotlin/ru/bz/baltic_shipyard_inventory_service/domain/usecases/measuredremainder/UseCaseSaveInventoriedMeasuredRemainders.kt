package ru.bz.baltic_shipyard_inventory_service.domain.usecases.measuredremainder

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.InventoriedMeasuredRemainders
import ru.bz.baltic_shipyard_inventory_service.domain.repository.MeasuredRemainderRepository


@Component
class UseCaseSaveInventoriedMeasuredRemainders(
    private val measuredRemainderRepository: MeasuredRemainderRepository
) {
    operator fun invoke(inventoriedMeasuredRemainders: InventoriedMeasuredRemainders): InventoriedMeasuredRemainders =
        measuredRemainderRepository.saveInventoriedMeasuredRemainder(inventoriedMeasuredRemainders)
}