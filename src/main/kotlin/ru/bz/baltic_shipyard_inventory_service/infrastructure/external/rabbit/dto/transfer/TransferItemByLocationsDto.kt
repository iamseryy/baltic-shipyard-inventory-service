package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.transfer

import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferItemByLocationsBase
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferredItemByLocations
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider


data class TransferItemByLocationsDto(
    @field:JsonProperty(JsonFieldsProvider.WAREHOUSE_CODE) override val warehouseCode: String,
    @field:JsonProperty(JsonFieldsProvider.LOCATION_SOURCE) override val binCodeSource: String,
    @field:JsonProperty(JsonFieldsProvider.LOCATION_TARGET) override val binCodeTarget: String,
    @field:JsonProperty(JsonFieldsProvider.ITEM_CODE) override val itemCode: String,
    @field:JsonProperty(JsonFieldsProvider.LOT_CODE) override val lotCode: String,
    @field:JsonProperty(JsonFieldsProvider.QUANTITY) override val quantity: Double,
): TransferItemByLocationsBase

fun TransferredItemByLocations.toTransferItemByLocationsDto() = TransferItemByLocationsDto(
    warehouseCode = warehouseCode,
    binCodeSource = binCodeSource,
    binCodeTarget = binCodeTarget,
    itemCode = itemCode,
    lotCode = lotCode,
    quantity = quantity
)
