package ru.bz.baltic_shipyard_inventory_service.domain.repository

import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferItemByLocations
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferredItemByLocations


interface TransferRepository {
    fun transferItemByLocations(transferItemByLocations: TransferItemByLocations): TransferredItemByLocations
    fun updateTransferredItemByLocations(transferredItemByLocations: TransferredItemByLocations): TransferredItemByLocations
    fun findTransferredItemByLocationsByTransactionId(id: Int): TransferredItemByLocations?
}