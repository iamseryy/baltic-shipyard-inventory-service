package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.listener

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.usecases.transfer.TransferUseCases
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.transaction.transfer.TransferItemByBinsTransactionResponseMessage
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.transaction.transfer.toTransferredItemByLocations


@Component
class TransferQueuesListener(
    private val transferUseCases: TransferUseCases
) {
    @RabbitListener(queues = ["\${application.rabbitmq.queue.response_to_transfer_by_bins}"])
    fun onTransferItemByLocationsTransaction(message: TransferItemByBinsTransactionResponseMessage){
        message.transferItemByBinsTransactionResponse?.let { transactionResponse ->
            transferUseCases.findTransferredItemByLocationsByTransactionId(transactionResponse.id)
        }?.let { transferredItem ->
            transferUseCases.updateTransferredItemByLocations(
                message.transferItemByBinsTransactionResponse.toTransferredItemByLocations(transferredItem)
            )
        }
    }
}