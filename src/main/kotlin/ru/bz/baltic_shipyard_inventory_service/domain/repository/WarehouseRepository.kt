package ru.bz.baltic_shipyard_inventory_service.domain.repository

import ru.bz.baltic_shipyard_inventory_service.domain.model.container.Container
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.ContainersPagination
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacedContainer
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacementContainer
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchContainerFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchInventoryBalanceFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchLocationDetailShotFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchLotDetailShotFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.item.Item
import ru.bz.baltic_shipyard_inventory_service.domain.model.lot.LotDetail
import ru.bz.baltic_shipyard_inventory_service.domain.model.stock.StockListPagination
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferItemByLocations
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferredItemByLocations
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.location.Bin
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.location.Warehouse

interface WarehouseRepository {
    fun getWarehouseDetail(warehouseCode: String): Warehouse?
    fun getLocationDetailByFilter(filter: SearchLocationDetailShotFilter): Bin?
    fun findContainerByCode(code: String): Container?
    fun findContainersByFilter(filter: SearchContainerFilter): ContainersPagination?
    fun placeContainer(placementContainer: PlacementContainer): PlacedContainer
    fun findPlacedContainerByTransactionId(id: Int): PlacedContainer?
    fun updatePlacedContainer(placedContainer: PlacedContainer): PlacedContainer
    fun findInventoryBalanceByFilter(filter: SearchInventoryBalanceFilter): StockListPagination?
    fun getItemDetail(itemCode: String): Item?
    fun getLotDetail(filter: SearchLotDetailShotFilter): LotDetail?
    fun transferItemByLocations(transferItemByLocations: TransferItemByLocations): TransferredItemByLocations
    fun updateTransferredItemByLocations(transferredItemByLocations: TransferredItemByLocations): TransferredItemByLocations
    fun findTransferredItemByLocationsByTransactionId(id: Int): TransferredItemByLocations?
}