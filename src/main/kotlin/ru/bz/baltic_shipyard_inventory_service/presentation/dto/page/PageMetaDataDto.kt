package ru.bz.baltic_shipyard_inventory_service.presentation.dto.page

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields


@Schema(description="schema.pagemetadatadto.desc")
data class PageMetaDataDto(
    @Schema(
        description="schema.pagination.number.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example="3"
    )
    @JsonProperty(JsonFields.NUMBER)
    val number: Int,

    @Schema(
        description="schema.pagination.size.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example="10"
    )
    @JsonProperty(JsonFields.SIZE)
    val size: Int,

    @Schema(
        description="schema.pagination.totalelements.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="40"
    )
    @JsonProperty(JsonFields.TOTAL_ELEMENTS)
    val totalElements: Long? = null,

    @Schema(
        description="schema.pagination.totalpages.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="4"
    )
    @JsonProperty(JsonFields.TOTAL_PAGES)
    val totalPages: Int? = null,

    @Schema(
        description="schema.pagination.pagefirst.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="false"
    )
    @get:JsonProperty(JsonFields.IS_FIRST)
    val isFirst: Boolean? = null,

    @Schema(
        description="schema.pagination.pagelast.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="false"
    )
    @get:JsonProperty(JsonFields.IS_LAST)
    val isLast: Boolean? = null
)


