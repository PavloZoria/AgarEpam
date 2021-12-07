plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
}
group = "com.ua.agar.io.hackathon.playground"
version = "unspecified"

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
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
    android()
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
                api(project(":core"))
                api(project(":data"))

                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
            }
        }
    }
}