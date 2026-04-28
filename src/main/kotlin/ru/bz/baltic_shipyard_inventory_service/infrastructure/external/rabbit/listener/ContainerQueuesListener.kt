package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.listener

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.usecases.container.ContainerUseCases
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.container.PlacementContainerTransactionResponseMessage
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.container.toPlacedContainer


@Component
class ContainerQueuesListener(
    private val containerUseCases: ContainerUseCases
) {

    @RabbitListener(queues = ["\${application.rabbitmq.queue.response_to_placement_container}"])
    fun onPlacementContainer(
        //message: TestMessage
        message: PlacementContainerTransactionResponseMessage
    ) {
        //println(message.test)
        message.placementContainerTransactionResponse?.let {transactionResponse ->
            containerUseCases.findPlacedContainerByTransactionId(transactionResponse.id)
        }?.let {placedContainer ->
            containerUseCases.updatePlacedContainer(
                message.placementContainerTransactionResponse.toPlacedContainer(placedContainer)
            )
        }
    }
}