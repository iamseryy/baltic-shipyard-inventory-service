package ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.common.page

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.common.pagination.DomainPageRequest
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.ALWAYS)
data class DomainPageRequestDto(
    @field:JsonProperty(JsonFieldsProvider.NUMBER) val number: Int,
    @field:JsonProperty(JsonFieldsProvider.SIZE) val size: Int
)

fun DomainPageRequest.toDomainPageRequestDto(): DomainPageRequestDto = DomainPageRequestDto(
    number = number,
    size = size
)
