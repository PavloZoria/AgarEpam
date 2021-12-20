buildscript {
    val kotlin_version by extra("1.5.31")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://kotlin.bintray.com/ktor")
        mavenLocal()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlin_version")
        classpath(kotlin("serialization", version = kotlin_version))
    }
}

allprojects {
    val prop = java.util.Properties().apply {
        load(java.io.FileInputStream(File(rootProject.rootDir, "github.properties")))
    }
    val gitHubUser: String by prop
    val gitHubKey: String by prop
    val gitHubUri:String by prop

    repositories {
        google()
        mavenCentral()
        maven {
            println("gitHubUri: $gitHubUri, gitHubUser: $gitHubUser, gitHubKey: $gitHubKey")
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/PavloZoria/AgarEpam")

            credentials {
                username = gitHubUser
                password = gitHubKey
            }
        }
        // maven(url = "https://kotlin.bintray.com/kotlinx")
        mavenLocal()
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }

}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}