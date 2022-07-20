buildscript {

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    dependencies {
        classpath(Dependencies.Gradle.Android)
        classpath(Dependencies.Gradle.Kotlin)
        classpath(Dependencies.Gradle.Serialization)
        classpath(Dependencies.Gradle.SqlDelight)
    }
}
