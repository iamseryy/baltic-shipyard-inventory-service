package ru.bz.baltic_shipyard_inventory_service.domain.repository

import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.barcode.BarCodeData

interface BarCodeRepository {
    fun findDataByBarCode(code: String): BarCodeData?
}