package ru.bz.baltic_shipyard_inventory_service.presentation.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import ru.bz.baltic_shipyard_inventory_service.presentation.validation.annotation.EnumTypeSubset
import ru.bz.baltic_shipyard_inventory_service.domain.model.transaction.TransactionStatus

class EnumTypeSubsetValidator: ConstraintValidator<EnumTypeSubset, TransactionStatus> {
    private lateinit var subset: Array<TransactionStatus>

    @Override
    override fun initialize(constraintAnnotation: EnumTypeSubset?) {
        if (constraintAnnotation != null) {
            subset = constraintAnnotation.anyOf
        }
    }

    override fun isValid(value: TransactionStatus?, context: ConstraintValidatorContext?): Boolean {
        return value == null || subset.contains(value)
    }
}