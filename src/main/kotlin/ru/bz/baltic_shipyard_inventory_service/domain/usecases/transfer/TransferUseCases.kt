package ru.bz.baltic_shipyard_inventory_service.domain.usecases.transfer

import org.springframework.stereotype.Component


@Component
data class TransferUseCases(
    val transferItemByLocations: UseCaseTransferItemByLocations,
    val findTransferredItemByLocationsByTransactionId: UseCaseFindTransferredItemByLocationsByTransactionId,
    val updateTransferredItemByLocations: UseCaseUpdateTransferredItemByLocations
)
