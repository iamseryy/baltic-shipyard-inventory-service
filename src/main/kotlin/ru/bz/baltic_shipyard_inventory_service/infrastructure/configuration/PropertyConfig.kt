package ru.bz.baltic_shipyard_inventory_service.infrastructure.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.FileSystemResource


@Configuration
class PropertyConfig {
//    @Bean
//    fun properties(): PropertySourcesPlaceholderConfigurer {
//        val pspc = PropertySourcesPlaceholderConfigurer()
//        pspc.setLocations(
////            FileSystemResource("d:config.yml")  // ��������������: ������� �����
//            ClassPathResource("config.yml") // ������� �����
//        )
//        pspc.setIgnoreResourceNotFound(false) // ���������� ����������� �������, ����� �������������� ��������� ��������
//
//        return pspc
//    }
}