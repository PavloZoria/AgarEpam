pluginManagement {

    plugins {
        kotlin("plugin.serialization") version "1.3.1"
    }

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "agar-library"

include("core")
include("data")
include("playground")
include("agar-epam")
