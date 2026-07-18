package ru.bz.baltic_shipyard_inventory_service.common.errors

class ResourceNotFoundException(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message ?: "Resource not found", cause) {

    val errorCode: String = ErrorCode.RESOURCE_NOT_FOUND.code
}
