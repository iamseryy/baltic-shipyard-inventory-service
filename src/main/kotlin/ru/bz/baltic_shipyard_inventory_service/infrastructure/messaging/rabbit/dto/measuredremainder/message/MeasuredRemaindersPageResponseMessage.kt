package ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.measuredremainder.message

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.common.pagination.DomainPage
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.measuredremainder.MeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.JsonFieldsProvider
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.RabbitMessage
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.common.page.PageMetaDataDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.measuredremainder.MeasuredRemainderDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.measuredremainder.toMeasuredRemainder


@JsonInclude(JsonInclude.Include.ALWAYS)
data class MeasuredRemaindersPageResponseMessage(
    @field:JsonProperty(JsonFieldsProvider.MEASURED_REMAINDERS) val measuredRemainders: List<MeasuredRemainderDto>,
    @field:JsonProperty(JsonFieldsProvider.PAGE_META_DATA) val page: PageMetaDataDto

): RabbitMessage(messageType = "MEASURED_REMAINDER_SEARCH_RESPONSE")

fun MeasuredRemaindersPageResponseMessage.toDomainPage() = DomainPage<MeasuredRemainder>(
    content = measuredRemainders.map { it.toMeasuredRemainder() },
    number = page.number,
    size = page.size,
    totalElements = page.totalElements
)