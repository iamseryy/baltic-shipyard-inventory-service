package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.measuredremainder

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemaindersPagination
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider

@JsonInclude(JsonInclude.Include.ALWAYS)
data class MeasuredRemaindersPaginationDto(
    @JsonProperty(JsonFieldsProvider.MEASURED_REMAINDERS) val measuredRemainders: List<MeasuredRemainderDto>,
    @JsonProperty(JsonFieldsProvider.PAGE) val page: Int,
    @JsonProperty(JsonFieldsProvider.PAGE_SIZE) val pageSize: Int
)

fun MeasuredRemaindersPaginationDto.toMeasuredRemainderList() = measuredRemainders.map { measuredRemainderDto ->
    measuredRemainderDto.toMeasuredRemainder()
}

fun MeasuredRemaindersPaginationDto.toMeasuredRemaindersPagination() = MeasuredRemaindersPagination(
    measuredRemainders = measuredRemainders.map { it.toMeasuredRemainder() },
    page = page,
    pageSize = pageSize
)




