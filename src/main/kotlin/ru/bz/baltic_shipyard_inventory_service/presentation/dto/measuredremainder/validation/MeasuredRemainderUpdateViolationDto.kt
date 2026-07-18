package ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.validation

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.FieldError
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.ViolationDto
import kotlin.collections.forEach


@Schema(description="schema.validationresponse.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class MeasuredRemainderUpdateViolationDto(
    @Schema(description = "schema.measuredremainder.material.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @field:JsonProperty(JsonFields.MATERIAL) val material: ViolationDto? = null,

    @Schema(description = "schema.measuredremainder.status.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @field:JsonProperty(JsonFields.STATUS) val status: ViolationDto? = null,

    @Schema(description = "schema.measuredremainder.comment.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @field:JsonProperty(JsonFields.COMMENT) val comment: ViolationDto? = null,

    @Schema(description = "schema.measuredremainder.length.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @field:JsonProperty(JsonFields.LENGTH) val length: ViolationDto? = null,

    @Schema(description = "schema.measuredremainder.width.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @field:JsonProperty(JsonFields.WIDTH) val width: ViolationDto? = null,

    @Schema(description = "schema.measuredremainder.width.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @field:JsonProperty(JsonFields.DEPTH) val depth: ViolationDto? = null
){
    class EntryBuilder {
        private var material: ViolationDto? = null
        private var status: ViolationDto? = null
        private var comment: ViolationDto? = null
        private var length: ViolationDto? = null
        private var width: ViolationDto? = null
        private var depth: ViolationDto? = null

        fun material(violation: ViolationDto): EntryBuilder {
            this.material = violation
            return this
        }

        fun status(violation: ViolationDto): EntryBuilder {
            this.status = violation
            return this
        }

        fun comment(violation: ViolationDto): EntryBuilder {
            this.comment = violation
            return this
        }

        fun length(violation: ViolationDto): EntryBuilder {
            this.length = violation
            return this
        }

        fun width(violation: ViolationDto): EntryBuilder {
            this.width = violation
            return this
        }

        fun depth(violation: ViolationDto): EntryBuilder {
            this.depth = violation
            return this
        }

        fun build() = MeasuredRemainderUpdateViolationDto(
            material = material,
            status = status,
            comment = comment,
            length = length,
            width = width,
            depth = depth
        )

        fun build(fieldErrors: List<FieldError>): MeasuredRemainderUpdateViolationDto {
            fieldErrors.forEach { addField(it.field, it.defaultMessage ) }
            return build()
        }

        private fun addField(field: String, message: String?): EntryBuilder {
            var error = mapOf<String, String>()

            if(!message.isNullOrEmpty()) {
                error = message
                    .split(MeasuredRemainderValidationFields.FIELDS_DELIMITER)
                    .map { it.split(MeasuredRemainderValidationFields.VALUE_DELIMITER) }
                    .filter { it.size == 2 }
                    .associate { it[0] to it[1] }
            }

            return with(ViolationDto(error[JsonFields.CODE] ?: "", error[JsonFields.DESCRIPTION] ?: "")) {
                when(field) {
                    MeasuredRemainderValidationFields.FIELD_MATERIAL -> material(this)
                    MeasuredRemainderValidationFields.FIELD_STATUS -> status(this)
                    MeasuredRemainderValidationFields.FIELD_COMMENT -> comment(this)
                    MeasuredRemainderValidationFields.FIELD_LENGTH -> length(this)
                    MeasuredRemainderValidationFields.FIELD_WIDTH -> width(this)
                    MeasuredRemainderValidationFields.FIELD_DEPTH -> depth(this)
                    else -> {throw IllegalArgumentException()}
                }
            }
        }
    }
}

