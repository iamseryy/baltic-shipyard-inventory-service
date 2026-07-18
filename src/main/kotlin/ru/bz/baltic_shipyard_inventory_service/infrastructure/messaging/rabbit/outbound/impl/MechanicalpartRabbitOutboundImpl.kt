package ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.outbound.impl

import org.springframework.amqp.core.AmqpTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.mechanicalpart.ReportedOperation
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.config.RabbitKeys
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.config.RabbitProperties
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.mechanicalpart.ReportedOperationMessage
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.mechanicalpart.ReportedOperationResultMessage
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.mechanicalpart.toReportedOperationDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.outbound.MechanicalpartRabbitOutbound


@Component
class MechanicalpartRabbitOutboundImpl(
    private val amqpTemplate: AmqpTemplate,
    private val props: RabbitProperties
): MechanicalpartRabbitOutbound {
    override fun reportOperation(reportedOperation: ReportedOperation): ReportedOperation? =
        ReportedOperationMessage(reportedOperation.toReportedOperationDto()).let { operation ->
            amqpTemplate.convertSendAndReceiveAsType(
                props.getExchange(RabbitKeys.MECHANICAL_PART_EXCHANGE),
                props.getKey(RabbitKeys.MECHANICAL_PART_REPORT_OPERATION_KEY),
                operation,
                object : ParameterizedTypeReference<ReportedOperationResultMessage>() {}
            )?.reportedOperationDto.let { result -> if (result == true) reportedOperation else null }
            // )?.reportedOperationDto?.toReportedOperation()
        }
}