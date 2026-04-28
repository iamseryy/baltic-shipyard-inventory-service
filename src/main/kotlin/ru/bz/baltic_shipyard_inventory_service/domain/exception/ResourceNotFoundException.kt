package ru.bz.baltic_shipyard_inventory_service.domain.exception

class ResourceNotFoundException:  Exception {
    constructor(): super()
    constructor(message: String?): super(message)
}