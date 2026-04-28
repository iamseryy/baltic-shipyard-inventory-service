import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.kotlin.dsl.jar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.5"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
	kotlin("plugin.jpa") version "2.0.0"
	id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "ru.bz"
version = "0.0.1"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.MappedSuperclass")
	annotation("javax.persistence.Embeddable")
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
	implementation("org.springframework.boot:spring-boot-starter-security:3.4.2")
	implementation("io.jsonwebtoken:jjwt-api:0.12.6")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.18.1")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.postgresql:postgresql:42.7.5")
	implementation("org.liquibase:liquibase-core")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("jakarta.validation:jakarta.validation-api")
	implementation("org.springframework.boot:spring-boot-starter-amqp")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.testcontainers:postgresql:1.20.4")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}


//tasks.jar {
//	archiveBaseName.set("baltic-shipyard-inventory-service")
//	archiveVersion.set("")
//	archiveClassifier.set("")
//}

//tasks.withType<Jar> {
//	description = "disable plain JAR"
//	group = JavaBasePlugin.DOCUMENTATION_GROUP
//	enabled = false //disable plain JAR
//}
//
//tasks.register<Jar>("fatJar") {
//	description = "Register a fat Jar task to build all required classes and dependencies into a single JAR file"
//	group = JavaBasePlugin.DOCUMENTATION_GROUP
//	archiveBaseName.set("baltic-shipyard-inventory-service") // application name
//	archiveClassifier.set("") // without suffix
//	from(sourceSets.main.get().output) // from all project classes
//	dependsOn(configurations.runtimeClasspath) // dependencies
//	from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }) // include dependencies
//}
//
//tasks.named<ShadowJar>("shadowJar") {
//	description = "Setting up shadowJar to create fat JARs"
//	group = JavaBasePlugin.DOCUMENTATION_GROUP
//	archiveBaseName.set("baltic-shipyard-inventory-service") // application name
//	archiveClassifier.set("") // without suffix
//}
//
//tasks.register<Copy>("copyJar") {
//	description = "Copy jar to deployment directory"
//	group = JavaBasePlugin.DOCUMENTATION_GROUP
//	from(tasks.jar)
//	into("deployment/baltic-shipyard-inventory-service-deployment/baltic-shipyard-inventory-service")
//}
//
//tasks.jar {
//	finalizedBy(tasks.named("copyJar"))
//}
//
//
//tasks.withType<ShadowJar> {
//	archiveClassifier.set("")
//	manifest {
//		attributes(
//			"Main-Class" to "ru.bz.baltic_shipyard_inventory_service.BalticShipyardInventoryServiceApplicationKt"
//		)
//	}
//}
//
//



//tasks.register<Copy>("copyJar") {
//	from(tasks.named("shadowJar").map { it.outputs.files })
//	into("deployment/baltic-shipyard-inventory-service-deployment/baltic-shipyard-inventory-service")
//}
//
//tasks.named("shadowJar") {
//	finalizedBy("copyJar")
//}



//tasks.withType<Test> {
//	useJUnitPlatform()
//}

//tasks.bootBuildImage {
//	builder.set("paketobuildpacks/builder-jammy-base:latest")
//}

//tasks.jar.configure {
//	manifest {
//		attributes(mapOf("Main-Class" to "ru.bz.baltic_shipyard_inventory_service.BalticShipyardInventoryServiceApplicationKt"))
//	}
//	configurations["compileClasspath"].forEach { file: File ->
//		from(zipTree(file.absoluteFile))
//	}
//	duplicatesStrategy = DuplicatesStrategy.INCLUDE
//}
