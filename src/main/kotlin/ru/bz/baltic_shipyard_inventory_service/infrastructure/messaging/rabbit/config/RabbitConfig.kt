@file:Suppress("removal", "DEPRECATION")

package ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.config

import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.springframework.amqp.core.Declarables
import org.springframework.amqp.core.MessagePostProcessor
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.interceptor.RabbitMdcInterceptor
import java.nio.charset.StandardCharsets


@Configuration
class RabbitConfig(
    private val props: RabbitProperties,
    private val rabbitMdcInterceptor: RabbitMdcInterceptor,
    private val objectMapper: ObjectMapper
) {
    private val logger = KotlinLogging.logger {}

    @Bean
    fun messageConverter(objectMapper: ObjectMapper): MessageConverter {
        return Jackson2JsonMessageConverter(objectMapper)
    }

    @Bean
    fun rabbitTemplate(
        connectionFactory: ConnectionFactory,
        messageConverter: MessageConverter
    ): RabbitTemplate {
        return RabbitTemplate(connectionFactory).apply {
            setMessageConverter(messageConverter)
            setReplyTimeout(15000L)

            setBeforePublishPostProcessors(MessagePostProcessor { msg ->
                val traceId = msg.messageProperties.getHeader("X-Trace-Id") ?: "NO_TRACE"
                logger.debug {
                    val payload = String(msg.body, StandardCharsets.UTF_8)
                    "[RabbitMQ SEND] TraceId: $traceId, Payload: $payload"
                }
                msg
            })

            setAfterReceivePostProcessors(MessagePostProcessor { msg ->
                val traceId = msg.messageProperties.getHeader("X-Trace-Id") ?: "NO_TRACE"
                logger.debug {
                    val payload = String(msg.body, StandardCharsets.UTF_8)
                    "[RabbitMQ REPLY] TraceId: $traceId, Payload: $payload"
                }
                msg
            })
        }
    }

    @Bean
    fun rabbitListenerContainerFactory(
        connectionFactory: ConnectionFactory,
        messageConverter: MessageConverter
    ): SimpleRabbitListenerContainerFactory {
        return SimpleRabbitListenerContainerFactory().apply {
            setConnectionFactory(connectionFactory)
            setMessageConverter(messageConverter)
            setDefaultRequeueRejected(false)
            setAdviceChain(rabbitMdcInterceptor)
        }
    }


    @Bean
    fun measuredRemaindersDeclarables(): Declarables =
        RabbitDeclarablesBuilder.multiQueueDeclarables(
            exchangeName = props.getExchange(RabbitKeys.MEASURED_REMAINDERS_EXCHANGE ),
            queues = listOf(
                props.getQueue(RabbitKeys.FIND_MEASURED_REMAINDERS_QUEUE) to props.getKey(RabbitKeys.FIND_MEASURED_REMAINDERS_KEY),
                props.getQueue(RabbitKeys.UPDATE_MEASURED_REMAINDER_QUEUE) to props.getKey(RabbitKeys.UPDATE_MEASURED_REMAINDER_KEY),
                props.getQueue(RabbitKeys.INVENTORY_MEASURED_REMAINDERS_QUEUE) to props.getKey(RabbitKeys.INVENTORY_MEASURED_REMAINDERS_KEY),
            ).filter { it.second.isNotEmpty() }
        )

    @Bean
    fun warehouseTransferDeclarables(): Declarables =
        RabbitDeclarablesBuilder.multiQueueDeclarables(
            exchangeName = props.getExchange(RabbitKeys.WAREHOUSE_EXCHANGE),
            queues = listOf(
                props.getQueue(RabbitKeys.TRANSFER_BY_BINS_QUEUE) to props.getKey(RabbitKeys.TRANSFER_BY_BINS_KEY),
                props.getQueue(RabbitKeys.RESPONSE_TRANSFER_BY_BINS_QUEUE) to props.getKey(RabbitKeys.RESPONSE_TRANSFER_BY_BINS_KEY)
            ).filter { it.second.isNotEmpty() }
        ).let { declarables ->
            val responseQueue = Queue(props.getQueue(RabbitKeys.RESPONSE_TRANSFER_BY_BINS_QUEUE), true)
            Declarables(declarables.declarables + responseQueue)
        }


    @Bean
    fun inventoryDeclarables(): Declarables = RabbitDeclarablesBuilder.singleQueueDeclarables(
        exchangeName = props.getExchange(RabbitKeys.WAREHOUSE_EXCHANGE),
        queueName = props.getQueue(RabbitKeys.FIND_STOCK_LIST_QUEUE),
        routingKey = props.getKey(RabbitKeys.FIND_STOCK_LIST_KEY)
    )

    @Bean
    fun itemDeclarables(): Declarables = RabbitDeclarablesBuilder.singleQueueDeclarables(
        exchangeName = props.getExchange(RabbitKeys.WAREHOUSE_EXCHANGE),
        queueName = props.getQueue(RabbitKeys.GET_ITEM_DETAIL_QUEUE),
        routingKey = props.getKey(RabbitKeys.GET_ITEM_DETAIL_KEY)
    )

    @Bean
    fun lotDeclarables(): Declarables = RabbitDeclarablesBuilder.singleQueueDeclarables(
        exchangeName = props.getExchange(RabbitKeys.WAREHOUSE_EXCHANGE),
        queueName = props.getQueue(RabbitKeys.GET_LOT_DETAIL_QUEUE),
        routingKey = props.getKey(RabbitKeys.GET_LOT_DETAIL_KEY)
    )

    @Bean
    fun warehouseDeclarables(): Declarables = RabbitDeclarablesBuilder.multiQueueDeclarables(
        exchangeName = props.getExchange(RabbitKeys.WAREHOUSE_EXCHANGE),
        queues = listOf(
            props.getQueue(RabbitKeys.GET_WAREHOUSE_DETAIL_QUEUE) to props.getKey(RabbitKeys.GET_WAREHOUSE_DETAIL_KEY),
            props.getQueue(RabbitKeys.GET_LOCATION_DETAIL_QUEUE) to props.getKey(RabbitKeys.GET_LOCATION_DETAIL_KEY)
        ).filter { it.second.isNotEmpty() }
    )

    @Bean
    fun containerDeclarables(): Declarables =
        RabbitDeclarablesBuilder.multiQueueDeclarables(
            exchangeName = props.getExchange(RabbitKeys.WAREHOUSE_EXCHANGE),
            queues = listOf(
                props.getQueue(RabbitKeys.GET_CONTAINER_QUEUE) to props.getKey(RabbitKeys.GET_CONTAINER_KEY),
                props.getQueue(RabbitKeys.FIND_CONTAINERS_QUEUE) to props.getKey(RabbitKeys.FIND_CONTAINERS_KEY),
                props.getQueue(RabbitKeys.PLACEMENT_CONTAINER_QUEUE) to props.getKey(RabbitKeys.PLACEMENT_CONTAINER_KEY),
                props.getQueue(RabbitKeys.RESPONSE_PLACEMENT_CONTAINER_QUEUE) to props.getKey(RabbitKeys.RESPONSE_PLACEMENT_CONTAINER_KEY),
            ).filter { it.second.isNotEmpty() }
        ).let { declarables ->
            val responseQueue = Queue(props.getQueue(RabbitKeys.RESPONSE_PLACEMENT_CONTAINER_QUEUE), true)
            Declarables(declarables.declarables + responseQueue)
        }


    @Bean
    fun barcodeDeclarables(): Declarables = RabbitDeclarablesBuilder.singleQueueDeclarables(
        exchangeName = props.getExchange(RabbitKeys.BARCODE_EXCHANGE),
        queueName = props.getQueue(RabbitKeys.FIND_BARCODE_DATA_QUEUE),
        routingKey = props.getKey(RabbitKeys.FIND_BARCODE_DATA_KEY)
    )

    @Bean
    fun mechanicalPartDeclarables(): Declarables = RabbitDeclarablesBuilder.singleQueueDeclarables(
        exchangeName = props.getExchange(RabbitKeys.MECHANICAL_PART_EXCHANGE),
        queueName = props.getQueue(RabbitKeys.MECHANICAL_PART_REPORT_OPERATION_QUEUE),
        routingKey = props.getKey(RabbitKeys.MECHANICAL_PART_REPORT_OPERATION_KEY)
    )
}