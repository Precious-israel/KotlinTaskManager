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
tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.edsarah.taskmanager.MainKt"
    }
    // Bundle Kotlin's standard library into the jar so `java -jar` works
    from(configurations.runtimeClasspath.get().map {
        if (it.isDirectory) it else zipTree(it)
    }) {
        exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA", "META-INF/*.MF")
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}