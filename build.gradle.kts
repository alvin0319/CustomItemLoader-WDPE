import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.20"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "dev.minjae.customitemloader.wdpe"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        name = "waterdog-repo"
        url = uri("https://repo.waterdog.dev/artifactory/main")
    }
    maven {
        name = "opencollab-releases"
        url = uri("https://repo.opencollab.dev/maven-releases/")
    }
    maven {
        name = "opencollab-snapshots"
        url = uri("https://repo.opencollab.dev/maven-snapshots/")
    }
}

dependencies {
    testImplementation(kotlin("test"))
    compileOnly("dev.waterdog.waterdogpe:waterdog:2.0.0-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}

// shadowJar {
//    archiveClassifier.set("")
//    manifest {
//        attributes(mapOf("Main-Class" to "dev.minjae.musicbot.BootstrapKt"))
//    }
// }

tasks.named<ShadowJar>("shadowJar") {
    archiveClassifier.set("")
}
