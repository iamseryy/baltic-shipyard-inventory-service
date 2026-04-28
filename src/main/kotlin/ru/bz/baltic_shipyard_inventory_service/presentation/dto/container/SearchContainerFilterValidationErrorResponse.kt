package ru.bz.baltic_shipyard_inventory_service.presentation.dto.container

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.FieldError
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.ViolationDto


private const val FIELDS_DELIMITER = ";"
private const val VALUE_DELIMITER = "="
private const val FIELD_CONTAINER_CODE = "containerCode"
private const val FIELD_WAREHOUSE_OWNER_CODE = "warehouseOwnerCode"
private const val FIELD_CONTAINER_DESCRIPTION = "containerDescription"
private const val FIELD_STATUS = "status"
private const val FIELD_CONTAINER_LOCATION_WAREHOUSE_CODE = "containerLocationWarehouseCode"
private const val FIELD_CONTAINER_LOCATION_BIN_CODE = "containerLocationBinCode"
private const val FIELD_WAREHOUSE_ORDER_CODE = "warehouseOrderCode"
private const val FIELD_ITEM_CODE = "itemCode"
private const val FIELD_LOT_CODE = "lotCode"
private const val FIELD_STOCK_POSITION_LOCATION_WAREHOUSE_CODE = "stockPositionLocationWarehouseCode"
private const val FIELD_STOCK_POSITION_LOCATION_BIN_CODE = "stock_position_location_bin_code"
private const val FIELD_PAGE = "page"
private const val FIELD_PAGE_SIZE = "page_size"


