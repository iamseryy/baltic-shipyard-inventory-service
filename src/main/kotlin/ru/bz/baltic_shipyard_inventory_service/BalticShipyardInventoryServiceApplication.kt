package ru.bz.baltic_shipyard_inventory_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import ru.bz.baltic_shipyard_inventory_service.infrastructure.messaging.rabbit.config.RabbitProperties


@SpringBootApplication
@EnableConfigurationProperties(RabbitProperties::class)
class BalticShipyardInventoryServiceApplication
fun main(args: Array<String>) {
    runApplication<BalticShipyardInventoryServiceApplication>(*args)
}