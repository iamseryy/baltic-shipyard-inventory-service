package ru.bz.baltic_shipyard_inventory_service.presentation.dto.mechanicalpart

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.FieldError
import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.ViolationByFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.ViolationDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.toViolationDto


private const val FIELDS_DELIMITER = ";"
private const val VALUE_DELIMITER = "="
private const val FIELD_PROJECT_CODE = "projectCode"
private const val FIELD_ELEMENT_CODE = "elementCode"
private const val FIELD_ROOT_ITEM_CODE = "rootItemCode"
private const val FIELD_PARENT_ITEM_CODE = "parentItemCode"
private const val FIELD_PARENT_ITEM_POSITION = "parentItemPosition"
private const val FIELD_PARENT_OPERATION_CODE = "parentOperationCode"
private const val FIELD_CHILD_OPERATION_CODE = "childOperationCode"
private const val FIELD_REPORTED_QUANTITY = "reportedQuantity"


@Schema(description="schema.violationbyfieldsdto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class ReportedOperationValidationErrorResponse(
    @Schema(description="schema.project.desc", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(JsonFields.PROJECT) val projectCode: ViolationDto? = null,

    @Schema(description="schema.element.desc", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(JsonFields.ELEMENT) val elementCode: ViolationDto? = null,

    @Schema(description="schema.root.item.desc", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(JsonFields.ROOT_ITEM) val rootItemCode: ViolationDto? = null,

    @Schema(description="schema.master.item.desc", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(JsonFields.MASTER_ITEM) val parentItemCode: ViolationDto? = null,

    @Schema(description="schema.master.item.position.desc", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(JsonFields.MASTER_ITEM_POSITION) val parentItemPosition: ViolationDto? = null,

    @Schema(description="schema.master.operation.desc", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(JsonFields.MASTER_OPERATION) val parentOperationCode: ViolationDto? = null,

    @Schema(description="schema.operation.desc", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(JsonFields.OPERATION) val childOperationCode: ViolationDto? = null,

    @Schema(description="schema.quantity.desc", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty(JsonFields.QUANTITY) val reportedQuantity: ViolationDto? = null
) {
    class EntryBuilder {
        var projectCode: ViolationDto? = null
        var elementCode: ViolationDto? = null
        var rootItemCode: ViolationDto? = null
        var parentItemCode: ViolationDto? = null
        var parentItemPosition: ViolationDto? = null
        var parentOperationCode: ViolationDto? = null
        var childOperationCode: ViolationDto? = null
        var reportedQuantity: ViolationDto? = null

        fun projectCode(violation: ViolationDto): EntryBuilder {
            this.projectCode = violation
            return this
        }

        fun elementCode(violation: ViolationDto): EntryBuilder {
            this.elementCode = violation
            return this
        }

        fun rootItemCode(violation: ViolationDto): EntryBuilder {
            this.rootItemCode = violation
            return this
        }

        fun parentItemCode(violation: ViolationDto): EntryBuilder {
            this.parentItemCode = violation
            return this
        }

        fun parentItemPosition(violation: ViolationDto): EntryBuilder {
            this.parentItemPosition = violation
            return this
        }

        fun parentOperationCode(violation: ViolationDto): EntryBuilder {
            this.parentOperationCode = violation
            return this
        }

        fun childOperationCode(violation: ViolationDto): EntryBuilder {
            this.childOperationCode = violation
            return this
        }

        fun reportedQuantity(violation: ViolationDto): EntryBuilder {
            this.reportedQuantity = violation
            return this
        }

        fun build() = ReportedOperationValidationErrorResponse(
            projectCode = this.projectCode,
            elementCode = this.elementCode,
            rootItemCode = this.rootItemCode,
            parentItemPosition = this.parentItemPosition,
            parentOperationCode = this.parentOperationCode,
            childOperationCode = this.childOperationCode,
            reportedQuantity = this.reportedQuantity,
        )

        fun build(fieldErrors: List<FieldError>): ReportedOperationValidationErrorResponse {
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
                    FIELD_PROJECT_CODE -> projectCode(this)
                    FIELD_ELEMENT_CODE -> elementCode(this)
                    FIELD_ROOT_ITEM_CODE -> rootItemCode(this)
                    FIELD_PARENT_ITEM_CODE -> parentItemCode(this)
                    FIELD_PARENT_ITEM_POSITION -> parentItemPosition(this)
                    FIELD_PARENT_OPERATION_CODE -> parentOperationCode(this)
                    FIELD_CHILD_OPERATION_CODE -> childOperationCode(this)
                    FIELD_REPORTED_QUANTITY -> reportedQuantity(this)
                    else -> {throw IllegalArgumentException()}
                }
            }
        }
    }
}

fun ViolationByFields.toReportedOperationValidationErrorResponse() = ReportedOperationValidationErrorResponse(
    projectCode = projectCodeViolation?.toViolationDto(),
    elementCode = elementCodeViolation?.toViolationDto(),
    rootItemCode = rootItemCodeViolation?.toViolationDto(),
    parentItemCode = parentItemCodeViolation?.toViolationDto(),
    parentItemPosition = parentItemPositionViolation?.toViolationDto(),
    parentOperationCode = parentOperationCodeViolation?.toViolationDto(),
    childOperationCode = childOperationCodeViolation?.toViolationDto(),
    reportedQuantity = quantityViolation?.toViolationDto()
)
