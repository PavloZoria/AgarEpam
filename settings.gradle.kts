import java.io.File
import java.io.FileInputStream
import java.util.*

pluginManagement {

//    val prop = java.util.Properties().apply {
//        load(java.io.FileInputStream(File(rootProject.rootDir, "github.properties")))
//    }
//    val gitHubUser: String by prop
//    val gitHubKey: String by prop
//    val gitHubUri:String by prop

    plugins {
        kotlin("plugin.serialization") version "1.3.1"
    }

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://kotlin.bintray.com/ktor")

//        maven {
//            println("gitHubUri: $gitHubUri, gitHubUser: $gitHubUser, gitHubKey: $gitHubKey")
//            name = "GitHubPackages"
//            url = uri("https://maven.pkg.github.com/AgarEpam")
//
//            credentials {
//                username = gitHubUser
//                password = gitHubKey
//            }
//        }
    }
}

rootProject.name = "agar-android-playground"

include("app")
