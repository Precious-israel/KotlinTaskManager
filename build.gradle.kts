plugins {
    kotlin("jvm") version "1.9.24"
    application
}

group = "com.edsarah"
version = "1.0.0"

repositories { mavenCentral() }

dependencies {
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("com.edsarah.taskmanager.MainKt")
}

kotlin { jvmToolchain(17) }

tasks.test { useJUnitPlatform() }

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}