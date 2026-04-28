package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.measuredremainder

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.abortreason.AbortReasonDto

@JsonInclude(JsonInclude.Include.NON_NULL)
data class MeasuredRemainderResponseDto(
    @JsonProperty(JsonFieldsProvider.ID) val id: String,
    @JsonProperty(JsonFieldsProvider.ABORT_REASON) val abortReason: AbortReasonDto? = null,
)

