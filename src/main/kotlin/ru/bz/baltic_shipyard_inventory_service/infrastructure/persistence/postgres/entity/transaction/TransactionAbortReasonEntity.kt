package ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import ru.bz.baltic_shipyard_inventory_service.domain.model.violation.AbortReason
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.dto.abortreason.AbortReasonDbDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.dto.abortreason.toAbortReasonDbDto
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.dto.violation.toViolation
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.dto.violation.toViolationByFields
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.EntityFields
import java.io.Serializable


@Entity
@Table(schema = "inventory_service", name = "transaction_abortreason")
@JsonInclude(JsonInclude.Include.NON_NULL)
class TransactionAbortReasonEntity (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @field:Column(name = EntityFields.ABORT_REASON)
    @JdbcTypeCode(SqlTypes.JSON)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val abortReason: AbortReasonDbDto
): Serializable

fun AbortReason.toTransactionAbortReasonEntity() = TransactionAbortReasonEntity(
    abortReason = toAbortReasonDbDto()
)

fun TransactionAbortReasonEntity.toAbortReason() = AbortReason(
    violationByFields = abortReason.violationByFields?.toViolationByFields(),
    generalViolation = abortReason.generalViolation?.toViolation()
)



