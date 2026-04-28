package ru.bz.baltic_shipyard_inventory_service.infrastructure.external.rabbit.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import java.time.LocalDateTime
import java.util.*



@JsonInclude(JsonInclude.Include.ALWAYS)
abstract class RabbitMessage(
    @field:JsonProperty(JsonFieldsProvider.MESSAGE_ID) val messageId: String = UUID.randomUUID().toString(),

    @field:JsonFormat(shape = JsonFormat.Shape.STRING)
    @field:JsonSerialize(using = LocalDateTimeSerializer::class)
    @field:JsonDeserialize(using = LocalDateTimeDeserializer::class)
    @field:JsonProperty(JsonFieldsProvider.DATE) val timestamp: LocalDateTime = LocalDateTime.now(),

    @field:JsonProperty(JsonFieldsProvider.TYPE) val messageType: String
) {
    constructor(messageType: String) : this (
        messageId = UUID.randomUUID().toString(),
        timestamp = LocalDateTime.now(),
        messageType = messageType
    )
}