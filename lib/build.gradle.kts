plugins {
    id ("com.android.library")
    id ("kotlin-android")
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
}

dependencies {
    implementation(Dependencies.ComposeX.Graphics)
    implementation(Dependencies.ComposeX.Material)
    implementation(Dependencies.ComposeX.Foundation)
}
