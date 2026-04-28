package ru.bz.baltic_shipyard_inventory_service.domain.usecases.barcode

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.repository.BarCodeRepository
import ru.bz.baltic_shipyard_inventory_service.domain.model.barcode.BarCodeData
import ru.bz.baltic_shipyard_inventory_service.domain.exception.ResourceNotFoundException


@Component
class UseCaseFindDataByBarCode(
    private val barCodeRepository: BarCodeRepository
) {
    operator fun invoke(code: String): BarCodeData = barCodeRepository.findDataByBarCode(code) ?: throw ResourceNotFoundException()

}