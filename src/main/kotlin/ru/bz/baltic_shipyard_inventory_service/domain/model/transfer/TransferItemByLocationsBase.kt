package ru.bz.baltic_shipyard_inventory_service.domain.model.transfer

interface TransferItemByLocationsBase {
    val warehouseCode: String
    val binCodeSource: String
    val binCodeTarget: String
    val itemCode: String
    val lotCode: String
    val quantity: Double
}