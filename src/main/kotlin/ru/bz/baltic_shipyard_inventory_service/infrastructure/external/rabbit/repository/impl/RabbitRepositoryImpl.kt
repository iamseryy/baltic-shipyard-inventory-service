package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.repository.impl

import org.springframework.amqp.core.AmqpTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
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
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.barcode.BarCodeDataMessage
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.barcode.toBarCodeData
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.container.*
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.filter.*
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.item.ItemMessageDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.item.toItem
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.lot.LotDetailMessageDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.lot.toLotDetail
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.measuredremainder.MeasuredRemaindersPaginationDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.measuredremainder.toMeasuredRemaindersPagination
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.mechanicalpart.ReportedOperationDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.mechanicalpart.ReportedOperationMessage
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.mechanicalpart.ReportedOperationResultMessage
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.mechanicalpart.toReportedOperation
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.mechanicalpart.toReportedOperationDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.stock.StockListPaginationDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.stock.toStockListPagination
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.transaction.measuredremainder.toInventoryMeasuredRemainderTransactionDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.transaction.measuredremainder.toUpdateMeasuredRemainderTransactionDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.transaction.transfer.TransferItemByBinTransactionMessage
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.transaction.transfer.toTransferItemByLocationsTransactionDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.warehouse.BinMessageDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.warehouse.WarehouseMessageDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.warehouse.toBin
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.warehouse.toWarehouse
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.repository.RabbitRepository


private const val WAREHOUSE_DIRECT = "application.rabbitmq.exchange.warehouse_direct"
private const val WAREHOUSE_TRANSFER_DIRECT = "application.rabbitmq.exchange.warehouse-transfer_direct"
private const val WAREHOUSE_TRANSFER_KEY_TRANSFER_BY_BINS = "application.rabbitmq.key.transfer_by_bins"
private const val WAREHOUSE_KEY_GET = "application.rabbitmq.key.get_warehouse_detail"
private const val WAREHOUSE_KEY_GET_LOCATION = "application.rabbitmq.key.get_location_detail"
private const val MR_DIRECT = "application.rabbitmq.exchange.measured-remainders_direct"
private const val MR_KEY_FIND = "application.rabbitmq.key.find_measured-remainders"
private const val MR_KEY_UPDATE = "application.rabbitmq.key.update_measured-remainder"
private const val MR_KEY_INVENTORY = "application.rabbitmq.key.inventory_measured-remainders"
private const val INVENTORY_DIRECT = "application.rabbitmq.exchange.inventory_direct"
private const val INVENTORY_FIND_STOCK = "application.rabbitmq.key.find_stock_list"
private const val ITEM_DIRECT = "application.rabbitmq.exchange.item_direct"
private const val ITEM_KEY_GET = "application.rabbitmq.key.get_item_detail"
private const val LOT_DIRECT = "application.rabbitmq.exchange.lot_direct"
private const val LOT_KEY_GET = "application.rabbitmq.key.get_lot_detail"
private const val CONTAINER_DIRECT = "application.rabbitmq.exchange.container_direct"
private const val CONTAINER_KEY_GET_CONTAINER= "application.rabbitmq.key.get_container"
private const val CONTAINER_KEY_FIND_CONTAINERS= "application.rabbitmq.key.find_containers"
private const val CONTAINER_KEY_PLACEMENT_CONTAINER= "application.rabbitmq.key.placement_container"
private const val CONTAINER_KEY_PLACEMENT_CONTAINER_RESPONSE= "application.rabbitmq.key.response_to_placement_container"
private const val BARCODE_DIRECT = "application.rabbitmq.exchange.barcode_direct"
private const val BARCODE_KEY_FIND_DATA= "application.rabbitmq.key.find_barcode_data"
private const val MECHANICALPART_DIRECT = "application.rabbitmq.exchange.mechanicalpart_direct"
private const val MECHANICALPART_KEY_REPORT_OPERATION= "application.rabbitmq.key.report_operation"

