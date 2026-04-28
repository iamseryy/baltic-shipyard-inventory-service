package ru.bz.baltic_shipyard_inventory_service.presentation.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import ru.bz.baltic_shipyard_inventory_service.presentation.validation.annotation.NotEmpty

class NotEmptyValidator: ConstraintValidator<NotEmpty, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?) = !(value != null && value.isEmpty())
}