package ru.bz.baltic_shipyard_inventory_service.applications.usecases.measuredremainder

import org.springframework.stereotype.Service
import ru.bz.baltic_shipyard_inventory_service.common.errors.ResourceNotFoundException
import ru.bz.baltic_shipyard_inventory_service.domain.repository.MeasuredRemainderRepository

@Service
class GetBinCodesByWarehouseCodeUseCase(
    private val repository: MeasuredRemainderRepository
) {
    operator fun invoke(warehouseCode: String): List<String> =
        repository.findBinCodesByWarehouseCode(warehouseCode)?: throw ResourceNotFoundException()
}

