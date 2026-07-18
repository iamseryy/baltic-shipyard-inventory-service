package ru.bz.baltic_shipyard_inventory_service.presentation.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import ru.bz.baltic_shipyard_inventory_service.presentation.validation.annotation.ValidEnumWithAllowed


class ValidEnumWithAllowedValidator : ConstraintValidator<ValidEnumWithAllowed, Enum<*>?> {

    private lateinit var allowedValues: Set<String>

    override fun initialize(constraint: ValidEnumWithAllowed) {
        allowedValues = constraint.allowedValues.toSet()
    }

    override fun isValid(value: Enum<*>?, context: ConstraintValidatorContext): Boolean {
        if (value == null) return true

        val valueName = value.name
        return allowedValues.contains(valueName)
    }
}