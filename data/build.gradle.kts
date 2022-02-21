plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    compileSdk = Versions.Sdk.Compile

    defaultConfig {
        minSdk = Versions.Sdk.Min
    }
}

dependencies {

    kapt(Dependencies.RoomX.Compiler)

    implementation(project(":domain"))

    implementation(Dependencies.Koin.Android)

    implementation(Dependencies.Ktor.Android)
    implementation(Dependencies.Ktor.Client)
    implementation(Dependencies.Ktor.Logging)
    implementation(Dependencies.Ktor.Serialize)
    implementation(Dependencies.Ktor.Logback)
    implementation(Dependencies.Serialization.Json)

    implementation(Dependencies.RoomX.Runtime)
    implementation(Dependencies.RoomX.Kotlin)
    implementation(Dependencies.Timber)
}
