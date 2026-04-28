package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.filter

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchMeasuredRemainderFilter
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider


@JsonInclude(JsonInclude.Include.NON_NULL)
data class SearchMeasuredRemainderFilterDto(
    @field:JsonProperty(JsonFieldsProvider.ID) val id: String? = null,
    @field:JsonProperty(JsonFieldsProvider.REMAINDER_LIKE) val remainderLike: String? = null,
    @field:JsonProperty(JsonFieldsProvider.PROJECT) val project: String? = null,
    @field:JsonProperty(JsonFieldsProvider.MATERIAL_LIKE) val materialLike: String? = null,
    @field:JsonProperty(JsonFieldsProvider.WAREHOUSE) val warehouse: String? = null,
    @field:JsonProperty(JsonFieldsProvider.LOCATION) val location: String? = null,
    @field:JsonProperty(JsonFieldsProvider.STATUS) val status: Int? = null,
    @field:JsonProperty(JsonFieldsProvider.COMMENT) val comment: String? = null,
    @field:JsonProperty(JsonFieldsProvider.LENGTH_FROM) val lengthFrom: Double? = null,
    @field:JsonProperty(JsonFieldsProvider.WIDTH_FROM) val widthFrom: Double? = null,
    @field:JsonProperty(JsonFieldsProvider.DEPTH) val depth: Double? = null,
    @field:JsonProperty(JsonFieldsProvider.PAGE) val page: Int?,
    @field:JsonProperty(JsonFieldsProvider.PAGE_SIZE) val pageSize: Int?
)

fun SearchMeasuredRemainderFilter.toSearchMeasuredRemainderFilterDto() =
    SearchMeasuredRemainderFilterDto (
        id = id,
        remainderLike = remainderLike,
        project = project,
        materialLike = materialLike,
        warehouse = warehouse,
        location = location,
        status = status?.number,
        comment = comment,
        lengthFrom = lengthFrom,
        widthFrom = widthFrom,
        depth = depth,
        page = page,
        pageSize = pageSize
    )