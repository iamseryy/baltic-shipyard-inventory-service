package ru.bz.baltic_shipyard_inventory_service.applications.usecases.barcode

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.common.errors.ResourceNotFoundException
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.barcode.BarCodeData
import ru.bz.baltic_shipyard_inventory_service.domain.repository.BarCodeRepository

@Component
class FindDataByBarCodeUseCase(
    private val barCodeRepository: BarCodeRepository
) {
    operator fun invoke(code: String): BarCodeData = barCodeRepository.findDataByBarCode(code) ?: throw ResourceNotFoundException()

}