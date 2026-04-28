package ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemaindersPagination


@Schema(description="schema.measuredremainderspaginationdto.desc")
data class MeasuredRemaindersPaginationDto(
    @Schema(
        description="schema.measuredremainderspaginationdto.measuredremainders.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
    )
    @JsonProperty(JsonFields.MEASURED_REMAINDERS)
    val measuredRemainders: List<MeasuredRemainderDto>,

    @Schema(
        description="schema.pagination.page.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example="3"
    )
    @JsonProperty(JsonFields.PAGE)
    val page: Int,

    @Schema(
        description="schema.pagination.page_size.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example="10"
    )
    @JsonProperty(JsonFields.PAGE_SIZE)
    val pageSize: Int
)

fun MeasuredRemaindersPagination.toMeasuredRemaindersPaginationDto() = MeasuredRemaindersPaginationDto(
    measuredRemainders = measuredRemainders.map { it.toMeasuredRemainderDto() },
    page = page,
    pageSize = pageSize
)
