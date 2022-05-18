plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Versions.Sdk.Compile

    defaultConfig {
        minSdk = Versions.Sdk.Min
    }
    namespace = "com.github.ryjen.jokeapp.meta"
}

dependencies {
    implementation(Dependencies.Koin.Android)
    implementation(Dependencies.CoroutinesX.Android)
    implementation(Dependencies.CoroutinesX.Core)
}
