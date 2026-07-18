package ru.bz.baltic_shipyard_inventory_service.common.errors.abortreason


data class Violation(
    val code: String,
    val params: List<Any?> = emptyList(),
    val externalDescription: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Violation) return false
        return code == other.code &&
                params == other.params &&
                externalDescription == other.externalDescription
    }

    override fun hashCode(): Int {
        var result = code.hashCode()
        result = 31 * result + params.hashCode()
        result = 31 * result + (externalDescription?.hashCode() ?: 0)
        return result
    }
}