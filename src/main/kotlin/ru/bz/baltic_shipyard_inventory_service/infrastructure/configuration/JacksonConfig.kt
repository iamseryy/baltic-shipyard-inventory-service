package ru.bz.baltic_shipyard_inventory_service.infrastructure.configuration

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class JacksonConfig {
//    @Bean
//    fun objectMapper(): ObjectMapper? {
//        return JsonMapper.builder()
//            .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, true)
//            .addModule(JavaTimeModule())
//            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
//            .serializationInclusion(JsonInclude.Include.NON_NULL)
//            .build()
//    }
}