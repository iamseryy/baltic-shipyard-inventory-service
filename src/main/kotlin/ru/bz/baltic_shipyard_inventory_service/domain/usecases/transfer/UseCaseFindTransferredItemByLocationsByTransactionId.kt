package ru.bz.baltic_shipyard_inventory_service.domain.usecases.transfer

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.exception.ResourceNotFoundException
import ru.bz.baltic_shipyard_inventory_service.domain.repository.TransferRepository


@Component
class UseCaseFindTransferredItemByLocationsByTransactionId(
    private val transferRepository: TransferRepository
) {
    operator fun invoke(id: Int) = transferRepository.findTransferredItemByLocationsByTransactionId(id) ?: throw ResourceNotFoundException()
}