package ru.bz.baltic_shipyard_inventory_service.presentation.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import ru.bz.baltic_shipyard_inventory_service.presentation.validation.annotation.NotString

class NotStringValidator: ConstraintValidator<NotString, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        TODO("Not yet implemented")
    }
}