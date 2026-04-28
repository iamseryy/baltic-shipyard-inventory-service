package ru.bz.baltic_shipyard_inventory_service.domain.usecases.transfer

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferItemByLocations
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferredItemByLocations
import ru.bz.baltic_shipyard_inventory_service.domain.repository.TransferRepository


@Component
class UseCaseTransferItemByLocations(
    private val transferRepository: TransferRepository
) {
    operator fun invoke(transferItemByLocations: TransferItemByLocations): TransferredItemByLocations =
        transferRepository.transferItemByLocations(transferItemByLocations)
}