package ru.bz.baltic_shipyard_inventory_service.presentation.validation.annotation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import ru.bz.baltic_shipyard_inventory_service.presentation.validation.NotStringValidator
import kotlin.reflect.KClass


@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [NotStringValidator::class])
@MustBeDocumented
annotation class NotString(
    val message: String = "",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
