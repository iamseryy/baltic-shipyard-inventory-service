package ru.bz.baltic_shipyard_inventory_service.presentation.dto.filter

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.ContainerStatus
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchContainerFilter
import ru.bz.baltic_shipyard_inventory_service.presentation.validation.annotation.NotEmpty


@Schema(description="schema.searchcontainerfilterdto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class SearchContainerFilterDto(
    @Schema(
        description="schema.container.code.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="R020000002"
    )
    @NotEmpty
    @JsonProperty(JsonFields.CONTAINER_CODE)
    val containerCode: String? = null,

    @Schema(
        description="schema.warehouse.owner.code.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="R0200"
    )
    @NotEmpty
    @JsonProperty(JsonFields.WAREHOUSE_OWNER_CODE)
    val warehouseOwnerCode: String? = null,


    @Schema(
        description="schema.container.desc.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="for spool card"
    )
    @NotEmpty
    @JsonProperty(JsonFields.CONTAINER_DESCRIPTION)
    val containerDescription: String? = null,

    @Schema(
        description="schema.container.status.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="EMPTY"
    )
    @JsonProperty(JsonFields.CONTAINER_STATUS)
    val status: ContainerStatus? = null,

    @Schema(
        description="schema.container.location.warehouse.code.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="R0200"
    )
    @NotEmpty
    @JsonProperty(JsonFields.CONTAINER_LOCATION_WAREHOUSE_CODE)
    val containerLocationWarehouseCode: String? = null,

    @Schema(
        description="schema.container.location.bin.code.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="R0200"
    )
    @NotEmpty
    @JsonProperty(JsonFields.CONTAINER_LOCATION_BIN_CODE)
    val containerLocationBinCode: String? = null,

    @Schema(
        description="schema.order.code.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="001000423"
    )
    @NotEmpty
    @JsonProperty(JsonFields.WAREHOUSE_ORDER_CODE)
    val warehouseOrderCode: String? = null,

    @Schema(
        description="schema.item.code.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="01920371168"
    )
    @NotEmpty
    @JsonProperty(JsonFields.ITEM_CODE)
    val itemCode: String? = null,

    @Schema(
        description="schema.lot.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="211007-00066"
    )
    @NotEmpty
    @JsonProperty(JsonFields.LOT_CODE)
    val lotCode: String? = null,

    @Schema(
        description="schema.stock.location.warehouse.code.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="R0260"
    )
    @NotEmpty
    @JsonProperty(JsonFields.STOCK_POSITION_LOCATION_WAREHOUSE_CODE)
    val stockPositionLocationWarehouseCode: String? = null,

    @Schema(
        description="schema.stock.location.bin.code.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="R0260"
    )
    @NotEmpty
    @JsonProperty(JsonFields.STOCK_POSITION_LOCATION_BIN_CODE)
    val stockPositionLocationBinCode: String? = null,

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

fun SearchContainerFilterDto.toSearchContainerFilter() = SearchContainerFilter(
    containerCode = containerCode,
    warehouseOwnerCode = warehouseOwnerCode,
    containerDescription = containerDescription,
    status = status,
    containerLocationWarehouseCode = containerLocationWarehouseCode,
    containerLocationBinCode = containerLocationBinCode,
    warehouseOrderCode = warehouseOrderCode,
    itemCode = itemCode,
    lotCode = lotCode,
    stockPositionLocationWarehouseCode = stockPositionLocationWarehouseCode,
    stockPositionLocationBinCode = stockPositionLocationBinCode,
    page = page,
    pageSize = pageSize
)
