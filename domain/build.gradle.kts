plugins {
    id("com.android.library")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Versions.Sdk.Compile

    defaultConfig {
        minSdk = Versions.Sdk.Min
    }
    namespace = "com.github.ryjen.jokeapp.domain"
}

dependencies {
    implementation(project(":meta"))
    implementation(Dependencies.Koin.Android)
    implementation(Dependencies.Koin.Compat)

    implementation(Dependencies.CoroutinesX.Android)
    implementation(Dependencies.CoroutinesX.Core)
    implementation(Dependencies.Timber)
}
