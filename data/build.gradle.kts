import java.io.File
import java.io.FileInputStream
import java.util.*

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("maven-publish")
}

apply {
    from("$rootDir/publishing.gradle")
}

val versionProp = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "project.properties")))
}
val dataVersion: String by versionProp

group = "ua.com.epam.agar.data"
version = dataVersion

val kotlinxCoroutinesVersion: String by rootProject
val kotlinxSerializationVersion: String by rootProject
val ktorVersion: String by rootProject

val prop = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "github.properties")))
}
val gitHubUser: String by prop
val gitHubKey: String by prop
val gitHubUri:String by prop

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
    maven("https://kotlin.bintray.com/ktor")
//    mavenLocal()

    maven {
        println("gitHubUri: $gitHubUri, gitHubUser: $gitHubUser, gitHubKey: $gitHubKey")
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/AgarEpam")

        credentials {
            username = gitHubUser
            password = gitHubKey
        }
    }
}

kotlin {
    jvm()
    ios()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api("ua.com.epam.agar.core:core:0.0.1")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:$kotlinxSerializationVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")

                implementation("io.ktor:ktor-client-core:$ktorVersion")
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-cio:$ktorVersion")
                implementation("io.ktor:ktor-client-core-jvm:$ktorVersion")
                // implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
                // implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$kotlinxCoroutinesVersion")
            }
        }

        val iosMain by getting {
            dependencies {
                // implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:$kotlinxCoroutinesVersion")
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
                // implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:$kotlinxCoroutinesVersion")
                // implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$kotlinxCoroutinesVersion")
            }
        }
        // configure([ios_x86_64Main, ios_arm64Main]) {
        //     dependsOn iosMain
        // }
    }
}

// configurations {
//     compileClasspath
// }
//
// task packForXCode(type: Sync) {
//     final File frameworkDir = new File(buildDir, "xcode-frameworks")
//
//     final String configuration = project.findProperty("CONFIGURATION")?.toUpperCase() ?: "DEBUG"
//     final String arch = project.findProperty("ARCHS") ?: "x86_64"
//
//     dependsOn kotlin.targets."ios_${arch}".compilations.main.linkTaskName("FRAMEWORK", configuration)
//
//     from { kotlin.targets."ios_${arch}".compilations.main.getBinary("FRAMEWORK", configuration).parentFile }
//     into frameworkDir
// }
//
// tasks.build.dependsOn packForXCode