@Schema(description="schema.violationbyfieldsdto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class SearchContainerFilterValidationErrorResponse(
    @Schema(description="schema.warehouse.desc", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(JsonFields.CONTAINER_CODE) val containerCode: ViolationDto? = null,

    @Schema(description="schema.warehouse.desc", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(JsonFields.WAREHOUSE_OWNER_CODE) val warehouseOwnerCode: ViolationDto? = null,

    @Schema(description="schema.warehouse.desc", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(JsonFields.CONTAINER_DESCRIPTION) val containerDescription: ViolationDto? = null,

    @Schema(description="schema.warehouse.desc", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(JsonFields.STATUS) val status: ViolationDto? = null,

    @Schema(description="schema.warehouse.desc", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(JsonFields.CONTAINER_LOCATION_WAREHOUSE_CODE) val containerLocationWarehouseCode: ViolationDto? = null,

    @Schema(description="schema.warehouse.desc", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(JsonFields.CONTAINER_LOCATION_BIN_CODE) val containerLocationBinCode: ViolationDto? = null,

    @Schema(description="schema.warehouse.desc", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(JsonFields.WAREHOUSE_ORDER_CODE) val warehouseOrderCode: ViolationDto? = null,

    @Schema(description="schema.warehouse.desc", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(JsonFields.ITEM_CODE) val itemCode: ViolationDto? = null,

    @Schema(description="schema.warehouse.desc", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(JsonFields.LOT_CODE) val lotCode: ViolationDto? = null,

    @Schema(description="schema.warehouse.desc", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(JsonFields.STOCK_POSITION_LOCATION_WAREHOUSE_CODE) val stockPositionLocationWarehouseCode: ViolationDto? = null,

    @Schema(description="schema.warehouse.desc", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(JsonFields.STOCK_POSITION_LOCATION_BIN_CODE) val stockPositionLocationBinCode: ViolationDto? = null,

    @Schema(description="schema.page.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty(JsonFields.PAGE) val page: ViolationDto? = null,

    @Schema(description="schema.pagesize.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty(JsonFields.PAGE_SIZE) val pageSize: ViolationDto? = null
) {
    class EntryBuilder {
        private var containerCode: ViolationDto? = null
        private var warehouseOwnerCode: ViolationDto? = null
        private var containerDescription: ViolationDto? = null
        private var status: ViolationDto? = null
        private var containerLocationWarehouseCode: ViolationDto? = null
        private var containerLocationBinCode: ViolationDto? = null
        private var warehouseOrderCode: ViolationDto? = null
        private var itemCode: ViolationDto? = null
        private var lotCode: ViolationDto? = null
        private var stockPositionLocationWarehouseCode: ViolationDto? = null
        private var stockPositionLocationBinCode: ViolationDto? = null
        private var page: ViolationDto? = null
        private var pageSize: ViolationDto? = null


        fun containerCode(violation: ViolationDto): EntryBuilder {
            this.containerCode = violation
            return this
        }

        fun warehouseOwnerCode(violation: ViolationDto): EntryBuilder {
            this.warehouseOwnerCode = violation
            return this
        }

        fun containerDescription(violation: ViolationDto): EntryBuilder {
            this.containerDescription = violation
            return this
        }

        fun status(violation: ViolationDto): EntryBuilder {
            this.status = violation
            return this
        }

        fun containerLocationWarehouseCode(violation: ViolationDto): EntryBuilder {
            this.containerLocationWarehouseCode = violation
            return this
        }

        fun containerLocationBinCode(violation: ViolationDto): EntryBuilder {
            this.containerLocationBinCode = violation
            return this
        }

        fun warehouseOrderCode(violation: ViolationDto): EntryBuilder {
            this.warehouseOrderCode = violation
            return this
        }

        fun itemCode(violation: ViolationDto): EntryBuilder {
            this.itemCode = violation
            return this
        }

        fun lotCode(violation: ViolationDto): EntryBuilder {
            this.lotCode = violation
            return this
        }

        fun stockPositionLocationWarehouseCode(violation: ViolationDto): EntryBuilder {
            this.stockPositionLocationWarehouseCode = violation
            return this
        }

        fun stockPositionLocationBinCode(violation: ViolationDto): EntryBuilder {
            this.stockPositionLocationBinCode = violation
            return this
        }

        fun page(violation: ViolationDto): EntryBuilder {
            this.page = violation
            return this
        }

        fun pageSize(violation: ViolationDto): EntryBuilder {
            this.pageSize = violation
            return this
        }

        fun build() = SearchContainerFilterValidationErrorResponse (
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

        fun build(fieldErrors: List<FieldError>): SearchContainerFilterValidationErrorResponse {
            fieldErrors.forEach { addField(it.field, it.defaultMessage ) }
            return build()
        }

        private fun addField(field: String, message: String?): EntryBuilder {
            var error = mapOf<String, String>()

            if(!message.isNullOrEmpty()) {
                error = message
                    .split(FIELDS_DELIMITER)
                    .map { it.split(VALUE_DELIMITER) }
                    .filter { it.size == 2 }
                    .associate { it[0] to it[1] }
            }

            return with(ViolationDto(error[JsonFields.CODE] ?: "", error[JsonFields.DESCRIPTION] ?: "")) {
                when(field) {
                    FIELD_CONTAINER_CODE -> containerCode(this)
                    FIELD_WAREHOUSE_OWNER_CODE -> warehouseOwnerCode(this)
                    FIELD_CONTAINER_DESCRIPTION -> containerDescription(this)
                    FIELD_STATUS -> status(this)
                    FIELD_CONTAINER_LOCATION_WAREHOUSE_CODE -> containerLocationWarehouseCode(this)
                    FIELD_CONTAINER_LOCATION_BIN_CODE -> containerLocationBinCode(this)
                    FIELD_WAREHOUSE_ORDER_CODE -> warehouseOrderCode(this)
                    FIELD_ITEM_CODE -> itemCode(this)
                    FIELD_LOT_CODE -> lotCode(this)
                    FIELD_STOCK_POSITION_LOCATION_WAREHOUSE_CODE -> stockPositionLocationWarehouseCode(this)
                    FIELD_STOCK_POSITION_LOCATION_BIN_CODE -> stockPositionLocationBinCode(this)
                    FIELD_PAGE -> page(this)
                    FIELD_PAGE_SIZE -> pageSize(this)
                    else -> {throw IllegalArgumentException()}
                }
            }
        }
    }
}