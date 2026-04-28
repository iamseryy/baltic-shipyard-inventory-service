package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.repository

import ru.bz.baltic_shipyard_inventory_service.domain.model.barcode.BarCodeData
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.Container
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.ContainersPagination
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacedContainer
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.*
import ru.bz.baltic_shipyard_inventory_service.domain.model.item.Item
import ru.bz.baltic_shipyard_inventory_service.domain.model.lot.LotDetail
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.InventoriedMeasuredRemainders
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemaindersPagination
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.UpdatableMeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.domain.model.mechanicalpart.ReportedOperation
import ru.bz.baltic_shipyard_inventory_service.domain.model.stock.StockListPagination
import ru.bz.baltic_shipyard_inventory_service.domain.model.transfer.TransferredItemByLocations
import ru.bz.baltic_shipyard_inventory_service.domain.model.warehouse.Bin
import ru.bz.baltic_shipyard_inventory_service.domain.model.warehouse.Warehouse

interface RabbitRepository {
    fun findMeasuredRemainderByFilter(filter: SearchMeasuredRemainderFilter): MeasuredRemaindersPagination?
    fun updateMeasuredRemainder(updatableMeasuredRemainder: UpdatableMeasuredRemainder)
    fun inventoryMeasuredRemainder(inventoriedMeasuredRemainders: InventoriedMeasuredRemainders)
    fun transferItemByLocations(transferredItemByLocations: TransferredItemByLocations)
    fun findInventoryBalanceByFilter(filter: SearchInventoryBalanceFilter): StockListPagination?
    fun getItemDetail(itemCode: String): Item?
    fun getLotDetail(filter: SearchLotDetailShotFilter): LotDetail?
    fun getWarehouseDetail(warehouseCode: String): Warehouse?
    fun getLocationDetailByFilter(filter: SearchLocationDetailShotFilter): Bin?
    fun getContainerByCode(code: String): Container?
    fun findContainersByFilter(filter: SearchContainerFilter): ContainersPagination?
    fun findBarCodeDataByCode(code: String): BarCodeData?
    fun placeContainer(placedContainer: PlacedContainer)
    fun reportOperation(reportedOperation: ReportedOperation): ReportedOperation?
}