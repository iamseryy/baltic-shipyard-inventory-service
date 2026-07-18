package ru.bz.baltic_shipyard_inventory_service.presentation.validation.annotation

import jakarta.validation.Constraint
import ru.bz.baltic_shipyard_inventory_service.presentation.validation.ValidEnumWithAllowedValidator
import kotlin.reflect.KClass


@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ValidEnumWithAllowedValidator::class])
annotation class ValidEnumWithAllowed(
    val allowedValues: Array<String> = [],
    val message: String = "Invalid enum value",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<*>> = []
)