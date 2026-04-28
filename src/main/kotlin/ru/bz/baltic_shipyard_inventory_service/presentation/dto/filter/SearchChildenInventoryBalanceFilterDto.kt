package ru.bz.baltic_shipyard_inventory_service.presentation.dto.filter

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.validation.annotation.NotEmpty
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchInventoryBalanceFilter


@Schema(description="schema.searchchildinventorybalancefilterdto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class SearchChildrenInventoryBalanceFilterDto(
    @Schema(
        description="schema.item.code.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example="01362783045"
    )
    @NotEmpty
    @JsonProperty(JsonFields.ITEM_CODE)
    val itemCode: String,

    @Schema(
        description="schema.lot.code.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example="210901-00019"
    )
    @NotEmpty
    @JsonProperty(JsonFields.LOT_CODE)
    val lotCode: String,

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

fun SearchChildrenInventoryBalanceFilterDto.toSearchInventoryBalanceFilter() = SearchInventoryBalanceFilter(
    warehouseCode = null,
    binCode = null,
    itemCode = itemCode,
    lotCode = lotCode,
    findLotChildren = true,
    page = page,
    pageSize = pageSize
)
