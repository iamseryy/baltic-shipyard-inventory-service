package ru.bz.baltic_shipyard_inventory_service.presentation.dto.transaction.measuredremainder

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import ru.bz.baltic_shipyard_inventory_service.presentation.dto.JsonFields
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.measuredremainder.TransactionsOfUpdatableMeasuredRemaindersPagination


@Schema(description="schema.transactionsupdatablemeasuredremainderspaginationdto.desc")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class TransactionsOfUpdatableMeasuredRemaindersPaginationDto(
    @Schema(
        description="schema.transactionsupdatablemeasuredremainderspaginationdto.transactions.desc",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @JsonProperty(JsonFields.TRANSACTIONS)  val transactions: List<TransactionOfUpdatableMeasuredRemaindersDto>,

    @Schema(
        description="schema.pagination.page.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example="3"
    )
    @JsonProperty(JsonFields.PAGE) val page: Int,

    @Schema(
        description="schema.pagination.page_size.desc",
        requiredMode = Schema.RequiredMode.REQUIRED,
        example="10"
    )
    @JsonProperty(JsonFields.PAGE_SIZE) val pageSize: Int
)

fun TransactionsOfUpdatableMeasuredRemaindersPagination.toTransactionsOfUpdatableMeasuredRemaindersPaginationDto() =
    TransactionsOfUpdatableMeasuredRemaindersPaginationDto(
        transactions = transactions.map { it.toTransactionOfUpdatableMeasuredRemaindersDto() },
        page = page,
        pageSize = pageSize
    )
