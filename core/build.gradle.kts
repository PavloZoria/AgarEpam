import java.io.File
import java.io.FileInputStream
import java.util.*

plugins {
    kotlin("multiplatform")
    id("maven-publish")
}

apply {
    from("$rootDir/publishing.gradle")
}

val kotlinVersion: String by rootProject
val kotlinxCoroutinesVersion: String by rootProject

val versionProp = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "project.properties")))
}
val coreVersion: String by versionProp

group = "ua.com.epam.agar.core"
version = coreVersion

kotlin {
    jvm()
    ios()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api("org.jetbrains.kotlin:kotlin-stdlib")
                api("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
            }
        }
    }
}