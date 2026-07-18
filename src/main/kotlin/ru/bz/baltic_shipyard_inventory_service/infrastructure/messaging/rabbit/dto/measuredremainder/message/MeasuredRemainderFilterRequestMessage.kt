package ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.measuredremainder.message

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.JsonFieldsProvider
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.RabbitMessage
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.measuredremainder.filter.MeasuredRemainderFilterDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.common.page.DomainPageRequestDto

@JsonInclude(JsonInclude.Include.ALWAYS)
data class MeasuredRemainderFilterRequestMessage(
    @field:JsonProperty(JsonFieldsProvider.MEASURED_REMAINDER_FILTER) val filter: MeasuredRemainderFilterDto,
    @field:JsonProperty(JsonFieldsProvider.PAGE_REQUEST) val pageRequest: DomainPageRequestDto
): RabbitMessage(messageType = "MEASURED_REMAINDER_SEARCH_REQUEST")

