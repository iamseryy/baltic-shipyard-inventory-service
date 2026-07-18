package ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.measuredremainder.filter

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.filter.MeasuredRemainderFilter
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.NON_NULL)
data class MeasuredRemainderFilterDto(
    @field:JsonProperty(JsonFieldsProvider.ID) val id: String?,
    @field:JsonProperty(JsonFieldsProvider.CODE_LIKE) val codeLike: String?,
    @field:JsonProperty(JsonFieldsProvider.PROJECT_CODE) val projectCode: String?,
    @field:JsonProperty(JsonFieldsProvider.MATERIAL_LIKE) val materialLike: String?,
    @field:JsonProperty(JsonFieldsProvider.WAREHOUSE_CODE) val warehouseCode: String?,
    @field:JsonProperty(JsonFieldsProvider.BIN_CODE) val binCode: String?,
    @field:JsonProperty(JsonFieldsProvider.STATUS) val status: Int?,
    @field:JsonProperty(JsonFieldsProvider.LENGTH_FROM) val lengthFrom: Double?,
    @field:JsonProperty(JsonFieldsProvider.WIDTH_FROM) val widthFrom: Double?,
    @field:JsonProperty(JsonFieldsProvider.THICKNESS_FROM) val thicknessFrom: Double?
)

fun MeasuredRemainderFilter.toMeasuredRemainderFilterDto() = MeasuredRemainderFilterDto (
        id = id,
        codeLike = codeLike,
        projectCode = projectCode,
        materialLike = materialLike,
        warehouseCode = warehouseCode,
        binCode = binCode,
        status = status?.number,
        lengthFrom = lengthFrom,
        widthFrom = widthFrom,
        thicknessFrom = thicknessFrom
    )