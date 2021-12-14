plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    // id("kotlinx-serialization")
}

val kotlinxCoroutinesVersion: String by rootProject
val kotlinxSerializationVersion: String by rootProject
val ktorVersion: String by rootProject

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
                // implementation("io.ktor:ktor-client-core-ios:$ktorVersion")
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
