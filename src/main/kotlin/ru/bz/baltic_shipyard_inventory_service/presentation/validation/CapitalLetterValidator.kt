package ru.bz.baltic_shipyard_inventory_service.presentation.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import ru.bz.baltic_shipyard_inventory_service.presentation.validation.annotation.CapitalLetter


class CapitalLetterValidator: ConstraintValidator<CapitalLetter, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (!value.isNullOrEmpty()) {
            return Character.isUpperCase(value[0]);
        }
        return true;
    }
}