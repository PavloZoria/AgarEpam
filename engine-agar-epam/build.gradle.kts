import java.io.File
import java.io.FileInputStream
import java.util.*

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("maven-publish")
}

 apply {
      from("$rootDir/publishing.gradle")
 }

val versionProp = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "project.properties")))
}
val engineVersion: String by versionProp
group = "ua.com.epam.agar.engine"
version = engineVersion

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

val kotlinxCoroutinesVersion: String by rootProject

android {
    compileSdkVersion(31)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(31)
    }
}

kotlin {
    android {
        publishLibraryVariants("release", "debug")
    }
     ios {
         binaries {
             framework {
                 baseName = "Shared"
             }
         }
     }

    sourceSets {
        val commonMain by getting {
            dependencies {
//                 api(project(":core"))
//                 implementation(project(":data"))
                api("ua.com.epam.agar.core:core:0.0.3") {
                    isTransitive = true
                }
                implementation("ua.com.epam.agar.data:data:0.0.3") {
                    isTransitive = true
                }

                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
            }
        }
    }
}