buildscript {

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Dependencies.Gradle.Android)
        classpath(Dependencies.Gradle.Kotlin)
        classpath(Dependencies.Gradle.Serialization)
    }
}
