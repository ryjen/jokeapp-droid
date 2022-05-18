plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization")
    kotlin("kapt")
}

android {
    compileSdk = Versions.Sdk.Compile

    defaultConfig {
        minSdk = Versions.Sdk.Min
    }
    namespace = "com.github.ryjen.jokeapp.data"
}

dependencies {

    testImplementation("junit:junit:4.12")
    kapt(Dependencies.RoomX.Compiler)

    implementation(project(":domain"))

    implementation(Dependencies.Koin.Android)

    implementation(Dependencies.Ktor.Android)
    implementation(Dependencies.Ktor.Client)
    implementation(Dependencies.Ktor.Content)
    implementation(Dependencies.Ktor.Json)
    implementation(Dependencies.Ktor.Logging)
    implementation(Dependencies.Ktor.Serialize)
    implementation(Dependencies.Ktor.Logback)
    implementation(Dependencies.Serialization.Json)

    implementation(Dependencies.RoomX.Runtime)
    implementation(Dependencies.RoomX.Kotlin)
    implementation(Dependencies.Timber)

    testImplementation(Dependencies.JUnit)
    testImplementation(Dependencies.Kotlin.Test)
}
