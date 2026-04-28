package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.container

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import ru.bz.baltic_shipyard_inventory_service.domain.model.container.PlacedContainer
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.TransactionStatus
import ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto.JsonFieldsProvider
import java.time.LocalDateTime


@JsonInclude(JsonInclude.Include.ALWAYS)
data class PlacementContainerTransactionDto(
    @field:JsonProperty(JsonFieldsProvider.TRANSACTION_ID) val id: Int,

    @field:JsonProperty(JsonFieldsProvider.TRANSACTION_DATE)
    @field:JsonFormat(shape = JsonFormat.Shape.STRING)
    @field:JsonSerialize(using = LocalDateTimeSerializer::class)
    val created: LocalDateTime,

    @field:JsonProperty(JsonFieldsProvider.TRANSACTION_STATUS) val status: TransactionStatus,
    @field:JsonProperty(JsonFieldsProvider.TRANSACTION_USER_LOGIN) val userLogin: String,
    @field:JsonProperty(JsonFieldsProvider.PLACEMENT_CONTAINER) val placementContainer: PlacementContainerDto
)

fun PlacedContainer.toPlacementContainerTransactionDto() = PlacementContainerTransactionDto(
    id = transaction.id,
    created = transaction.created,
    status = transaction.status,
    userLogin = transaction.userLogin,
    placementContainer = toPlacementContainerDto()

)