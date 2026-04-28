package ru.bz.baltic_shipyard_inventory_service.presentation.dto.transaction

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.FieldError
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.violation.ViolationDto


private const val FIELDS_DELIMITER = ";"
private const val VALUE_DELIMITER = "="
private const val FIELD_ID = "id"
private const val FIELD_CREATED_FROM = "createdFrom"
private const val FIELD_CREATED_TO = "createdTo"
private const val FIELD_STATUS = "status"
private const val FIELD_USER = "userLogin"
private const val FIELD_PAGE = "page"
private const val FIELD_PAGE_SIZE = "pageSize"


@Schema(description="schema.validationerrorresponse.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class SearchTransactionFilterValidationErrorResponse(
    @Schema(
        description="schema.transaction.id.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.TRANSACTION_ID) val id: ViolationDto? = null,

    @Schema(
        description="schema.searchtransactionfilterdto.datefrom.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.TRANSACTION_DATE_FROM) val createdFrom: ViolationDto? = null,

    @Schema(
        description="schema.searchtransactionfilterdto.dateto.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.TRANSACTION_DATE_TO) val createdTo: ViolationDto? = null,

    @Schema(
        description="schema.transaction.status.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.TRANSACTION_STATUS) val status: ViolationDto? = null,

    @Schema(
        description="schema.transaction.user.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.TRANSACTION_USER_LOGIN) val user: ViolationDto? = null,

    @Schema(
        description="schema.pagination.page.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.PAGE) val page: ViolationDto? = null,

    @Schema(
        description="schema.pagination.page_size.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @field:JsonProperty(JsonFields.PAGE_SIZE) val pageSize: ViolationDto? = null,
)
{
    class EntryBuilder {
        private var id: ViolationDto? = null
        private var createdFrom: ViolationDto? = null
        private var createdTo: ViolationDto? = null
        private var status: ViolationDto? = null
        private var user: ViolationDto? = null
        private var page: ViolationDto? = null
        private var pageSize: ViolationDto? = null


        fun id(violation: ViolationDto): EntryBuilder {
            this.id = violation
            return this
        }

        fun createdFrom(violation: ViolationDto): EntryBuilder {
            this.createdFrom = violation
            return this
        }

        fun createdTo(violation: ViolationDto): EntryBuilder {
            this.createdTo = violation
            return this
        }

        fun status(violation: ViolationDto): EntryBuilder {
            this.status = violation
            return this
        }

        fun user(violation: ViolationDto): EntryBuilder {
            this.user = violation
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

        private fun build() = SearchTransactionFilterValidationErrorResponse(
            id = id,
            createdFrom = createdFrom,
            createdTo = createdTo,
            status = status,
            user = user,
            page = page,
            pageSize = pageSize
        )

        fun build(fieldErrors: List<FieldError>): SearchTransactionFilterValidationErrorResponse {
            fieldErrors.forEach { addField(it.field, it.defaultMessage, ) }
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
                    FIELD_CREATED_FROM -> createdFrom(this)
                    FIELD_CREATED_TO -> createdTo(this)
                    FIELD_STATUS -> status(this)
                    FIELD_USER -> user(this)
                    FIELD_PAGE -> page(this)
                    FIELD_PAGE_SIZE -> pageSize(this)
                    else -> {throw IllegalArgumentException()}
                }
            }
        }
    }
}