@Service
class RabbitRepositoryImpl (
    private val amqpTemplate: AmqpTemplate,
    private val environment: Environment
): RabbitRepository {
    override fun findMeasuredRemainderByFilter(filter: SearchMeasuredRemainderFilter): MeasuredRemaindersPagination? =
        with (
            filter.toSearchMeasuredRemainderFilterDto()
        ) {
            amqpTemplate.convertSendAndReceiveAsType(
                environment.getProperty(MR_DIRECT),
                environment.getProperty(MR_KEY_FIND),
                this,
                object: ParameterizedTypeReference<MeasuredRemaindersPaginationDto>() {}
            )?.toMeasuredRemaindersPagination()
        }
    override fun updateMeasuredRemainder(updatableMeasuredRemainder: UpdatableMeasuredRemainder) {
        updatableMeasuredRemainder.toUpdateMeasuredRemainderTransactionDto().let {updateMeasuredRemainderTransaction ->
            amqpTemplate.convertAndSend(
                environment.getProperty(MR_DIRECT),
                environment.getProperty(MR_KEY_UPDATE),
                updateMeasuredRemainderTransaction
            )
        }
    }
    override fun inventoryMeasuredRemainder(inventoriedMeasuredRemainders: InventoriedMeasuredRemainders) {
        inventoriedMeasuredRemainders.toInventoryMeasuredRemainderTransactionDto().let {inventoryMeasuredRemainderTransaction ->
            amqpTemplate.convertAndSend(
                environment.getProperty(MR_DIRECT),
                environment.getProperty(MR_KEY_INVENTORY),
                inventoryMeasuredRemainderTransaction
            )
        }
    }
    override fun findInventoryBalanceByFilter(filter: SearchInventoryBalanceFilter): StockListPagination? =
        with (
            filter.toSearchInventoryBalanceFilterDto()
        ) {
            amqpTemplate.convertSendAndReceiveAsType(
                environment.getProperty(INVENTORY_DIRECT),
                environment.getProperty(INVENTORY_FIND_STOCK),
                this,
                object: ParameterizedTypeReference<StockListPaginationDto>() {}
            )
        }?.toStockListPagination()
    override fun getItemDetail(itemCode: String): Item? =
        amqpTemplate.convertSendAndReceiveAsType(
            environment.getProperty(ITEM_DIRECT),
            environment.getProperty(ITEM_KEY_GET),
            itemCode,
            object: ParameterizedTypeReference<ItemMessageDto>() {}
        )?.let {itemMessageDto ->
            itemMessageDto.item?.toItem()
        }
    override fun getLotDetail(filter: SearchLotDetailShotFilter): LotDetail? =
        with(
            filter.toSearchLotDetailShotFilterDto()
        ){
            amqpTemplate.convertSendAndReceiveAsType(
                environment.getProperty(LOT_DIRECT),
                environment.getProperty(LOT_KEY_GET),
                this,
                object: ParameterizedTypeReference<LotDetailMessageDto>() {}
            )?.let { lotDetailMessageDto ->
                lotDetailMessageDto.lot?.toLotDetail()
            }
        }
    override fun getWarehouseDetail(warehouseCode: String): Warehouse? =
        amqpTemplate.convertSendAndReceiveAsType(
            environment.getProperty(WAREHOUSE_DIRECT),
            environment.getProperty(WAREHOUSE_KEY_GET),
            warehouseCode,
            object: ParameterizedTypeReference<WarehouseMessageDto>() {}
        )?.let {warehouseMessageDto ->
            warehouseMessageDto.warehouse?.toWarehouse()
        }
    override fun getLocationDetailByFilter(filter: SearchLocationDetailShotFilter): Bin? =
        with(filter.toSearchLocationDetailShotFilterDto()) {
            amqpTemplate.convertSendAndReceiveAsType(
                environment.getProperty(WAREHOUSE_DIRECT),
                environment.getProperty(WAREHOUSE_KEY_GET_LOCATION),
                this,
                object: ParameterizedTypeReference<BinMessageDto>() {}
            )?.let {warehouseMessageDto ->
                warehouseMessageDto.bin?.toBin()
            }
        }
    override fun getContainerByCode(code: String): Container? =
        amqpTemplate.convertSendAndReceiveAsType(
            environment.getProperty(CONTAINER_DIRECT),
            environment.getProperty(CONTAINER_KEY_GET_CONTAINER),
            code,
            object: ParameterizedTypeReference<ContainerMessageDto>() {}
        )?.let {containerDetailMessage ->
            containerDetailMessage.container?.toContainer()
        }

    override fun findContainersByFilter(filter: SearchContainerFilter): ContainersPagination? =
        with(filter.toSearchContainerFilterDto()) {
            amqpTemplate.convertSendAndReceiveAsType(
                environment.getProperty(CONTAINER_DIRECT),
                environment.getProperty(CONTAINER_KEY_FIND_CONTAINERS),
                this,
                object: ParameterizedTypeReference<ContainersPaginationMessageDto>() {}
            )?.containers?.toContainersPagination()
        }

    override fun findBarCodeDataByCode(code: String): BarCodeData? =
        amqpTemplate.convertSendAndReceiveAsType(
            environment.getProperty(BARCODE_DIRECT),
            environment.getProperty(BARCODE_KEY_FIND_DATA),
            code,
            object: ParameterizedTypeReference<BarCodeDataMessage>() {}
        )?.let {barCodeDataMessage ->
            barCodeDataMessage.barCodeData?.toBarCodeData()
        }

    override fun transferItemByLocations(transferredItemByLocations: TransferredItemByLocations) {
        transferredItemByLocations.toTransferItemByLocationsTransactionDto().let { transferTransaction ->
            amqpTemplate.convertAndSend(
                environment.getProperty(WAREHOUSE_TRANSFER_DIRECT),
                environment.getProperty(WAREHOUSE_TRANSFER_KEY_TRANSFER_BY_BINS),
                TransferItemByBinTransactionMessage(transferTransaction)
            )
        }
    }

    override fun placeContainer(placedContainer: PlacedContainer) {
        placedContainer.toPlacementContainerTransactionDto().let {placementContainer ->
            amqpTemplate.convertAndSend(
                environment.getProperty(CONTAINER_DIRECT),
                environment.getProperty(CONTAINER_KEY_PLACEMENT_CONTAINER),
                PlacementContainerMessage(placementContainer)
            )
        }
    }

    override fun reportOperation(reportedOperation: ReportedOperation): ReportedOperation? =
        ReportedOperationMessage(reportedOperation.toReportedOperationDto()).let { operation ->
            amqpTemplate.convertSendAndReceiveAsType(
                        environment.getProperty(MECHANICALPART_DIRECT),
                        environment.getProperty(MECHANICALPART_KEY_REPORT_OPERATION),
                        operation,
                        object : ParameterizedTypeReference<ReportedOperationResultMessage>() {}
            )?.reportedOperationDto.let { result -> if (result == true) reportedOperation else null }
                    // )?.reportedOperationDto?.toReportedOperation()
        }

}
