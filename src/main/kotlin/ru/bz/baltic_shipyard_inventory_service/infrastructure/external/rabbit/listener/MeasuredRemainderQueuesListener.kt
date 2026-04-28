package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.listener

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.usecases.measuredremainder.MeasuredRemainderUseCases
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.transaction.measuredremainder.InventoryMeasuredRemaindersTransactionResponseDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.transaction.measuredremainder.UpdateMeasuredRemainderTransactionResponseDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.transaction.measuredremainder.toInventoriedMeasuredRemainders
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.transaction.measuredremainder.toUpdatableMeasuredRemainder


@Component
class MeasuredRemainderQueuesListener(
    private val measuredRemainderUseCases: MeasuredRemainderUseCases
) {
    @RabbitListener(queues = ["\${application.rabbitmq.queue.response_to_update_measured-remainder}"])
    fun onUpdateMeasuredRemainderTransaction(transactionResponse: UpdateMeasuredRemainderTransactionResponseDto) {
        measuredRemainderUseCases.findUpdatableMeasuredRemaindersByTransactionId(transactionResponse.id)[0].let {
            measuredRemainderUseCases.saveUpdatableMeasuredRemainder(
                transactionResponse.toUpdatableMeasuredRemainder(
                    it
                )
            )
        }
    }

    @RabbitListener(queues = ["\${application.rabbitmq.queue.response_to_inventory_measured-remainders}"])
    fun onInventoryMeasuredRemainderTransaction(transactionResponse: InventoryMeasuredRemaindersTransactionResponseDto) {
        measuredRemainderUseCases.findUpdatableMeasuredRemaindersByTransactionId(transactionResponse.id).let {
            measuredRemainderUseCases.saveInventoriedMeasuredRemainders(
                transactionResponse.toInventoriedMeasuredRemainders(
                    it
                )
            )
        }
    }
}