package ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.inbound

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.applications.usecases.warehouse.container.ContainerUseCases
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.container.PlacementContainerTransactionResponseMessage
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.container.toPlacedContainer


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