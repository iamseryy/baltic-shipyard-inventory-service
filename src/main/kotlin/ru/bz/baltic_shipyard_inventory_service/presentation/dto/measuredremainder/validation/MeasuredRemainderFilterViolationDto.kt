package ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder.validation

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.FieldError
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.ViolationDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields


@Schema(description="schema.violationbyfieldsdto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class MeasuredRemainderFilterViolationDto(
    @Schema(
        description = "schema.measuredremainder.id.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.ID) val id: ViolationDto? = null,

    @Schema(
        description="schema.searchmeasuredremainderfilterdto.code.remainder.desc",
        requiredMode= Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.CODE_LIKE) val codeLike: ViolationDto? = null,

    @Schema(
        description="schema.project.code.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.PROJECT_CODE) val projectCode: ViolationDto? = null,

    @Schema(
        description="schema.searchmeasuredremainderfilterdto.material.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.MATERIAL_LIKE) val materialLike: ViolationDto? = null,

    @Schema(
        description="schema.warehouse.code.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.WAREHOUSE_CODE) val warehouseCode: ViolationDto? = null,

    @Schema(
        description="schema.bin.code.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.BIN_CODE) val binCode: ViolationDto? = null,

    @Schema(
        description="schema.measuredremainder.status.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.STATUS) val status: ViolationDto? = null,

    @Schema(
        description="schema.measuredremainder.comment.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.COMMENT) val comment: ViolationDto? = null,

    @Schema(
        description="schema.searchmeasuredremainderfilterdto.length.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.LENGTH_FROM) val lengthFrom: ViolationDto? = null,

    @Schema(
        description="schema.searchmeasuredremainderfilterdto.width.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.WIDTH_FROM) val widthFrom: ViolationDto? = null,

    @Schema(
        description="schema.measuredremainder.depth.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.DEPTH) val depth: ViolationDto? = null,

    @Schema(
        description="schema.pagination.number.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.PAGE_NUMBER) val pageNumber: ViolationDto? = null,

    @Schema(
        description="schema.pagination.size.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.PAGE_SIZE) val pageSize: ViolationDto? = null
) {
    class EntryBuilder {
        private var id: ViolationDto? = null
        private var codeLike: ViolationDto? = null
        private var projectCode: ViolationDto? = null
        private var materialLike: ViolationDto? = null
        private var warehouseCode: ViolationDto? = null
        private var binCode: ViolationDto? = null
        private var status: ViolationDto? = null
        private var comment: ViolationDto? = null
        private var lengthFrom: ViolationDto? = null
        private var widthFrom: ViolationDto? = null
        private var depth: ViolationDto? = null
        private var pageNumber: ViolationDto? = null
        private var pageSize: ViolationDto? = null

        fun id(violation: ViolationDto): EntryBuilder {
            this.id = violation
            return this
        }

        fun codeLike(violation: ViolationDto): EntryBuilder {
            this.codeLike = violation
            return this
        }

        fun projectCode(violation: ViolationDto): EntryBuilder {
            this.projectCode = violation
            return this
        }

        fun materialLike(violation: ViolationDto): EntryBuilder {
            this.materialLike = violation
            return this
        }

        fun warehouseCode(violation: ViolationDto): EntryBuilder {
            this.warehouseCode = violation
            return this
        }

        fun binCode(violation: ViolationDto): EntryBuilder {
            this.binCode = violation
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

        fun lengthFrom(violation: ViolationDto): EntryBuilder {
            this.lengthFrom = violation
            return this
        }

        fun widthFrom(violation: ViolationDto): EntryBuilder {
            this.widthFrom = violation
            return this
        }

        fun depth(violation: ViolationDto): EntryBuilder {
            this.depth = violation
            return this
        }

        fun pageNumber(violation: ViolationDto): EntryBuilder {
            this.pageNumber = violation
            return this
        }

        fun pageSize(violation: ViolationDto): EntryBuilder {
            this.pageSize = violation
            return this
        }

        fun build() =
            MeasuredRemainderFilterViolationDto(
                id = id,
                codeLike = codeLike,
                projectCode = projectCode,
                materialLike = materialLike,
                warehouseCode = warehouseCode,
                binCode = binCode,
                status = status,
                comment = comment,
                lengthFrom = lengthFrom,
                widthFrom = widthFrom,
                depth = depth,
                pageNumber = pageNumber,
                pageSize = pageSize
            )

        fun build(fieldErrors: List<FieldError>): MeasuredRemainderFilterViolationDto {
            fieldErrors.forEach { addField(it.field, it.defaultMessage, ) }
            return this.build()
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
                    MeasuredRemainderValidationFields.FIELD_ID -> id(this)
                    MeasuredRemainderValidationFields.FIELD_PROJECT_CODE -> projectCode(this)
                    MeasuredRemainderValidationFields.FIELD_CODE_LIKE -> codeLike(this)
                    MeasuredRemainderValidationFields.FIELD_MATERIAL_LIKE -> materialLike(this)
                    MeasuredRemainderValidationFields.FIELD_WAREHOUSE_CODE -> warehouseCode(this)
                    MeasuredRemainderValidationFields.FIELD_BIN_CODE -> binCode(this)
                    MeasuredRemainderValidationFields.FIELD_STATUS -> status(this)
                    MeasuredRemainderValidationFields.FIELD_COMMENT -> comment(this)
                    MeasuredRemainderValidationFields.FIELD_LENGTH_FROM -> lengthFrom(this)
                    MeasuredRemainderValidationFields.FIELD_WIDTH_FROM -> widthFrom(this)
                    MeasuredRemainderValidationFields.FIELD_DEPTH -> depth(this)
                    MeasuredRemainderValidationFields.FIELD_PAGE_NUMBER -> pageNumber(this)
                    MeasuredRemainderValidationFields.FIELD_PAGE_SIZE -> pageSize(this)
                    else -> {throw IllegalArgumentException()}
                }
            }
        }
    }
}