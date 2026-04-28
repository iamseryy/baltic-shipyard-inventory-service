package ru.bz.baltic_shipyard_inventory_service.presentation.validation.annotation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import ru.bz.baltic_shipyard_inventory_service.presentation.validation.NotEmptyValidator
import kotlin.reflect.KClass


@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [NotEmptyValidator::class])
@MustBeDocumented
annotation class NotEmpty(
    val message: String = "code={error.1001.code};description={error.1001.description}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)