package ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.measuredremainder.message

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.JsonFieldsProvider
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.RabbitMessage
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.measuredremainder.MeasuredRemainderDto


@JsonInclude(JsonInclude.Include.ALWAYS)
data class MeasuredRemainderUpdateRequestMessage(
    @field:JsonProperty(JsonFieldsProvider.MEASURED_REMAINDER) val measuredRemainder: MeasuredRemainderDto,
    @field:JsonProperty(JsonFieldsProvider.USER_LOGIN) val userLogin: String
): RabbitMessage(messageType = "MEASURED_REMAINDER_UPDATE_REQUEST")

