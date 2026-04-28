package ru.bz.baltic_shipyard_inventory_service.infrastructure.configuration

import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment


@Configuration
class RabbitConfig(
    private val environment: Environment
) {
    @Autowired
    fun setupRabbitTemplate(template: RabbitTemplate) {
        template.messageConverter = Jackson2JsonMessageConverter()
    }

    @Bean
    fun rabbitListenerContainerFactory(connectionFactory: ConnectionFactory?): SimpleRabbitListenerContainerFactory {
        return SimpleRabbitListenerContainerFactory().apply {
            setMessageConverter(Jackson2JsonMessageConverter())
            setConnectionFactory(connectionFactory)
        }
    }

    @Bean
    fun measuredRemaindersExchange() = DirectExchange(environment.getProperty("application.rabbitmq.exchange.measured-remainders_direct"), true, false)

    @Bean
    fun transferExchange() = DirectExchange(environment.getProperty("application.rabbitmq.exchange.warehouse-transfer_direct"), true, false)

    @Bean
    fun inventoryExchange() = DirectExchange(environment.getProperty("application.rabbitmq.exchange.inventory_direct"), true, false)

    @Bean
    fun itemExchange() = DirectExchange(environment.getProperty("application.rabbitmq.exchange.item_direct"), true, false)

    @Bean
    fun lotExchange() =
        DirectExchange(environment.getProperty("application.rabbitmq.exchange.lot_direct"), true, false)

    @Bean
    fun warehouseExchange() = DirectExchange(environment.getProperty("application.rabbitmq.exchange.warehouse_direct"), true, false)

    @Bean
    fun containerExchange() = DirectExchange(environment.getProperty("application.rabbitmq.exchange.container_direct"), true, false)

    @Bean
    fun barCodeExchange() = DirectExchange(environment.getProperty("application.rabbitmq.exchange.barcode_direct"), true, false)

    @Bean
    fun mechanicalpartExchange() = DirectExchange(environment.getProperty("application.rabbitmq.exchange.mechanicalpart_direct"), true, false)




    @Bean
    fun findMeasuredRemaindersQueue() = Queue(environment.getProperty("application.rabbitmq.queue.find_measured-remainders"), true)

    @Bean
    fun findMeasuredRemaindersBinding() =
        BindingBuilder.bind(findMeasuredRemaindersQueue())
            .to(measuredRemaindersExchange())
            .with(environment.getProperty("application.rabbitmq.key.find_measured-remainders"))

    @Bean
    fun updateMeasuredRemainderQueue() = Queue(environment.getProperty("application.rabbitmq.queue.update_measured-remainder"), true)

    @Bean
    fun responseUpdateMeasuredRemainderQueue() = Queue(environment.getProperty("application.rabbitmq.queue.response_to_update_measured-remainder"), true)

    @Bean
    fun updateMeasuredRemainderBinding() =
        BindingBuilder.bind(updateMeasuredRemainderQueue())
            .to(measuredRemaindersExchange())
            .with(environment.getProperty("application.rabbitmq.key.update_measured-remainder"))

    @Bean
    fun inventoryMeasuredRemaindersQueue() = Queue(environment.getProperty("application.rabbitmq.queue.inventory_measured-remainders"), true)

    @Bean
    fun responseInventoryMeasuredRemaindersQueue() = Queue(environment.getProperty("application.rabbitmq.queue.response_to_inventory_measured-remainders"), true)

    @Bean
    fun inventoryMeasuredRemaindersBinding() =
        BindingBuilder.bind(inventoryMeasuredRemaindersQueue())
            .to(measuredRemaindersExchange())
            .with(environment.getProperty("application.rabbitmq.key.inventory_measured-remainders"))



    @Bean
    fun transferByLocationsQueue() = Queue(environment.getProperty("application.rabbitmq.queue.transfer_by_bins"), true)

    @Bean
    fun transferByLocationsBinding() =
        BindingBuilder.bind(transferByLocationsQueue())
            .to(transferExchange())
            .with(environment.getProperty("application.rabbitmq.key.transfer_by_bins"))


    @Bean
    fun responseTransferByLocationsQueue() = Queue(environment.getProperty("application.rabbitmq.queue.response_to_transfer_by_bins"), true)

    @Bean
    fun responseTransferByLocationsBinding() =
        BindingBuilder.bind(responseTransferByLocationsQueue())
            .to(transferExchange())
            .with(environment.getProperty("application.rabbitmq.key.response_to_transfer_by_bins"))



    @Bean
    fun findStockListQueue() = Queue(environment.getProperty("application.rabbitmq.queue.find_stock_list"), true)

    @Bean
    fun findStockListBinding() =
        BindingBuilder.bind(findStockListQueue())
            .to(inventoryExchange())
            .with(environment.getProperty("application.rabbitmq.key.find_stock_list"))

    @Bean
    fun getItemDetailQueue() = Queue(environment.getProperty("application.rabbitmq.queue.get_item_detail"), true)

    @Bean
    fun getItemDetailBinding() =
        BindingBuilder.bind(getItemDetailQueue())
            .to(itemExchange())
            .with(environment.getProperty("application.rabbitmq.key.get_item_detail"))

    @Bean
    fun getLotDetailQueue() = Queue(environment.getProperty("application.rabbitmq.queue.get_lot_detail"), true)

    @Bean
    fun getLotDetailBinding() =
        BindingBuilder.bind(getLotDetailQueue())
            .to(lotExchange())
            .with(environment.getProperty("application.rabbitmq.key.get_lot_detail"))

    @Bean
    fun getWarehouseDetailQueue() = Queue(environment.getProperty("application.rabbitmq.queue.get_warehouse_detail"), true)

    @Bean
    fun getWarehouseDetailBinding() =
        BindingBuilder.bind(getWarehouseDetailQueue())
            .to(warehouseExchange())
            .with(environment.getProperty("application.rabbitmq.key.get_warehouse_detail"))

    @Bean
    fun getLocationDetailQueue() = Queue(environment.getProperty("application.rabbitmq.queue.get_location_detail"), true)

    @Bean
    fun getLocationDetailBinding() =
        BindingBuilder.bind(getLocationDetailQueue())
            .to(warehouseExchange())
            .with(environment.getProperty("application.rabbitmq.key.get_location_detail"))

    @Bean
    fun getContainerQueue() = Queue(environment.getProperty("application.rabbitmq.queue.get_container"), true)

    @Bean
    fun getContainerBinding() =
        BindingBuilder.bind(getContainerQueue())
            .to(containerExchange())
            .with(environment.getProperty("application.rabbitmq.key.get_container"))

    @Bean
    fun findContainersQueue() = Queue(environment.getProperty("application.rabbitmq.queue.find_containers"), true)

    @Bean
    fun findContainersBinding() =
        BindingBuilder.bind(findContainersQueue())
            .to(containerExchange())
            .with(environment.getProperty("application.rabbitmq.key.find_containers"))


    @Bean
    fun findBarCodeDataQueue() = Queue(environment.getProperty("application.rabbitmq.queue.find_barcode_data"), true)

    @Bean
    fun findBarCodeDataBinding() =
        BindingBuilder.bind(findBarCodeDataQueue())
            .to(barCodeExchange())
            .with(environment.getProperty("application.rabbitmq.key.find_barcode_data"))

    @Bean
    fun placeContainerQueue() = Queue(environment.getProperty("application.rabbitmq.queue.placement_container"), true)

    @Bean
    fun placeContaineBinding() =
        BindingBuilder.bind(placeContainerQueue())
            .to(containerExchange())
            .with(environment.getProperty("application.rabbitmq.key.placement_container"))

    @Bean
    fun responsePlaceContainerQueue() = Queue(environment.getProperty("application.rabbitmq.queue.response_to_placement_container"), true)

    @Bean
    fun responsePlaceContaineBinding() =
        BindingBuilder.bind(responsePlaceContainerQueue())
            .to(containerExchange())
            .with(environment.getProperty("application.rabbitmq.key.response_to_placement_container"))

    @Bean
    fun reportOperationQueue() = Queue(environment.getProperty("application.rabbitmq.queue.report_operation"), true)

    @Bean
    fun reportOperationBinding() =
        BindingBuilder.bind(reportOperationQueue())
            .to(mechanicalpartExchange())
            .with(environment.getProperty("application.rabbitmq.key.report_operation"))
}