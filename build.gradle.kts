plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.kotlin.jpa)
    alias(libs.plugins.shadow)
    alias(libs.plugins.git.properties)
}

group = "ru.bz"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}


// Config JPA For KOTLIN
allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

dependencies {
    // SPRING BOOT STARTERS
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.security)
    implementation(libs.spring.boot.starter.amqp)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.spring.boot.starter.actuator)

    // KOTLIN & JACKSON
    runtimeOnly(libs.kotlin.reflect)
    implementation(libs.jackson.module.kotlin)
    implementation(libs.jackson.datatype.jsr310)

    // DATABASE
    implementation(libs.postgresql)
    implementation(libs.spring.boot.starter.liquibase)

    // JWT
    implementation(libs.bundles.jjwt)

    // API DOCS
    implementation(libs.springdoc.openapi)

    // VALIDATION
    implementation(libs.jakarta.validation.api)

    // LOG
    implementation(libs.kotlin.logging)
    implementation(libs.logstash.encoder)

    // TEST
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.kotlin.test.junit5)
    testImplementation(libs.spring.rabbit.test)
    testImplementation(libs.bundles.testcontainers)
    testRuntimeOnly(libs.junit.platform.launcher)
}

kotlin {
    jvmToolchain(24)

    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    failOnNoDiscoveredTests.set(false)
}

springBoot {
    buildInfo()
}

gitProperties {
    keys = listOf("git.branch", "git.commit.id.abbrev", "git.commit.time")
    // ISO-8601
    dateFormat = "yyyy-MM-dd'T'HH:mm:ssXXX"
    dateFormatTimeZone = "UTC"
}