package ru.bz.baltic_shipyard_inventory_service.applications.usecases.warehouse.transfer

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.bz.baltic_shipyard_inventory_service.common.errors.ResourceNotFoundException
import ru.bz.baltic_shipyard_inventory_service.domain.repository.WarehouseRepository

@Component
class FindTransferredItemByLocationsByTransactionIdUseCase(
    private val warehouseRepository: WarehouseRepository
) {
    operator fun invoke(id: Int) = warehouseRepository.findTransferredItemByLocationsByTransactionId(id) ?: throw ResourceNotFoundException()
}