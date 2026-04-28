package ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.measuredremainder

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.FieldError
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.ViolationDto


private const val FIELDS_DELIMITER = ";"
private const val VALUE_DELIMITER = "="
private const val FIELD_WAREHOUSE = "warehouse"
private const val FIELD_LOCATION = "location"



@Schema(description="schema.violationbyfieldsdto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class InventoriedMeasuredRemaindersViolationByFieldDto(

    @Schema(description="schema.warehouse.desc", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(JsonFields.WAREHOUSE) val warehouse: ViolationDto? = null,

    @Schema(description="schema.location.desc", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(JsonFields.LOCATION) val location: ViolationDto? = null,

    @Schema(description="schema.measuredremainders.desc", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(JsonFields.MEASURED_REMAINDERS) val measuredRemainders: List<InventoriedMeasuredRemainderViolationByFieldDto>? = null,
) {
    class EntryBuilder {
        private var warehouse: ViolationDto? = null
        private var location: ViolationDto? = null
        private var measuredRemainders: List<InventoriedMeasuredRemainderViolationByFieldDto>? = null

        fun warehouse(violation: ViolationDto): EntryBuilder {
            this.warehouse = violation
            return this
        }

        fun location(violation: ViolationDto): EntryBuilder {
            this.location = violation
            return this
        }

        fun measuredRemainders(measuredRemainders: List<InventoriedMeasuredRemainderViolationByFieldDto>): EntryBuilder {
            this.measuredRemainders = measuredRemainders
            return this
        }

        fun build() = InventoriedMeasuredRemaindersViolationByFieldDto(
            warehouse = warehouse,
            location = location,
            measuredRemainders = measuredRemainders
        )

        fun build(fieldErrors: List<FieldError>): InventoriedMeasuredRemaindersViolationByFieldDto {
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
                    FIELD_WAREHOUSE -> warehouse(this)
                    FIELD_LOCATION -> location(this)
                    else -> {throw IllegalArgumentException()}
                }
            }
        }
    }
}