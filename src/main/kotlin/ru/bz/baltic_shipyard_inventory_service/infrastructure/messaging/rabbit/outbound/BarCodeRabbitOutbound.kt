package ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.outbound

import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.barcode.BarCodeData


interface BarCodeRabbitOutbound {
    fun findBarCodeDataByCode(code: String): BarCodeData?
}