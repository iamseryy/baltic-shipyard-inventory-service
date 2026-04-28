package ru.bz.baltic_shipyard_inventory_service.presentation.dto.measuredremainder

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.FieldError
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.ViolationDto
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields


private const val FIELDS_DELIMITER = ";"
private const val VALUE_DELIMITER = "="
private const val FIELD_ID = "id"
private const val FIELD_PROJECT = "project"
private const val FIELD_REMAINDER_LIKE = "remainderLike"
private const val FIELD_MATERIAL_LIKE = "materialLike"
private const val FIELD_WAREHOUSE = "warehouse"
private const val FIELD_LOCATION = "location"
private const val FIELD_STATUS = "status"
private const val FIELD_COMMENT = "comment"
private const val FIELD_LENGTH_FROM = "lengthFrom"
private const val FIELD_WIDTH_FROM = "widthFrom"
private const val FIELD_DEPTH = "depth"
private const val FIELD_PAGE = "page"
private const val FIELD_PAGE_SIZE = "pageSize"


@Schema(description="schema.validationerrorresponse.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class SearchMeasuredRemainderFilterValidationErrorResponse(
    @Schema(
        description = "schema.measuredremainder.id.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.ID) val id: ViolationDto? = null,

    @Schema(
        description="schema.searchmeasuredremainderfilterdto.remainder.desc",
        requiredMode= Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.REMAINDER_LIKE) val remainderLike: ViolationDto? = null,

    @Schema(
        description="schema.project.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.PROJECT) val project: ViolationDto? = null,

    @Schema(
        description="schema.searchmeasuredremainderfilterdto.material.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.MATERIAL_LIKE) val materialLike: ViolationDto? = null,

    @Schema(
        description="schema.warehouse.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.WAREHOUSE) val warehouse: ViolationDto? = null,

    @Schema(
        description="schema.location.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.LOCATION) val location: ViolationDto? = null,

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
        description="schema.pagination.page.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.PAGE) val page: ViolationDto? = null,

    @Schema(
        description="schema.pagination.page_size.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.PAGE_SIZE) val pageSize: ViolationDto? = null
) {
    class EntryBuilder {
        private var id: ViolationDto? = null
        private var remainderLike: ViolationDto? = null
        private var project: ViolationDto? = null
        private var materialLike: ViolationDto? = null
        private var warehouse: ViolationDto? = null
        private var location: ViolationDto? = null
        private var status: ViolationDto? = null
        private var comment: ViolationDto? = null
        private var lengthFrom: ViolationDto? = null
        private var widthFrom: ViolationDto? = null
        private var depth: ViolationDto? = null
        private var page: ViolationDto? = null
        private var pageSize: ViolationDto? = null

        fun id(violation: ViolationDto): EntryBuilder {
            this.id = violation
            return this
        }

        fun remainderLike(violation: ViolationDto): EntryBuilder {
            this.remainderLike = violation
            return this
        }

        fun project(violation: ViolationDto): EntryBuilder {
            this.project = violation
            return this
        }

        fun materialLike(violation: ViolationDto): EntryBuilder {
            this.materialLike = violation
            return this
        }

        fun warehouse(violation: ViolationDto): EntryBuilder {
            this.warehouse = violation
            return this
        }

        fun location(violation: ViolationDto): EntryBuilder {
            this.location = violation
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

        fun page(violation: ViolationDto): EntryBuilder {
            this.page = violation
            return this
        }

        fun pageSize(violation: ViolationDto): EntryBuilder {
            this.pageSize = violation
            return this
        }

        fun build() =
            SearchMeasuredRemainderFilterValidationErrorResponse(
                id = id,
                remainderLike = remainderLike,
                project = project,
                materialLike = materialLike,
                warehouse = warehouse,
                location = location,
                status = status,
                comment = comment,
                lengthFrom = lengthFrom,
                widthFrom = widthFrom,
                depth = depth,
                page = page,
                pageSize = pageSize
            )

        fun build(fieldErrors: List<FieldError>): SearchMeasuredRemainderFilterValidationErrorResponse {
            fieldErrors.forEach { addField(it.field, it.defaultMessage, ) }
            return this.build()
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
                    FIELD_PROJECT -> project(this)
                    FIELD_REMAINDER_LIKE -> remainderLike(this)
                    FIELD_MATERIAL_LIKE -> materialLike(this)
                    FIELD_WAREHOUSE -> warehouse(this)
                    FIELD_LOCATION -> location(this)
                    FIELD_STATUS -> status(this)
                    FIELD_COMMENT -> comment(this)
                    FIELD_LENGTH_FROM -> lengthFrom(this)
                    FIELD_WIDTH_FROM -> widthFrom(this)
                    FIELD_DEPTH -> depth(this)
                    FIELD_PAGE -> page(this)
                    FIELD_PAGE_SIZE -> pageSize(this)
                    else -> {throw IllegalArgumentException()}
                }
            }
        }
    }
}