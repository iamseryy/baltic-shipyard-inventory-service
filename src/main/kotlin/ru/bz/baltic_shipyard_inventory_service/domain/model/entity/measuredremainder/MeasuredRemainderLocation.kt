package ru.bz.baltic_shipyard_inventory_service.domain.model.entity.measuredremainder

data class MeasuredRemainderLocation (
    var warehouseCode: String,
    var binCode: String,
    var sequence: Int
) {
    fun update(newWarehouseCode: String, newBinCode: String) {
        warehouseCode = newWarehouseCode
        binCode = newBinCode
    }
}