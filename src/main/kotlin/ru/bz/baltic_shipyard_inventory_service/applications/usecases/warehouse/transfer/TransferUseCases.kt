package ru.bz.baltic_shipyard_inventory_service.applications.usecases.warehouse.transfer

import org.springframework.stereotype.Component

@Component
data class TransferUseCases(
    val transferItemByLocations: TransferItemByLocationsUseCase,
    val findTransferredItemByLocationsByTransactionId: FindTransferredItemByLocationsByTransactionIdUseCase,
    val updateTransferredItemByLocations: UpdateTransferredItemByLocationsUseCase,
    val validateTransferItemByLocation: ValidateTransferItemByLocationUseCase
)