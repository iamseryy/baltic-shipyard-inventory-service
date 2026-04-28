package ru.bz.baltic_shipyard_inventory_service.infrastructure.configuration

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springframework.context.annotation.Configuration


@OpenAPIDefinition(
    info = Info(
        title = "springdoc.openapidefinition.title",
        description = "springdoc.openapidefinition.desc", version = "springdoc.openapidefinition.version",
        contact = Contact(
            name = "springdoc.openapidefinition.contact.name",
            email = "springdoc.openapidefinition.contact.email",
            url = "springdoc.openapidefinition.contact.url"
        )
    )
)
@SecurityScheme(
    name = "JWT",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer",
    description = "Input JWT"
)
@Configuration
class OpenApiConfig {
//    @Bean
//    fun swaggerMessageSource(): ReloadableResourceBundleMessageSource? = ReloadableResourceBundleMessageSource().apply {
//        setBasename("classpath:swaggermessages")
//        setCacheSeconds(3600)
//        setDefaultEncoding("UTF-8")
//    }

//    @Bean
//    fun customOpenAPI(): OpenAPI {
//        return OpenAPI()
//            .components(
//                Components()
//                    .addSecuritySchemes(
//                        "bearerAuth",
//                            SecurityScheme()
//                                .type(SecurityScheme.Type.HTTP)
//                                .scheme("bearer")
//                                .bearerFormat("JWT")
//                                .description("Input JWT")
//                    )
//            )
//    }

}