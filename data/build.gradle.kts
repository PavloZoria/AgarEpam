plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    // id("kotlinx-serialization")
}

val kotlinxCoroutinesVersion: String by rootProject
val kotlinxSerializationVersion: String by rootProject

kotlin {
    jvm()
    ios()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":core"))

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:$kotlinxSerializationVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")

                implementation("dev.icerock.moko:socket-io:0.3.0")

                implementation("io.rsocket.kotlin:rsocket-core:0.13.1")
                // TCP ktor transport
                implementation("io.rsocket.kotlin:rsocket-transport-ktor:0.13.1")
                // WS ktor transport client plugin
                implementation("io.rsocket.kotlin:rsocket-transport-ktor-client:0.13.1")

                implementation("io.ktor:ktor-client-core:1.6.6")
                // implementation("io.ktor:ktor-client-core:1.6.6")
//                // WS ktor transport server plugin
//                implementation("io.rsocket.kotlin:rsocket-transport-ktor-server:0.13.1")
//                implementation("io.ktor:ktor-client-serialization:${Deps.KTOR_VERSION}")
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("com.squareup.okhttp3:okhttp:4.9.0")
                implementation("io.ktor:ktor-client-cio:1.6.6") //jvm
                // implementation("io.ktor:ktor-client-okhttp:1.6.4") //jvm

            }
        }
        val jvmTest by getting
    }
}