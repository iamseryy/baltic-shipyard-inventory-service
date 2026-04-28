package ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.transaction

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.annotation.CreatedDate
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.Transaction
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.TransactionBase
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.TransactionStatus
import ru.bz.baltic_shipyard_inventory_service.infrastructure.persistence.postgres.entity.EntityFields
import java.time.LocalDateTime


@Entity
@Table(schema = "inventory_service", name = "transactions")
data class TransactionEntity(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Int = 0,

    @Enumerated(EnumType.STRING)
    @field:Column(name = EntityFields.STATUS, nullable = false)
    override val status: TransactionStatus = TransactionStatus.RUNNING,


    @field:Column(name = EntityFields.USER_LOGIN, nullable = false)
    override val userLogin: String,
    ): TransactionBase
{
    @CreatedDate
    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    override lateinit var created: LocalDateTime

    constructor(id: Int, status: TransactionStatus, userLogin: String, created: LocalDateTime): this(id, status, userLogin) {
        this.created = created
    }
}

fun Transaction.toTransactionEntity() = TransactionEntity(
    id = id,
    status = status,
    userLogin = userLogin,
    created = created
)

fun TransactionEntity.toTransaction() = Transaction(
    id = id,
    status = status,
    userLogin = userLogin,
    created = created
)