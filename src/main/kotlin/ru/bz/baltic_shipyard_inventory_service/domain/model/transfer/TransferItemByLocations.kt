package ru.bz.baltic_shipyard_inventory_service.domain.model.transfer

data class TransferItemByLocations(
    override val warehouseCode: String,
    override val binCodeSource: String,
    override val binCodeTarget: String,
    override val itemCode: String,
    override val lotCode: String,
    override val quantity: Double,
    val userLogin: String
): TransferItemByLocationsBase
