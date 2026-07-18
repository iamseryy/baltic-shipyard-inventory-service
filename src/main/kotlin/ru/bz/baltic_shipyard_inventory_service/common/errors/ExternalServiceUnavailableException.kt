package ru.bz.baltic_shipyard_inventory_service.common.errors


class ExternalServiceUnavailableException(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message ?: "External service unavailable", cause) {

    val errorCode: String = ErrorCode.SERVICE_UNAVAILABLE.code
}
