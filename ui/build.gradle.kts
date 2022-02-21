plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Versions.Sdk.Compile

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.ComposeX.Compiler
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(Dependencies.AppCompat)
    implementation(Dependencies.ComposeX.Tooling)
    implementation(Dependencies.ComposeX.Foundation)
    implementation(Dependencies.ComposeX.Material)

    implementation(Dependencies.NavigationX.Compose)
    implementation(Dependencies.Koin.Android)
    implementation(Dependencies.Koin.Compat)
    implementation(Dependencies.Koin.Compose)

    implementation(Dependencies.Accompanist.Placeholder)
    implementation(Dependencies.Accompanist.Insets)

    implementation(Dependencies.Icons)
}
