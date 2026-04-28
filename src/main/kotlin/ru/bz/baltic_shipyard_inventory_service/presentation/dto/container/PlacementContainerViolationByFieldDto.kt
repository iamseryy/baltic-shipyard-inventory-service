package ru.bz.baltic_shipyard_inventory_service.presentation.dto.container

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
private const val FIELD_BIN_CODE = "binCodeTarget"
private const val FIELD_CONTAINER_CODE = "containerCode"


@Schema(description="schema.violationbyfieldsdto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class PlacementContainerViolationByFieldDto(
    @Schema(description = "schema.container.code.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @field:JsonProperty(JsonFields.CONTAINER_CODE) val containerCode: ViolationDto? = null,

    @Schema(description = "schema.bin.code.desc", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @field:JsonProperty(JsonFields.BIN_CODE_TARGET) val binCodeTarget: ViolationDto? = null
) {
    class EntryBuilder {
        private var containerCode: ViolationDto? = null
        private var binCodeTarget: ViolationDto? = null

        fun containerCode(violation: ViolationDto): EntryBuilder {
            this.containerCode = containerCode
            return this
        }

        fun binCodeTarget(violation: ViolationDto): EntryBuilder {
            this.binCodeTarget = binCodeTarget
            return this
        }

        fun build() = PlacementContainerViolationByFieldDto(
            containerCode = containerCode,
            binCodeTarget = binCodeTarget
        )

        fun build(fieldErrors: List<FieldError>): PlacementContainerViolationByFieldDto {
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
                    FIELD_BIN_CODE-> binCodeTarget(this)
                    else -> {throw IllegalArgumentException()}
                }
            }
        }
    }
}

fun ViolationByFields.toPlacementContainerViolationByFieldDto() = PlacementContainerViolationByFieldDto(
    containerCode = containerCodeViolation?.toViolationDto(),
    binCodeTarget = binCodeViolation?.toViolationDto()
)