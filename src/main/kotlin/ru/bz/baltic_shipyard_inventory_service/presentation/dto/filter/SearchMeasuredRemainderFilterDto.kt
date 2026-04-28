package ru.bz.baltic_shipyard_inventory_service.presentation.dto.filter

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.validation.annotation.NotEmpty
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchMeasuredRemainderFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.measuredremainder.MeasuredRemainderStatus


@Schema(description="schema.searchmeasuredremainderfilterdto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class SearchMeasuredRemainderFilterDto(

    @Schema(
        description="schema.measuredremainder.id.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="R202011040000000009"
    )
    @NotEmpty
    @JsonProperty(JsonFields.ID)
    val id: String? = null,

    @Schema(
        description="schema.searchmeasuredremainderfilterdto.remainder.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="251-321"
    )
    @NotEmpty
    @JsonProperty(JsonFields.REMAINDER_LIKE)
    val remainderLike: String? = null,

    @Schema(
        description="schema.project.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="057120"
    )
    @NotEmpty
    @JsonProperty(JsonFields.PROJECT)
    val project: String? = null,

    @Schema(
        description="schema.searchmeasuredremainderfilterdto.material.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="\u0421\u04423\u0441\u043f2"
    )
    @NotEmpty
    @JsonProperty(JsonFields.MATERIAL_LIKE)
    val materialLike: String? = null,

    @Schema(
        description="schema.warehouse.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="R0200"
    )
    @NotEmpty
    @JsonProperty(JsonFields.WAREHOUSE)
    val warehouse: String? = null,

    @Schema(
        description="schema.location.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="01"
    )
    @NotEmpty
    @JsonProperty(JsonFields.LOCATION)
    val location: String? = null,

    @Schema(
        description="schema.measuredremainder.status.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="�����������"
    )
    @get:Min(message = "code={error.1007.code};description={error.1007.description}", value = 1)
    @get:Max(message = "code={error.1007.code};description={error.1007.description}", value = 4)
    @JsonProperty(JsonFields.STATUS)
    val status: Int? = null,

    @Schema(
        description="schema.measuredremainder.comment.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="\u0441/\u0437 \u2116 12-3877 \u043e\u0442 18.11.2020"
    )
    @NotEmpty
    @JsonProperty(JsonFields.COMMENT)
    val comment: String? = null,

    @Schema(
        description="schema.searchmeasuredremainderfilterdto.length.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="22.2"
    )
    @get:Min(message = "code={error.1005.code};description={error.1005.description}", value = 1)
    @get:Max(message = "code={error.1004.code};description={error.1004.description}", value = 1_000_000_000)
    @JsonProperty(JsonFields.LENGTH_FROM)
    val lengthFrom: Double? = null,

    @Schema(
        description="schema.searchmeasuredremainderfilterdto.width.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="30"
    )
    @get:Min(message = "code={error.1005.code};description={error.1005.description}", value = 1)
    @get:Max(message = "code={error.1004.code};description={error.1004.description}", value = 1_000_000_000)
    @JsonProperty(JsonFields.WIDTH_FROM)
    val widthFrom: Double? = null,

    @Schema(
        description="schema.measuredremainder.depth.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="15"
    )
    @get:Min(message = "code={error.1005.code};description={error.1005.description}", value = 1)
    @get:Max(message = "code={error.1004.code};description={error.1004.description}", value = 1_000_000_000)
    @JsonProperty(JsonFields.DEPTH)
    val depth: Double? = null,

    @Schema(
        description="schema.pagination.page.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="11"
    )
    @get:Min(message = "code={error.1002.code};description={error.1002.description}", value = 0)
    @get:Max(message = "code={error.1004.code};description={error.1004.description}", value = 1_000_000_000)
    @JsonProperty(JsonFields.PAGE)
    val page: Int = 0,

    @Schema(
        description="schema.pagination.page_size.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="10"
    )
    @get:Min(message = "code={error.1003.code};description={error.1003.description}", value = 1)
    @get:Max(message = "code={error.1004.code};description={error.1004.description}", value = 1_000_000_000)
    @JsonProperty(JsonFields.PAGE_SIZE)
    val pageSize: Int = 10
)

fun SearchMeasuredRemainderFilterDto.toSearchMeasuredRemainderFilter() = SearchMeasuredRemainderFilter (
    id = id,
    remainderLike = remainderLike,
    project = project,
    materialLike = materialLike,
    warehouse = warehouse,
    location = location,
    status = status?.let { MeasuredRemainderStatus.getByNumber(it) },
    comment = comment,
    lengthFrom = lengthFrom,
    widthFrom = widthFrom,
    depth = depth,
    page = page,
    pageSize = pageSize
)