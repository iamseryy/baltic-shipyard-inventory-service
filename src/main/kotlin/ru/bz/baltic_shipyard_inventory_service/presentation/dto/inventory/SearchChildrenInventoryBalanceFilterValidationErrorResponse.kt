package ru.bz.baltic_shipyard_inventory_service.presentation.dto.inventory

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.FieldError
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.ViolationDto


private const val FIELDS_DELIMITER = ";"
private const val VALUE_DELIMITER = "="
private const val FIELD_ITEM_CODE = "itemCode"
private const val FIELD_LOT_CODE = "lotCode"
private const val FIELD_PAGE = "page"
private const val FIELD_PAGE_SIZE = "page_size"


@Schema(description="schema.violationbyfieldsdto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class SearchChildrenInventoryBalanceFilterValidationErrorResponse(
    @Schema(description="schema.item.code.desc", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(JsonFields.ITEM_CODE) val itemCode: ViolationDto? = null,

    @Schema(description="schema.lot.code.desc", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(JsonFields.LOT_CODE) val lotCode: ViolationDto? = null,

    @Schema(description="schema.page.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty(JsonFields.PAGE) val page: ViolationDto? = null,

    @Schema(description="schema.pagesize.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty(JsonFields.PAGE_SIZE) val pageSize: ViolationDto? = null,

    ) {
    class EntryBuilder {
        private var itemCode: ViolationDto? = null
        private var lotCode: ViolationDto? = null
        private var page: ViolationDto? = null
        private var pageSize: ViolationDto? = null


        fun itemCode(violation: ViolationDto): EntryBuilder {
            this.itemCode = violation
            return this
        }

        fun lotCode(violation: ViolationDto): EntryBuilder {
            this.lotCode = violation
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

        fun build() = SearchChildrenInventoryBalanceFilterValidationErrorResponse(
            itemCode = itemCode,
            lotCode = lotCode,
            page = page,
            pageSize = pageSize
        )

        fun build(fieldErrors: List<FieldError>): SearchChildrenInventoryBalanceFilterValidationErrorResponse {
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
                    FIELD_ITEM_CODE -> itemCode(this)
                    FIELD_LOT_CODE -> lotCode(this)
                    FIELD_PAGE -> page(this)
                    FIELD_PAGE_SIZE -> pageSize(this)
                    else -> {throw IllegalArgumentException()}
                }
            }
        }

        private fun addField1(field: String, message: String?): EntryBuilder {
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
                    FIELD_ITEM_CODE -> itemCode(this)
                    FIELD_LOT_CODE -> lotCode(this)
                    FIELD_PAGE -> page(this)
                    FIELD_PAGE_SIZE -> pageSize(this)
                    else -> {throw IllegalArgumentException()}
                }
            }
        }

    }
}