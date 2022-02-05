// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath (Dependencies.Gradle.Android)
        classpath (Dependencies.Gradle.Kotlin)
        classpath (Dependencies.Gradle.Ktor)
    }
}
