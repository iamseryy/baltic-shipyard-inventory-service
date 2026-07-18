package ru.bz.baltic_shipyard_inventory_service.common.errors

import ru.bz.baltic_shipyard_inventory_service.common.errors.abortreason.Violation


enum class ErrorCode(val code: String) {
    SERVICE_TIMEOUT("ERR_SERVICE_TIMEOUT"),
    SERVICE_UNAVAILABLE("ERR_SERVICE_UNAVAILABLE"),
    UPDATE_EMPTY_RESPONSE("ERR_UPDATE_EMPTY_RESPONSE"),
    RESOURCE_NOT_FOUND("ERR_RESOURCE_NOT_FOUND"),
    INSUFFICIENT_STOCK("ERR_INSUFFICIENT_STOCK"),
    VALIDATION_FAILED("ERR_VALIDATION_FAILED"),
    EXTERNAL_VALIDATION("ERR_EXTERNAL_VALIDATION"),
    DEFAULT("ERR_DEFAULT")

    ;

    fun toViolation(): Violation = Violation(code = code)
    fun toViolation(vararg params: Any?): Violation = Violation(code = code, params = params.toList())
    fun toViolationWithExternal(externalDescription: String?, vararg params: Any?): Violation =
        Violation(code = code, params = params.toList(), externalDescription = externalDescription)
}