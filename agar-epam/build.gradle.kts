import java.io.File
import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-android")
    id("kotlin-kapt")
    id("maven-publish")
}

val versionProp = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "project.properties")))
}
val androidLibraryVersion: String by versionProp
group = "ua.com.epam.agar.android_library"
version = androidLibraryVersion

val prop = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "github.properties")))
}
val gitHubUser: String by prop
val gitHubKey: String by prop
val gitHubUri: String by prop

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
//        applicationId = "ua.com.epam.agar.android_library"
        minSdkVersion(24)
        targetSdkVersion(32)
//        versionCode = 1
//        versionName = "1.0"
    }

    buildFeatures {
        viewBinding = true
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
    api("ua.com.epam.agar.engine:engine-agar-epam:0.0.4") {
        isTransitive = true
    }
    api("ua.com.epam.agar.core:core:0.0.4") {
        isTransitive = true
    }
    implementation("ua.com.epam.agar.data:data:0.0.4")
//    api(project(":engine-agar-epam"))

    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("com.google.android.material:material:1.6.0-alpha01")
    implementation("androidx.constraintlayout:constraintlayout:2.1.2")
    implementation("androidx.navigation:navigation-fragment-ktx:2.4.0-rc01")
    implementation("androidx.navigation:navigation-ui-ktx:2.4.0-rc01")
    implementation("android.arch.lifecycle:extensions:1.1.1")
    implementation("android.arch.lifecycle:viewmodel:1.1.1")

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
}

publishing {

    publications {
        create<MavenPublication>("AndroidLibrary") {
            run {
                groupId = group.toString()
                artifactId = "agar-epam"
                version = androidLibraryVersion
                artifact("$buildDir/outputs/aar/agar-epam-release.aar")
                pom.withXml {
                    val dependenciesNode = asNode().appendNode("dependencies")
                    configurations.filter { it.name == "implementation" || it.name == "api" }.forEach { configuration ->
                        configuration.dependencies
                            .filter {
                                it.group != null && it.name != "unspecified" && it.version != null
                            }
                            .forEach {
                                val dependencyNode = dependenciesNode.appendNode("dependency")
                                dependencyNode.appendNode("groupId", it.group)
                                dependencyNode.appendNode("artifactId", it.name)
                                dependencyNode.appendNode("version", it.version)
                            }
                    }
                }
            }
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri(gitHubUri)
            credentials {
                username = gitHubUser
                password = gitHubKey

            }
        }
    }
}