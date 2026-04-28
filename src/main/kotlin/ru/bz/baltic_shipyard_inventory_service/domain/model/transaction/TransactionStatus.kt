package ru.bz.baltic_shipyard_inventory_service.domain.model.transaction

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue


enum class TransactionStatus(val description: String) {
    RUNNING("RUNNING"),
    COMPLETED("COMPLETED"),
    FAILED("FAILED"),
    @JsonEnumDefaultValue
    UNKNOWN("UNKNOWN")
}