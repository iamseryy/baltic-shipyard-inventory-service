package ru.bz.baltic_shipyard_inventory_service.presentation.validation.annotation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import ru.bz.baltic_shipyard_inventory_service.presentation.validation.EnumTypeSubsetValidator
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.TransactionStatus
import kotlin.reflect.KClass


private const val TEST = "{anyOf}"

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [EnumTypeSubsetValidator::class])
@MustBeDocumented
annotation class EnumTypeSubset(
    val anyOf: Array<TransactionStatus>,
    val message: String = "must be any of $TEST",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
