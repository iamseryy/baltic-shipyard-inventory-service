package ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.abortreason.AbortReasonDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.measuredremainder.InventoriedMeasuredRemaindersViolationByFieldDto

private const val FIELDS_DELIMITER = ";"
private const val VALUE_DELIMITER = "="
private const val FIELD_WAREHOUSE = "warehouse"
private const val FIELD_LOCATION = "location"
private const val MEASURED_REMAINDERS = "measuredRemainders"


private const val REGEX_PATTERN_FOR_SUBSTRING_INDEX = "\\[(\\d+)\\]"
private const val REGEX_PATTERN_FOR_SUBSTRING_FIELD = "^[^.]+\\."


@Schema(description="schema.validationerrorresponse.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class InventoriedMeasuredRemaindersValidationErrorResponse(
    @Schema(description = "schema.warehouse.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @field:JsonProperty(JsonFields.WAREHOUSE) val warehouse: String? = null,

    @Schema(description = "schema.location.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @field:JsonProperty(JsonFields.LOCATION) val location: String? = null,

    @Schema(description = "schema.measuredremainders.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @field:JsonProperty(JsonFields.MEASURED_REMAINDERS) val measuredRemainders: List<InventoriedMeasuredRemainderValidationErrorResponse>? = null,

    @Schema(description="schema.abortreasondto.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty(JsonFields.ABORT_REASON)
    val abortReason: AbortReasonDto<InventoriedMeasuredRemaindersViolationByFieldDto>? = null
)
//{
//    class EntryBuilder{
//        private var warehouse: ViolationDto? = null
//        private var location: ViolationDto? = null
//        private var measuredRemainders: ViolationDto? = null
//
//        fun warehouse(violation: ViolationDto): EntryBuilder {
//            this.warehouse = violation
//            return this
//        }
//
//        fun location(violation: ViolationDto): EntryBuilder {
//            this.location = violation
//            return this
//        }
//
//        fun measuredRemainders(violation: ViolationDto): EntryBuilder {
//            this.measuredRemainders = violation
//            return this
//        }
//
//        fun build() =
//            InventoriedMeasuredRemaindersValidationErrorResponse(
//                warehouse = warehouse,
//                location = location,
//                measuredRemainders = measuredRemainders
//            )
//
//        fun build(fieldErrors: List<FieldError>): InventoriedMeasuredRemaindersValidationErrorResponse {
//            fieldErrors.forEach { addField(it.field, it.defaultMessage, ) }
//            return this.build()
//        }
//
//        fun build(bindingResult: BindingResult): InventoriedMeasuredRemaindersValidationErrorResponse {
//            val regexIndex = Regex(REGEX_PATTERN_FOR_SUBSTRING_INDEX)
//            val regexField = Regex(REGEX_PATTERN_FOR_SUBSTRING_FIELD)
//
//            val measuredRemaindersFieldError = bindingResult.fieldErrors.filter { regexIndex.containsMatchIn(it.field)  }
//
//            val measuredRemaindersFieldErrorGrouping = measuredRemaindersFieldError.groupBy {
//                regexIndex.find(it.field)?.groupValues?.get(1)?.toInt()
//            }
//
//
////            val test2 = measuredRemaindersFieldErrorGrouping.map {
////                MeasuredRemainderForUpdateValidationErrorByFieldResponse.EntryBuilder().build(
////                    it.value.map {fieldError ->
////                        FieldError(
////                            fieldError.objectName,
////                            fieldError.field.replace(regexField, ""),
////                            fieldError.defaultMessage ?: ""
////                        ) })
////            }
//
//
//
//
//            return this.build()
//        }
//
//        private fun addField(field: String, message: String?): InventoriedMeasuredRemaindersValidationErrorResponse.EntryBuilder {
//            var error = mapOf<String, String>()
//
//            if(!message.isNullOrEmpty()) {
//                error = message
//                    .split(FIELDS_DELIMITER)
//                    .map { it.split(VALUE_DELIMITER) }
//                    .filter { it.size == 2 }
//                    .associate { it[0] to it[1] }
//            }
//
//            return with(ViolationDto(error[JsonFields.CODE] ?: "", error[JsonFields.DESCRIPTION] ?: "")) {
//                when(field) {
//                    FIELD_WAREHOUSE -> warehouse(this)
//                    FIELD_LOCATION -> location(this)
//                    MEASURED_REMAINDERS-> measuredRemainders(this)
//                    else -> {throw IllegalArgumentException()}
//                }
//            }
//        }
//    }
//}


