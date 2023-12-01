plugins {
  kotlin("jvm") version "1.9.10"
  kotlin("plugin.allopen") version "1.9.10"
  id("io.quarkus")
}

repositories {
  mavenCentral()
  mavenLocal()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

configurations.all {
  resolutionStrategy {
    force("com.squareup.okhttp3:okhttp:4.10.0")
    force("com.squareup.okio:okio:3.0.0")
  }
}

dependencies {
  implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
  implementation("io.quarkus:quarkus-config-yaml")
  implementation("io.quarkus:quarkus-kotlin")
  implementation("io.quarkus:quarkus-quartz")
  implementation("org.jetbrains.kotlin:kotlin-stdlib")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("io.quarkus:quarkus-rest-client-reactive-jackson")
  implementation("io.quarkus:quarkus-arc")
  implementation("com.github.pengrad:java-telegram-bot-api:6.9.1") {
    exclude(group = "com.squareup.okhttp3", module = "logging-interceptor")
  }
  implementation("io.github.microutils:kotlin-logging:3.0.5")

  testImplementation("io.quarkus:quarkus-junit5")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
  testImplementation("com.marcinziolo:kotlin-wiremock:2.1.1")
  testImplementation("net.javacrumbs.json-unit:json-unit:3.2.2")
  testImplementation("org.awaitility:awaitility-kotlin")
}

group = "ru.an1s9n"
version = "1.0.0"

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<Test> {
  systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}
allOpen {
  annotation("jakarta.ws.rs.Path")
  annotation("jakarta.enterprise.context.ApplicationScoped")
  annotation("io.quarkus.test.junit.QuarkusTest")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
  kotlinOptions.javaParameters = true
}
