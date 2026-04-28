package ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transfer

import jakarta.persistence.Access
import jakarta.persistence.AccessType
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferItemByLocations
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferredItemByLocations
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.EntityFields


@Embeddable
@Access(AccessType.FIELD)
data class TransferItemByLocationsEmbeddable(
    @field:Column(name = EntityFields.WAREHOUSE, nullable = false) val warehouseCode: String,
    @field:Column(name = EntityFields.LOCATION_SOURCE, nullable = false) val binCodeSource: String,
    @field:Column(name = EntityFields.LOCATION_TARGET, nullable = false) val binCodeTarget: String,
    @field:Column(name = EntityFields.ITEM, nullable = false) val itemCode: String,
    @field:Column(name = EntityFields.LOT, nullable = false) val lotCode: String,
    @field:Column(name = EntityFields.QUANTITY, nullable = false) val quantity: Double
)

fun TransferItemByLocations.toTransferItemByLocationsEmbeddable() = TransferItemByLocationsEmbeddable(
    warehouseCode = warehouseCode,
    binCodeSource = binCodeSource,
    binCodeTarget = binCodeTarget,
    itemCode = itemCode,
    lotCode = lotCode,
    quantity = quantity
)

fun TransferredItemByLocations.toTransferItemByLocationsEmbeddable() = TransferItemByLocationsEmbeddable(
    warehouseCode = warehouseCode,
    binCodeSource = binCodeSource,
    binCodeTarget = binCodeTarget,
    itemCode = itemCode,
    lotCode = lotCode,
    quantity = quantity
)

