package ru.bz.baltic_shipyard_inventory_service.infrastructure.repository.impl

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.barcode.BarCodeData
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.repository.RabbitRepository
import ru.bz.baltic_shipyard_inventory_service.domain.repository.BarCodeRepository


@Component
class BarCodeRepositoryImpl(
    private val rabbitRepository: RabbitRepository
): BarCodeRepository {
    override fun findDataByBarCode(code: String): BarCodeData? = rabbitRepository.findBarCodeDataByCode(code)
}