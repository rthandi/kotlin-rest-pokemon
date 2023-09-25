import java.util.*

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

val postgres_version: String by project
val ktorm_version: String by project
val h2_version: String by project

val liquibase_core: String by project

plugins {
  kotlin("jvm") version "1.9.10"
  id("io.ktor.plugin") version "2.3.4"
  id("org.jetbrains.kotlin.plugin.serialization") version "1.9.10"
  id("org.liquibase.gradle") version "2.2.0"
}

group = "pokemon.rthandi"
version = "0.0.1"

application {
  mainClass.set("pokemon.rthandi.ApplicationKt")

//  val isDevelopment: Boolean = project.ext.has("development")
  applicationDefaultJvmArgs = listOf("-Dio.ktor.development=true")
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("io.ktor:ktor-server-core-jvm")
  implementation("io.ktor:ktor-server-content-negotiation-jvm")
  implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
  implementation("org.postgresql:postgresql:$postgres_version")
  implementation("com.h2database:h2:$h2_version")
  implementation("io.ktor:ktor-server-netty-jvm")
  implementation("ch.qos.logback:logback-classic:$logback_version")
  implementation("org.ktorm:ktorm-core:$ktorm_version")
  implementation("org.ktorm:ktorm-support-postgresql:$ktorm_version")
  implementation("org.postgresql:postgresql:$postgres_version")
  liquibaseRuntime("org.liquibase:liquibase-core:$liquibase_core")
  liquibaseRuntime("org.postgresql:postgresql:$postgres_version")
  liquibaseRuntime("ch.qos.logback:logback-core:1.2.9")
  liquibaseRuntime("ch.qos.logback:logback-classic:1.2.9")
  liquibaseRuntime("javax.xml.bind:jaxb-api:2.2.4")
  liquibaseRuntime("info.picocli:picocli:4.7.3")
  testImplementation("io.ktor:ktor-server-tests-jvm:2.2.4")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

val propertiesFile = file("local.properties")
val properties = Properties()
if (propertiesFile.exists()) {
  properties.load(propertiesFile.inputStream())
}

val urlDev = properties.getProperty("liquibase.dev.url") ?: System.getenv("LIQUIBASE_DEV_URL")
val userDev = properties.getProperty("liquibase.dev.user") ?: System.getenv("LIQUIBASE_DEV_USER")
val pwdDev = properties.getProperty("liquibase.dev.pwd") ?: System.getenv("LIQUIBASE_DEV_PWD")

liquibase {
  activities.register("postgres") {
    this.arguments = mapOf(
      "url" to urlDev,
      "username" to userDev,
      "password" to pwdDev,
      "changelogFile" to "src/main/resources/db/changelog/db.changelog-master.xml",
      "logLevel" to "info"
    )
  }
}

