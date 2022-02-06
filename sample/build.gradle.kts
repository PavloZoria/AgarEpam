import java.io.File
import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android")
    id("kotlin-kapt")
}

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
    maven(url = "https://jitpack.io")
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

android {
    compileSdkVersion(32)

    defaultConfig {
        applicationId = "com.epam.agar.hackathon.agar_epam"
        minSdkVersion(24)
        targetSdkVersion(32)
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        viewBinding = true//TODO need to be described in the documentation
        dataBinding = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    implementation("ua.com.epam.agar.android_library:agar-epam:0.1.8")


    //for local testing
//    implementation(project(":core"))
//    implementation(project(":data"))
//    implementation(project(":engine-agar-epam"))
//    implementation(project(":agar-epam"))
}