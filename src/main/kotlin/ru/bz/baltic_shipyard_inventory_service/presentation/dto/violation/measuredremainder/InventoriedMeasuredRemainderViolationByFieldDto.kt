package ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.measuredremainder

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.FieldError
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.ViolationDto


private const val FIELDS_DELIMITER = ";"
private const val VALUE_DELIMITER = "="
private const val FIELD_ID = "id"
private const val FIELD_SEQUENCE = "sequence"
private const val FIELD_STATUS = "status"
private const val FIELD_COMMENT = "comment"
private const val FIELD_LENGTH = "length"
private const val FIELD_WIDTH = "width"


@Schema(description="schema.violationbyfieldsdto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class InventoriedMeasuredRemainderViolationByFieldDto(
    @Schema(description="schema.measuredremainder.id.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty(JsonFields.WAREHOUSE) val id: ViolationDto? = null,

    @Schema(description="schema.measuredremainder.sequence.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty(JsonFields.SEQUENCE) val sequence: ViolationDto? = null,

    @Schema(description="schema.measuredremainder.status.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty(JsonFields.STATUS) val status: ViolationDto? = null,

    @Schema(description="schema.measuredremainder.comment.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty(JsonFields.COMMENT) val comment: ViolationDto? = null,

    @Schema(description="schema.measuredremainder.length.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty(JsonFields.LENGTH) val length: ViolationDto? = null,

    @Schema(description="schema.measuredremainder.width.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonProperty(JsonFields.WIDTH) val width: ViolationDto? = null
) {
    class EntryBuilder {
        private var id: ViolationDto? = null
        private var sequence: ViolationDto? = null
        private var status: ViolationDto? = null
        private var comment: ViolationDto? = null
        private var length: ViolationDto? = null
        private var width: ViolationDto? = null

        fun id(violation: ViolationDto): EntryBuilder {
            this.id = violation
            return this
        }

        fun sequence(violation: ViolationDto): EntryBuilder {
            this.sequence = violation
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

        fun build() = InventoriedMeasuredRemainderViolationByFieldDto(
            id = id,
            sequence = sequence,
            status = status,
            comment = comment,
            length = length,
            width = width
        )

        fun build(fieldErrors: List<FieldError>): InventoriedMeasuredRemainderViolationByFieldDto {
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
                    FIELD_ID -> id(this)
                    FIELD_SEQUENCE -> sequence(this)
                    FIELD_STATUS -> status(this)
                    FIELD_COMMENT -> comment(this)
                    FIELD_LENGTH -> length(this)
                    FIELD_WIDTH -> width(this)
                    else -> {throw IllegalArgumentException()}
                }
            }
        }
    }
}
