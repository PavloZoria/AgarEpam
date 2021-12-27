import java.io.File
import java.io.FileInputStream
import java.util.*

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("maven-publish")
    id("io.kotest.multiplatform") version "5.0.2"
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
val kotlinVersion: String by rootProject

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
    mavenLocal()

//    maven {
//        println("gitHubUri: $gitHubUri, gitHubUser: $gitHubUser, gitHubKey: $gitHubKey")
//        name = "GitHubPackages"
//        url = uri("https://maven.pkg.github.com/AgarEpam")
//
//        credentials {
//            username = gitHubUser
//            password = gitHubKey
//        }
//    }
}

kotlin {
    jvm()
    ios()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api("ua.com.epam.agar.core:core:0.0.3") {
                    isTransitive = true
                }

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:$kotlinxSerializationVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")

                implementation("io.ktor:ktor-client-core:$ktorVersion")

                api("org.kodein.di:kodein-di:7.7.0") {
                    isTransitive = true
                }
            }
        }
        val commonTest by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test-common:$kotlinVersion")
                implementation("org.jetbrains.kotlin:kotlin-test-annotations-common:$kotlinVersion")
//                implementation("org.junit.jupiter:junit-jupiter:5.7.0")
                implementation("io.mockk:mockk-common:1.8.13.kotlin13")

                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))

                implementation("io.kotest:kotest-assertions-core:5.0.2")
                implementation("io.kotest:kotest-framework-engine:5.0.2")
                implementation("io.kotest:kotest-framework-datatest:5.0.2")
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
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("io.kotest:kotest-runner-junit5-jvm:5.0.2")
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

        val iosTest by getting
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
