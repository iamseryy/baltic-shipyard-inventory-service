package ru.bz.baltic_shipyard_inventory_service.infrastructure.repository

import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.barcode.BarCodeData
import ru.bz.baltic_shipyard_inventory_service.domain.repository.BarCodeRepository
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.outbound.BarCodeRabbitOutbound

@Component
class CompositeBarCodeRepository(
    private val barCodeRabbitOutbound: BarCodeRabbitOutbound
): BarCodeRepository {
    override fun findDataByBarCode(code: String): BarCodeData? = barCodeRabbitOutbound.findBarCodeDataByCode(code)
}