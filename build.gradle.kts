import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20"
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
}

dependencies {
    testImplementation(kotlin("test"))
    compileOnly("dev.waterdog.waterdogpe:waterdog:1.1.7")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
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
