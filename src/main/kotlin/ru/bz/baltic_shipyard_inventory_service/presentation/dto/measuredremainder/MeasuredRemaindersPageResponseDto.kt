package ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.domain.model.entity.measuredremainder.MeasuredRemainder
import ru.bz.baltic_shipyard_inventory_service.domain.common.pagination.DomainPage
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.page.PageMetaDataDto


@Schema(description="schema.measuredremainderspaginationdto.desc")
data class MeasuredRemaindersPageResponseDto(
    @Schema(
        description="schema.measuredremainderspaginationdto.measuredremainders.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
    )
    @JsonProperty(JsonFields.MEASURED_REMAINDERS)
    val measuredRemainders: List<MeasuredRemainderDto>,

    @Schema(
        description="schema.measuredremainderspaginationdto.measuredremainders.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
    )
    @JsonProperty(JsonFields.PAGE_META_DATA)
    val pageMetaData: PageMetaDataDto
)

fun DomainPage<MeasuredRemainder>.toMeasuredRemaindersPageResponseDto() = MeasuredRemaindersPageResponseDto(
    measuredRemainders = content.map { it.toMeasuredRemainderDto() },
    pageMetaData = PageMetaDataDto(
        number = number + 1,
        size = size,
        totalElements = totalElements,
        totalPages = totalPages,
        isFirst = number == 0 || totalElements == 0L,
        isLast = number == totalPages - 1 || totalElements  == 0L
    )
)
