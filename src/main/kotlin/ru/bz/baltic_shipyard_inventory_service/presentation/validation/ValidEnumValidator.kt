package ru.bz.baltic_shipyard_inventory_service.presentation.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import ru.bz.baltic_shipyard_inventory_service.presentation.validation.annotation.ValidEnum


class ValidEnumValidator : ConstraintValidator<ValidEnum, Enum<*>?> {

    override fun isValid(value: Enum<*>?, context: ConstraintValidatorContext): Boolean {
        if (value == null) return true
        val enumConstants = value::class.java.enumConstants

        return enumConstants.contains(value)
    }
}