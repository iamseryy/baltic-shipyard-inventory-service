package ru.bz.baltic_shipyard_inventory_service.common.errors


class ExternalServiceTimeoutException(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message ?: "External service timeout", cause) {

    val errorCode: String = ErrorCode.SERVICE_TIMEOUT.code
}