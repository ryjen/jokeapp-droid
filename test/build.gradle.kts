plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Versions.Sdk.Compile

    defaultConfig {
        minSdk = Versions.Sdk.Min

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    testOptions {
        animationsDisabled = true
    }
    namespace = "com.github.ryjen.jokeapp.test"
}

dependencies {

    implementation(Dependencies.Fake)
    implementation(Dependencies.CoroutinesX.Core)
    implementation(Dependencies.AppCompat)

    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":meta"))
    implementation(project(":ui"))
    implementation(project(":app"))

    testImplementation(Dependencies.JUnit)
    testImplementation(Dependencies.Truth)
    testImplementation(Dependencies.Koin.JUnit)
    testImplementation(Dependencies.Mockk.Mockk)

    androidTestImplementation(Dependencies.Fake)
    androidTestImplementation(Dependencies.RoomX.Kotlin)
    androidTestImplementation(Dependencies.Koin.Android)
    androidTestImplementation(Dependencies.Koin.JUnit)
    androidTestImplementation(Dependencies.CoroutinesX.Test)
    androidTestImplementation(Dependencies.Mockk.Android)
    androidTestImplementation(Dependencies.ComposeX.Test)
    androidTestImplementation(Dependencies.Espresso.Intents)
    androidTestImplementation(Dependencies.Truth)
}
