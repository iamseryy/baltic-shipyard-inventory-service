package ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.transfer

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.FieldError
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.ViolationDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.toViolationDto
import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.ViolationByFields


private const val FIELDS_DELIMITER = ";"
private const val VALUE_DELIMITER = "="
private const val FIELD_WAREHOUSE_CODE = "warehouseCode"
private const val FIELD_BIN_CODE_SOURCE = "binCodeSource"
private const val FIELD_BIN_CODE_TARGET = "binCodeTarget"
private const val FIELD_ITEM_CODE = "itemCode"
private const val FIELD_LOT_CODE = "lotCode"
private const val FIELD_QUANTITY = "quantity"

@Schema(description="schema.violationbyfieldsdto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class TransferItemByLocationsViolationByFieldDto(
    @Schema(description = "schema.warehouse.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @field:JsonProperty(JsonFields.WAREHOUSE_CODE) val warehouseCode: ViolationDto? = null,

    @Schema(description = "schema.location.source.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @field:JsonProperty(JsonFields.BIN_CODE_SOURCE) val binCodeSource: ViolationDto? = null,

    @Schema(description = "schema.location.target.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @field:JsonProperty(JsonFields.BIN_CODE_TARGET) val binCodeTarget: ViolationDto? = null,

    @Schema(description = "schema.item.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @field:JsonProperty(JsonFields.ITEM_CODE) val itemCode: ViolationDto? = null,

    @Schema(description = "schema.lot.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @field:JsonProperty(JsonFields.LOT_CODE) val lotCode: ViolationDto? = null,

    @Schema(description = "schema.quantity.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @field:JsonProperty(JsonFields.QUANTITY) val quantity: ViolationDto? = null,
) {
    class EntryBuilder {
        private var warehouseCode: ViolationDto? = null
        private var binCodeSource: ViolationDto? = null
        private var binCodeTarget: ViolationDto? = null
        private var itemCode: ViolationDto? = null
        private var lotCode: ViolationDto? = null
        private var quantity: ViolationDto? = null

        fun warehouseCode(violation: ViolationDto): EntryBuilder {
            this.warehouseCode = violation
            return this
        }

        fun binCodeSource(violation: ViolationDto): EntryBuilder {
            this.binCodeSource = violation
            return this
        }

        fun binCodeTarget(violation: ViolationDto): EntryBuilder {
            this.binCodeTarget = violation
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

        fun quantity(violation: ViolationDto): EntryBuilder {
            this.quantity = violation
            return this
        }

        fun build() = TransferItemByLocationsViolationByFieldDto(
            warehouseCode = warehouseCode,
            binCodeSource = binCodeSource,
            binCodeTarget = binCodeTarget,
            itemCode = itemCode,
            lotCode = lotCode,
            quantity = quantity
        )

        fun build(fieldErrors: List<FieldError>): TransferItemByLocationsViolationByFieldDto {
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
                    FIELD_WAREHOUSE_CODE -> warehouseCode(this)
                    FIELD_BIN_CODE_SOURCE -> binCodeSource(this)
                    FIELD_BIN_CODE_TARGET -> binCodeTarget(this)
                    FIELD_ITEM_CODE -> itemCode(this)
                    FIELD_LOT_CODE -> lotCode(this)
                    FIELD_QUANTITY -> quantity(this)
                    else -> {throw IllegalArgumentException()}
                }
            }
        }

    }
}

fun ViolationByFields.toTransferItemByLocationsViolationByFieldDto() = TransferItemByLocationsViolationByFieldDto(
    warehouseCode = warehouseCodeViolation?.toViolationDto(),
    binCodeSource = binCodeSourceViolation?.toViolationDto(),
    binCodeTarget = binCodeTargetViolation?.toViolationDto(),
    itemCode = itemCodeViolation?.toViolationDto(),
    lotCode = lotCodeViolation?.toViolationDto(),
    quantity = quantityViolation?.toViolationDto()
)
