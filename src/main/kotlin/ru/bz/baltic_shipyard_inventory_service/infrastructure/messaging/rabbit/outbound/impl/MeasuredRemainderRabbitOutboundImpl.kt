package ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.outbound.impl

import mu.KotlinLogging
import org.springframework.amqp.AmqpConnectException
import org.springframework.amqp.AmqpTimeoutException
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.amqp.core.MessagePostProcessor
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Component
import ru.bz.baltic_shipyard_inventory_service.common.errors.ErrorCode
import ru.bz.baltic_shipyard_inventory_service.common.errors.ExternalServiceTimeoutException
import ru.bz.baltic_shipyard_inventory_service.common.errors.ExternalServiceUnavailableException
import ru.bz.baltic_shipyard_inventory_service.common.errors.abortreason.AbortReason
import ru.bz.baltic_shipyard_inventory_service.domain.common.Result
import ru.bz.baltic_shipyard_inventory_service.domain.common.Result.Failure
import ru.bz.baltic_shipyard_inventory_service.domain.common.Result.Success
import ru.bz.baltic_shipyard_inventory_service.domain.common.pagination.DomainPage
import ru.bz.baltic_shipyard_inventory_service.domain.common.pagination.DomainPageRequest
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.measuredremainder.MeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.InventoriedMeasuredRemainders
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.filter.MeasuredRemainderFilter
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.config.RabbitKeys
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.config.RabbitProperties
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.common.page.toDomainPageRequestDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.measuredremainder.filter.toMeasuredRemainderFilterDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.measuredremainder.message.*
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.measuredremainder.toMeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.measuredremainder.toMeasuredRemainderDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.interceptor.RabbitMdcInterceptor
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.outbound.MeasuredRemainderRabbitOutbound


@Component
class MeasuredRemainderRabbitOutboundImpl(
    private val amqpTemplate: AmqpTemplate,
    private val props: RabbitProperties
): MeasuredRemainderRabbitOutbound {
    private val logger = KotlinLogging.logger {}

    override fun findPage(filter: MeasuredRemainderFilter, pageRequest: DomainPageRequest): DomainPage<MeasuredRemainder>? =
        with(
            MeasuredRemainderFilterRequestMessage(
                filter.toMeasuredRemainderFilterDto(),
                pageRequest.toDomainPageRequestDto()
            )
        ) {
            try {
                amqpTemplate.convertSendAndReceiveAsType(
                    props.getExchange(RabbitKeys.MEASURED_REMAINDERS_EXCHANGE),
                    props.getKey(RabbitKeys.FIND_MEASURED_REMAINDERS_KEY),
                    this,
                    MessagePostProcessor { message ->
                        message.messageProperties.setMessageId(messageId)
                        message.messageProperties.setHeader(RabbitMdcInterceptor.TRACE_ID_HEADER, messageId)
                        message.messageProperties.setHeader("messageType", messageType)
                        message
                    },
                    object : ParameterizedTypeReference<MeasuredRemaindersPageResponseMessage>() {}
                )?.toDomainPage()
            } catch (e: AmqpTimeoutException) {
                logger.error(e) { "RabbitMQ timeout" }
                throw ExternalServiceTimeoutException("RabbitMQ timeout", e)
            } catch (e: AmqpConnectException) {
                logger.error(e) { "RabbitMQ connection failed" }
                throw ExternalServiceUnavailableException("RabbitMQ connection failed", e)
            }
        }

    override fun update(measuredRemainder: MeasuredRemainder, userLogin: String): Result<MeasuredRemainder> =
        with(
            MeasuredRemainderUpdateRequestMessage(
                measuredRemainder.toMeasuredRemainderDto(),
                userLogin
            )
        ) {
            try {
                val response = amqpTemplate.convertSendAndReceiveAsType(
                    props.getExchange(RabbitKeys.MEASURED_REMAINDERS_EXCHANGE),
                    props.getKey(RabbitKeys.UPDATE_MEASURED_REMAINDER_KEY),
                    this,
                    MessagePostProcessor { message ->
                        message.messageProperties.setMessageId(messageId)
                        message.messageProperties.setHeader(RabbitMdcInterceptor.TRACE_ID_HEADER, messageId)
                        message.messageProperties.setHeader("messageType", messageType)
                        message
                    },
                    object : ParameterizedTypeReference<MeasuredRemainderUpdateResponseMessage>() {}
                )
                    return when {
                        response == null -> {
                            logger.warn { "RabbitMQ returned null when updating: ${measuredRemainder.id}" }
                            Failure(
                                AbortReason(
                                    generalViolation = ErrorCode.UPDATE_EMPTY_RESPONSE.toViolation()
                                )
                            )
                        }

                        response.abortReason != null -> {
                            Failure(
                                AbortReason(
                                    generalViolation = ErrorCode.EXTERNAL_VALIDATION.toViolationWithExternal(
                                        externalDescription = response.abortReason.generalViolation?.description
                                    )
                                )
                            )
                        }
                        else -> Success(response.measuredRemainderDto.toMeasuredRemainder())
                    }
            } catch (e: AmqpTimeoutException) {
                logger.error(e) { "RabbitMQ timeout" }
                throw ExternalServiceTimeoutException("RabbitMQ timeout", e)
            } catch (e: AmqpConnectException) {
                logger.error(e) { "RabbitMQ connection failed" }
                throw ExternalServiceUnavailableException("RabbitMQ connection failed", e)
            }
        }

    override fun inventoryMeasuredRemainder(inventoriedMeasuredRemainders: InventoriedMeasuredRemainders) {
        TODO("Not yet implemented")
    }

    override fun findBinCodesByWarehouseCode(warehouseCode: String): List<String>? =
        with(FindBinCodesByWarehouseCodeRequestMessage(warehouseCode)) {
        try {
            amqpTemplate.convertSendAndReceiveAsType(
                props.getExchange(RabbitKeys.MEASURED_REMAINDERS_EXCHANGE),
                props.getKey(RabbitKeys.FIND_BIN_CODES_OF_MEASURED_REMAINDERS_KEY),
                this,
                MessagePostProcessor { message ->
                    message.messageProperties.setMessageId(messageId)
                    message.messageProperties.setHeader(RabbitMdcInterceptor.TRACE_ID_HEADER, messageId)
                    message.messageProperties.setHeader("messageType", messageType)
                    message
                },
                object : ParameterizedTypeReference<BinCodesResponseMessage>() {}
            )?.binCodes
        } catch (e: AmqpTimeoutException) {
            logger.error(e) { "RabbitMQ timeout" }
            throw ExternalServiceTimeoutException("RabbitMQ timeout", e)
        } catch (e: AmqpConnectException) {
            logger.error(e) { "RabbitMQ connection failed" }
            throw ExternalServiceUnavailableException("RabbitMQ connection failed", e)
        }
    }
}