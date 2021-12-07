pluginManagement {
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
