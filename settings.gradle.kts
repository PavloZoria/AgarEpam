pluginManagement {

    plugins {
        kotlin("plugin.serialization") version "1.3.1"
    }

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://kotlin.bintray.com/ktor")
    }
}

rootProject.name = "agar-library"

include(":sample")
