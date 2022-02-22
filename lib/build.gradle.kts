plugins {
    id ("com.android.library")
    id ("kotlin-android")
}

android {
    compileSdk = Versions.Sdk.Compile

    kotlinOptions {
        useIR = true
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
