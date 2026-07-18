package ru.bz.baltic_shipyard_inventory_service.domain.common

import ru.bz.baltic_shipyard_inventory_service.common.errors.abortreason.AbortReason


sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Failure(
        val abortReason: AbortReason,
    ) : Result<Nothing>()
}