import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("jacoco")
	id("org.springframework.boot") version "2.4.5"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.sonarqube") version "3.0"
	id("org.jmailen.kotlinter") version "3.3.0"
	kotlin("jvm") version "1.4.32"
	kotlin("plugin.spring") version "1.4.32"
	kotlin("kapt") version "1.4.20"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	jcenter()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("com.h2database:h2")
	implementation("org.hibernate:hibernate-envers")
	implementation("com.github.slugify:slugify:2.4")
	runtimeOnly("org.postgresql:postgresql:42.2.18")
	implementation("io.springfox:springfox-boot-starter:3.0.0")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("io.github.teastman:spring-data-hibernate-event:1.0.1")
	implementation("com.sipios:spring-search:0.2.3")
	api("org.mapstruct:mapstruct:1.4.1.Final")
	compileOnly("io.springfox:springfox-swagger-ui:3.0.0")
	kapt("org.mapstruct:mapstruct-processor:1.4.1.Final")
	kapt("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("com.github.javafaker:javafaker:0.14") {
		exclude(module = "org.yaml")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.jacocoTestReport{
	reports {
		xml.isEnabled = true
	}
}
sonarqube{
	properties {
		property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
	}
}