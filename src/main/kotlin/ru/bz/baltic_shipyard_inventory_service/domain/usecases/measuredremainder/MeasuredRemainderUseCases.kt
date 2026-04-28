package ru.bz.baltic_shipyard_inventory_service.domain.usecases.measuredremainder

import org.springframework.stereotype.Component


@Component
data class MeasuredRemainderUseCases(
    val findByFilter: UseCaseFindMeasuredRemainderByFilter,
    val findById: UseCaseFindMeasuredRemainderById,
    val findUpdatableMeasuredRemaindersByTransactionId: UseCaseFindUpdatableMeasuredRemaindersByTransactionId,
    val saveForUpdate: UseCaseSaveForUpdate,
    val saveForInventory: UseCaseSaveForInventory,
    val saveUpdatableMeasuredRemainder: UseCaseSaveUpdatableMeasuredRemainder,
    val saveInventoriedMeasuredRemainders: UseCaseSaveInventoriedMeasuredRemainders
)
