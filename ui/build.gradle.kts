plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Versions.Sdk.Compile

    defaultConfig {
        minSdk = Versions.Sdk.Min
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.ComposeX.Compiler
    }
    namespace = "com.github.ryjen.jokeapp.ui"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":vendor"))
    implementation(Dependencies.AppCompat)
    implementation(Dependencies.ComposeX.Tooling)
    implementation(Dependencies.ComposeX.Foundation)
    implementation(Dependencies.ComposeX.Material)

    implementation(Dependencies.NavigationX.Compose)
    implementation(Dependencies.Koin.Android)
    implementation(Dependencies.Koin.Compat)
    implementation(Dependencies.Koin.Compose)

    implementation(Dependencies.Kredux)

    implementation(Dependencies.Accompanist.Placeholder)
    implementation(Dependencies.Accompanist.Insets)
    implementation(Dependencies.Accompanist.SystemUI)

    implementation(Dependencies.Icons)
}
