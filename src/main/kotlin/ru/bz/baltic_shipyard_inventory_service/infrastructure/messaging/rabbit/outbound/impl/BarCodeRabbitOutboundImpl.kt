package ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.outbound.impl

import org.springframework.amqp.core.AmqpTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.barcode.BarCodeData
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.config.RabbitKeys
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.config.RabbitProperties
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.barcode.BarCodeDataMessage
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.barcode.toBarCodeData
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.outbound.BarCodeRabbitOutbound

@Component
class BarCodeRabbitOutboundImpl(
    private val amqpTemplate: AmqpTemplate,
    private val props: RabbitProperties
): BarCodeRabbitOutbound {
    override fun findBarCodeDataByCode(code: String): BarCodeData? =
        amqpTemplate.convertSendAndReceiveAsType(
            props.getExchange(RabbitKeys.BARCODE_EXCHANGE),
            props.getKey(RabbitKeys.FIND_BARCODE_DATA_KEY),
            code,
            object: ParameterizedTypeReference<BarCodeDataMessage>() {}
        )?.let {barCodeDataMessage ->
            barCodeDataMessage.barCodeData?.toBarCodeData()
        }
}