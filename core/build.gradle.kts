plugins {
    kotlin("multiplatform")
}

val kotlinVersion: String by rootProject
val kotlinxCoroutinesVersion: String by rootProject

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