import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.1.7.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	kotlin("plugin.jpa") version "1.3.41"
	kotlin("jvm") version "1.3.41"
	kotlin("plugin.spring") version "1.3.41"
	antlr
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

repositories {
	mavenCentral()
}

tasks.withType<KotlinCompile> {
	kotlinOptions.suppressWarnings = true
	kotlinOptions.jvmTarget = "1.8"
	dependsOn(tasks.generateGrammarSource)
}

tasks.generateGrammarSource.map {
	it.outputDirectory = File("src/main/java/com/exmaple/query")
}

dependencies {
	compileOnly("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	antlr("org.antlr:antlr4:4.7.1")
	compile("org.antlr:antlr4-runtime:4.7.1")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}
