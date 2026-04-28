package ru.bz.baltic_shipyard_inventory_service.presentation.dto.filter

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.PastOrPresent
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.presentation.validation.annotation.EnumTypeSubset
import ru.bz.baltic_shipyard_inventory_service.presentation.validation.annotation.NotEmpty
import ru.bz.baltic_shipyard_inventory_service.domain.model.filter.SearchTransactionFilter
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.TransactionStatus
import java.time.LocalDateTime


private const val TRANSACTION_STATUS_ALLOWABLE_VALUES =  "RUNNING, COMPLETED, FAILED"

@Schema(description="schema.searchtransactionfilterdto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class SearchTransactionFilterDto(

    @Schema(
        description="schema.transaction.id.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="11"
    )
    @get:Min(message = "code={error.1003.code};description={error.1003.description}", value = 1)
    @get:Max(message = "code={error.1004.code};description={error.1004.description}", value = 1_000_000_000)
    @JsonProperty(JsonFields.TRANSACTION_ID)
    val id: Int? = null,

    @Schema(
        description="schema.searchtransactionfilterdto.datefrom.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        type = "string",
        example="2024-12-26T09:00:48.169662"
    )
    @field:PastOrPresent(message = "code={error.1008.code};description={error.1008.description}")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonProperty(JsonFields.TRANSACTION_DATE_FROM)
    val createdFrom: LocalDateTime? = null,

    @Schema(
        description="schema.searchtransactionfilterdto.dateto.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        type = "string",
        example="2024-12-27T23:00:48.169662"
    )
    @field:PastOrPresent(message = "code={error.1008.code};description={error.1008.description}")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @JsonProperty(JsonFields.TRANSACTION_DATE_TO)
    val createdTo: LocalDateTime? = null,

    @Schema(
        description="schema.transaction.status.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        allowableValues = ["RUNNING", "COMPLETED", "FAILED"],
        type = "String",
        example="RUNNING"
    )
    @EnumTypeSubset(
        anyOf = [TransactionStatus.RUNNING, TransactionStatus.COMPLETED, TransactionStatus.FAILED],
        message = "code={error.1009.code};description={error.1009.description}$TRANSACTION_STATUS_ALLOWABLE_VALUES"
    )
    @JsonProperty(JsonFields.TRANSACTION_STATUS)
    val status: TransactionStatus? = null,

    @Schema(
        description="schema.transaction.user.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="user123"
    )
    @NotEmpty
    @JsonProperty(JsonFields.TRANSACTION_USER_LOGIN)
    val userLogin: String? = null,

    @Schema(
        description="schema.pagination.page.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="11"
    )
    @get:Min(message = "code={error.1002.code};description={error.1002.description}", value = 0)
    @get:Max(message = "code={error.1004.code};description={error.1004.description}", value = 1_000_000_000)
    @JsonProperty(JsonFields.PAGE)
    val page: Int = 0,

    @Schema(
        description="schema.pagination.page_size.desc",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
        example="10"
    )
    @get:Min(message = "code={error.1003.code};description={error.1003.description}", value = 1)
    @get:Max(message = "code={error.1004.code};description={error.1004.description}", value = 1_000_000_000)
    @JsonProperty(JsonFields.PAGE_SIZE)
    val pageSize: Int = 10
)

fun SearchTransactionFilterDto.toSearchTransactionFilter() =
    SearchTransactionFilter(
        id = id,
        createdFrom = createdFrom,
        createdTo = createdTo,
        status = status,
        userLogin = userLogin,
        page = page,
        pageSize = pageSize
)
